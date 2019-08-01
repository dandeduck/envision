package neuralNetworks.objects.complexObjects;

import dataTypes.Data;
import neuralNetworks.algorithmics.ActivationFunction;
import neuralNetworks.algorithmics.TrainingAlgorithm;
import neuralNetworks.constants.enums.ActivationFunctionTypes;
import neuralNetworks.constants.enums.TrainingAlgorithmTypes;
import neuralNetworks.objects.basicObjects.Neuron;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Network {

    private final List<Layer> layers;
    private final List<WeightsMat> weightMatrices;

    private final List<DataCluster> dataClusters;
    private final ActivationFunction activationFunction;
    private final TrainingAlgorithm trainingAlgorithm;

    public Network(List<Data> dataList, int clusterSize, ActivationFunctionTypes functionType, TrainingAlgorithmTypes algorithmType, double learningRate, double acceptedError, Integer... layerSizes) {//in the future change Data to List<Data> and get TrainingAlgorithm or Enum of it
        dataClusters = new ArrayList<>();
        Collections.shuffle(dataList);
        divideDataIntoClusters(dataList, clusterSize);
        activationFunction = new ActivationFunction(functionType);
        trainingAlgorithm = algorithmType.getAlgorithm(learningRate, acceptedError);

        layers = initLayers(Arrays.asList(layerSizes));
        weightMatrices = initWeightMatrices();
    }

    private void divideDataIntoClusters(List<Data> dataList, int clusterSize) {
        while (!dataList.isEmpty()) {
            DataCluster cluster = new DataCluster(clusterSize);
            dataList.removeAll(cluster.addData(dataList));
            dataClusters.add(cluster);
        }
    }

    private List<Layer> initLayers(List<Integer> layerSizes) {
        return layerSizes.stream()
                .map(Layer::new)
                .collect(Collectors.toList());
    }

    private List<WeightsMat> initWeightMatrices() {
        return IntStream.range(0, layers.size())
                .skip(1)
                .mapToObj(e -> new WeightsMat(layers.get(e-1).size(), layers.get(e).size()))
                .collect(Collectors.toList());
    }

    public void train() {
        dataClusters.forEach(c -> addPatterns(c));
    }

    private void addPatterns(DataCluster cluster) {
        do {
            cluster.forEach(this::learnPattern);
        } while (!hasLearnedCluster(cluster));
    }

    private boolean hasLearnedCluster(DataCluster cluster) {
        return cluster.stream()
                .allMatch(d -> {
                    feedForward(d.getInputPointsAsNeurons());
                    return trainingAlgorithm.hasLearned(layers.get(layers.size()-1), d);
                });
    }

    private void learnPattern(Data outputPattern) {
        feedForward(outputPattern.getInputPointsAsNeurons());
        replaceWeights(trainingAlgorithm.getAdjustedWeights(layers, weightMatrices, outputPattern));
        System.out.printf("%.5f ", layers.get(layers.size()-1).get(0).get());
        System.out.printf(outputPattern.getOutputPointsAsNeurons().toString() + "\n");
    }

    public List<Double> compute(Data d) {
        feedForward(d.getInputPointsAsNeurons());
        return layers.get(layers.size()-1).stream()
                .map(Neuron::new)
                .mapToDouble(n -> n.get())
                .collect(ArrayList::new,ArrayList::add,ArrayList::addAll);
    }

    private void replaceWeights(List<WeightsMat> newWeights) {
        weightMatrices.clear();
        weightMatrices.addAll(newWeights);
    }

    private void feedForward(Layer input) {
        updateInputNeurons(input);
        IntStream.range(0, layers.size())
                .skip(1)
                .forEach(i -> feedNextLayer(layers.get(i-1), weightMatrices.get(i-1), layers.get(i)));
    }

    private void updateInputNeurons(Layer input) {
        layers.get(0).updateLayer(input);
    }

    private void feedNextLayer(Layer prevLayer, WeightsMat weightsMat, Layer nextLayer) {
        nextLayer.updateLayer(calcNextValues(weightsMat, prevLayer));
    }

    private Layer calcNextValues(WeightsMat W, Layer a) {
        return W.mulByNeurons(a).stream()
                .map(activationFunction::process)
                .map(Neuron::new)
                .collect(Collectors.toCollection(Layer::new));
    }
}
