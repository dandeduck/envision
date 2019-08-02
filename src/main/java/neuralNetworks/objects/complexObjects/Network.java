package neuralNetworks.objects.complexObjects;

import dataTypes.Data;
import dataTypes.Matrix;
import dataTypes.NumberValue;
import dataTypes.Vector;
import neuralNetworks.algorithmics.ActivationFunction;
import neuralNetworks.algorithmics.TrainingAlgorithm;
import neuralNetworks.constants.enums.ActivationFunctionTypes;
import neuralNetworks.constants.enums.TrainingAlgorithmTypes;
import neuralNetworks.objects.basicObjects.Bias;
import neuralNetworks.objects.basicObjects.Neuron;
import neuralNetworks.objects.basicObjects.Weight;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Network {

    private final Matrix<Neuron> layers;
    private final Matrix<Bias> biases;
    private final Vector<Matrix<Weight>> weightMatrices;

    private int clusterSize;
    private final List<Data> dataSet;
    private final List<DataCluster> dataClusters;
    private final ActivationFunction activationFunction;
    private final TrainingAlgorithm trainingAlgorithm;

    public Network(List<Data> dataList, int clusterSize, ActivationFunctionTypes functionType, TrainingAlgorithmTypes algorithmType, double learningRate, Integer... layerSizes) {
        this.clusterSize = clusterSize;
        dataSet = new ArrayList<>();
        dataSet.addAll(dataList);
        dataClusters = new ArrayList<>();

        activationFunction = new ActivationFunction(functionType);
        trainingAlgorithm = algorithmType.getAlgorithm(learningRate);

        layers = initLayers(Arrays.asList(layerSizes));
        biases = initBiasMat();
        weightMatrices = initWeightMatrices();
    }

    private Matrix<Neuron> initLayers(List<Integer> layerSizes) {
        return layerSizes.stream()
                .map(e -> new Vector<Neuron>(e, Neuron::new))
                .collect(Collectors.toCollection(Matrix::new));
    }

    private Matrix<Bias> initBiasMat() {
        return layers.stream()
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
        do {
            shuffleAndDivideDataIntoClusters(dataSet);

            dataClusters.forEach(c -> gradientDescent(c));

            feedForward(dataClusters.get(0).get(0).getInput());
            System.out.println(trainingAlgorithm.calcCost(layers.get(layers.size()-1),dataClusters.get(0).get(0).getOutput()));

        } while(true);
    }

    private void shuffleAndDivideDataIntoClusters(List<Data> dataList) {
        List<Data> tmp = new ArrayList<>();
        tmp.addAll(dataList);
        Collections.shuffle(tmp);

        while (!tmp.isEmpty()) {
            DataCluster cluster = new DataCluster(clusterSize);
            tmp.removeAll(cluster.addData(tmp));
            dataClusters.add(cluster);
        }
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
        feedForward(data.getInput());
        return trainingAlgorithm.calcDescent(layers, biases, weightMatrices, data.getOutput());
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

    private Vector<Neuron> calcNextValues(Matrix<Weight> W, Vector<Neuron> a, Vector<Bias> b) {
        return new Vector<>((Collection) W.transpose().mulByVector(a).sum(b).applyFunc(activationFunction::process));
    }

    public List<Double> compute(Data d) {
        feedForward(d.getInput());
        return layers.get(layers.size()-1).stream()
                .map(Neuron::new)
                .mapToDouble(n -> n.get())
                .collect(ArrayList::new,ArrayList::add,ArrayList::addAll);
    }
}
