package neuralNetworks.objects;

import dataTypes.DoubleVector;
import dataTypes.Matrix;
import dataTypes.MatrixVector;
import neuralNetworks.algorithmics.ActivationFunction;
import neuralNetworks.algorithmics.TrainingAlgorithm;
import neuralNetworks.constants.enums.ActivationFunctionTypes;
import neuralNetworks.constants.enums.TrainingAlgorithmTypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NeuralNetwork {

    private TrainingAlgorithm trainingAlgorithm;
    private final ActivationFunction activationFunction;

    private Matrix neuronLayers;
    private Matrix biasLayers;
    private MatrixVector weightMats;

    public NeuralNetwork(ActivationFunctionTypes activationType, Integer... layerSizes) {
        this(activationType, Arrays.asList(layerSizes));
    }

    public NeuralNetwork(ActivationFunctionTypes activationType, List<Integer> layerSizes) {
        activationFunction = new ActivationFunction(activationType);

        neuronLayers = new Matrix(layerSizes.size(), layerSizes);
        biasLayers = new Matrix(layerSizes.size(), layerSizes);
        weightMats = new MatrixVector(innerSizes(layerSizes), outerSizes(layerSizes));
    }

    private List<Integer> innerSizes(List<Integer> layerSizes) {
        return IntStream.range(0, layerSizes.size())
                .filter(index -> index%2 != 0)
                .mapToObj(layerSizes::get)
                .collect(Collectors.toList());
    }

    private List<Integer> outerSizes(List<Integer> layerSizes) {
        return IntStream.range(0, layerSizes.size())
                .filter(index -> index%2 == 0)
                .mapToObj(layerSizes::get)
                .collect(Collectors.toList());
    }

    public void train(TrainingAlgorithmTypes algorithmType, double learningRate, List<NetworkPattern> networkPatterns, int clusterSize) {
        trainingAlgorithm = algorithmType.getAlgorithm(learningRate);

        while(true) {//should be a while error is below a certain number, but not for tests
            List<NetworkPatternsCluster> clusters = getClusters(networkPatterns, clusterSize);
            clusters.forEach(cluster -> descent(calcAverageGradientDescentStep(cluster)));
        }
    }

    private List<NetworkPatternsCluster> getClusters(List<NetworkPattern> networkPatterns, int clusterSize) {
        List<NetworkPatternsCluster> clusters = new ArrayList<>();
        Collections.shuffle(networkPatterns);

        while(!networkPatterns.isEmpty())
            clusters.add(new NetworkPatternsCluster(networkPatterns, clusterSize));

        return clusters;
    }

    private void descent(NetworkGradient gradient) {
        neuronLayers.subtract(gradient.getNeuronLayersDescent());
        biasLayers.subtract(gradient.getBiasLayersDescent());
        weightMats = new MatrixVector(weightMats.subtract(gradient.getWeightMatsDescent()));
    }

    private NetworkGradient calcAverageGradientDescentStep(NetworkPatternsCluster cluster) {
        List<NetworkGradient> gradients = cluster.getPatterns().stream()
                .map(this::calcGradientDescentStep)
                .collect(Collectors.toList());

        return averageGradient(gradients);
    }

    private NetworkGradient calcGradientDescentStep(NetworkPattern pattern) {
        feedForward(pattern.getInput());
        return trainingAlgorithm.calcGradientDescentStep(neuronLayers, biasLayers, weightMats, pattern.getOutput());
    }

    private NetworkGradient averageGradient(List<NetworkGradient> gradients) {
        NetworkGradient avg = gradients.get(0);
        gradients.stream().skip(1).forEach(avg::add);
        return avg.divide(1/gradients.size());
    }

    private void feedForward(DoubleVector inputValues) {
        neuronLayers = new Matrix(neuronLayers.lengthStream()
                .mapToObj(index -> feedNext(inputValues, index))
                .collect(Collectors.toList()));
    }

    private DoubleVector feedNext(DoubleVector inputValues, int index) {
        if(isFirstLayer(index))
            return inputValues;
        else {
            index--;
            return calcNextNeuronLayer(neuronLayers.get(index), weightMats.get(index), biasLayers.get(index));
        }
    }

    private boolean isFirstLayer(int index) {
        return index == 0;
    }

    private DoubleVector calcNextNeuronLayer(DoubleVector inputLayer, Matrix weightsMat, DoubleVector inputLayerBiases) {
        return new DoubleVector(new DoubleVector(weightsMat.transpose().multiplyByVector(inputLayer).add(inputLayerBiases)).applyFunction(activationFunction::process));
    }
}
