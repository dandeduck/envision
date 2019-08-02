package neuralNetworks.objects.complexObjects;

import dataTypes.Data;
import dataTypes.Matrix;
import dataTypes.Vector;
import neuralNetworks.algorithmics.ActivationFunction;
import neuralNetworks.algorithmics.TrainingAlgorithm;
import neuralNetworks.constants.enums.ActivationFunctionTypes;
import neuralNetworks.constants.enums.TrainingAlgorithmTypes;
import neuralNetworks.objects.basicObjects.Bias;
import neuralNetworks.objects.basicObjects.Neuron;
import neuralNetworks.objects.basicObjects.Weight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Network {

    private final Matrix<Neuron> layers;
    private final Matrix<Bias> biases;
    private final Vector<Matrix<Weight>> weightMatrices;

    private final List<DataCluster> dataClusters;
    private final ActivationFunction activationFunction;
    private final TrainingAlgorithm trainingAlgorithm;

    public Network(List<Data> dataList, int clusterSize, ActivationFunctionTypes functionType, TrainingAlgorithmTypes algorithmType, double learningRate, Integer... layerSizes) {//in the future change Data to List<Data> and get TrainingAlgorithm or Enum of it
        dataClusters = new ArrayList<>();
        Collections.shuffle(dataList);
        divideDataIntoClusters(dataList, clusterSize);
        activationFunction = new ActivationFunction(functionType);
        trainingAlgorithm = algorithmType.getAlgorithm(learningRate);

        layers = initLayers(Arrays.asList(layerSizes));
        biases = initBiasMat();
        weightMatrices = initWeightMatrices();
    }

    private void divideDataIntoClusters(List<Data> dataList, int clusterSize) {
        while (!dataList.isEmpty()) {
            DataCluster cluster = new DataCluster(clusterSize);
            dataList.removeAll(cluster.addData(dataList));
            dataClusters.add(cluster);
        }
    }

    private Matrix<Neuron> initLayers(List<Integer> layerSizes) {
        return layerSizes.stream()
                .map(e -> new Vector<Neuron>(e, Neuron::new))
                .collect(Collectors.toCollection(Matrix::new));
    }

    private Matrix<Bias> initBiasMat() {
        return layers.stream()
                .limit(layers.size()-1)
                .skip(1)
                .map(Vector::new)
                .map(l -> new Vector<Bias>(l.size(), Bias::new))
                .collect(Collectors.toCollection(Matrix::new));
    }

    private Vector<Matrix<Weight>> initWeightMatrices() {
        return IntStream.range(0, layers.size())
                .skip(1)
                .mapToObj(e -> new Matrix<>(layers.get(e-1).size(), layers.get(e).size(), Weight::new))
                .collect(Collectors.toCollection(Vector::new));
    }

    public void train() {
        dataClusters.forEach(c -> gradientDescent(c));
    }

    public void gradientDescent(DataCluster cluster) {
        List<NetworkDescent> descents = new ArrayList<>();
        cluster.forEach(d -> descents.add(calcDescent(d)));

        descents.get(0).averageAdditions(descents);
        applyDescent(descents.get(0));
    }

    private void applyDescent(NetworkDescent descent) {
        layers.sub(descent.getLayersDescent());
        biases.sub(descent.getBiasesDescent());
        weightMatrices.sub(descent.getWeightsDescent());
    }

    private NetworkDescent calcDescent(Data data) {
        feedForward(data.getInputPointsAsNeurons());
        return trainingAlgorithm.calcDescent(layers, biases, weightMatrices, data.getOutputPointsAsNeurons());
    }

    public List<Double> compute(Data d) {
        feedForward(d.getInputPointsAsNeurons());
        return layers.get(layers.size()-1).stream()
                .map(Neuron::new)
                .mapToDouble(n -> n.get())
                .collect(ArrayList::new,ArrayList::add,ArrayList::addAll);
    }

    private void feedForward(Vector<Neuron> input) {
        updateInputNeurons(input);
        IntStream.range(0, layers.size())
                .skip(1)
                .forEach(i -> feedNextLayer(layers.get(i-1), weightMatrices.get(i-1), layers.get(i), biases.get(i-1)));
    }

    private void updateInputNeurons(Vector<Neuron> input) {
        layers.get(0).set(input);
    }

    private void feedNextLayer(Vector<Neuron> prevLayer, Matrix<Weight> weightsMat, Vector<Neuron> nextLayer, Vector<Bias> layerBiases) {
        nextLayer.set(calcNextValues(weightsMat, prevLayer, layerBiases));
    }

    private Vector calcNextValues(Matrix<Weight> W, Vector<Neuron> a, Vector<Bias> b) {
        return W.mulByVector(a).sum(b).applyFunc(activationFunction::process);
    }
}
