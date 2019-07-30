package neuralNetworks.objects.complexObjects;


import dataTypes.Data;
import dataTypes.Matrix;
import dataTypes.Value;
import dataTypes.Vector;
import neuralNetworks.algorithmics.ActivationFunction;
import neuralNetworks.algorithmics.BackPropagation;
import neuralNetworks.algorithmics.TrainingAlgorithm;
import neuralNetworks.constants.enums.ActivationFunctionTypes;
import neuralNetworks.objects.exception.NoCorrespondingWeightsException;
import neuralNetworks.objects.basicObjects.Neuron;
import neuralNetworks.objects.basicObjects.Weight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Network {

    private final List<Layer> layers;
    private final List<WeightsMat> weightMatrices;
    private final Data outputPattern;

    private final ActivationFunction activationFunction;
    private final TrainingAlgorithm trainingAlgorithm;

    public Network(Data outputPattern, ActivationFunctionTypes functionType, double learningRate, Integer... layerSizes) {//in the future change Data to List<Data> and get TrainingAlgorithm or Enum of it
        this.outputPattern = outputPattern;
        activationFunction = new ActivationFunction(functionType);

        layers = initLayers(Arrays.asList(layerSizes));
        weightMatrices = initWeightMatrices(Arrays.asList(layerSizes));
        trainingAlgorithm = new BackPropagation(learningRate, 0.01);
    }

    private List<Layer> initLayers(List<Integer> layerSizes) {
        return layerSizes.stream()
                .map(l -> layerSizes.indexOf(l) == 0 ? new Layer(l,false) : new Layer(l))
                .collect(Collectors.toList());
    }

    private List<WeightsMat> initWeightMatrices(List<Integer> layerSizes) {
        return IntStream.range(1, layerSizes.size())
                .skip(1)
                .mapToObj(m -> new WeightsMat(layerSizes.get(m-1), layerSizes.get(m)))
                .collect(Collectors.toList());
    }

    public void train() {
         do {
             System.out.println(layers.toString());
             feedFarward(new Vector<>(outputPattern.getInputPoints()));
             replaceWeights(trainingAlgorithm.computeOutputPattern(layers, weightMatrices, outputPattern));
        } while (trainingAlgorithm.hasLearned(layers.get(layers.size()-1), outputPattern));
    }

    private void replaceWeights(List<WeightsMat> newWeights) {
        weightMatrices.clear();
        weightMatrices.addAll(newWeights);
    }

    private void feedFarward(Vector<Neuron> input) {
        updateInputNeurons(input);
        layers.stream()
                .skip(1)
                .forEach(l -> feedNextLayer(getPrevLayer(layers,l),l));
    }

    private void updateInputNeurons(Vector<Neuron> input) {
        layers.get(0).updateLayer(input);
    }

    private Layer getPrevLayer(List<Layer> layers, Layer currentLayer) {
        return layers.get(layers.indexOf(currentLayer)-1);
    }


    private void feedNextLayer(Layer prevLayer, Layer nextLayer) {
        Matrix WeightsMat = getCorrespondingWeights(nextLayer);
        Vector<Neuron> prevValues = prevLayer.getNeurons();

        nextLayer.updateLayer(calcNextValues(WeightsMat, prevValues));
    }

    private Matrix<Weight> getCorrespondingWeights(Layer layer){
        try {
            checkIfLayerHasCorrespondingWeights(layer);
        } catch (NoCorrespondingWeightsException e) {
            e.printStackTrace();
        }
        return weightMatrices.get(layers.indexOf(layer)-1);
    }

    private void checkIfLayerHasCorrespondingWeights(Layer layer) throws NoCorrespondingWeightsException {
        if(layers.indexOf(layer) == 0)
            throw new NoCorrespondingWeightsException();
    }

    private Vector<Neuron> calcNextValues(Matrix<Weight> W, Vector<Neuron> a) {
        return new Vector<>(W.mulByVector(toWeights(a)).stream()
                .map(v -> activationFunction.process(v))
                .collect(Collectors.toList()));
    }

    private Vector<Weight> toWeights(Vector<Neuron> neurons) {
        return new Vector<>(neurons.stream()
                .map(Weight::new)
                .collect(Collectors.toList()));
    }
}
