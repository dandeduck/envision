package neuralNetworks.algorithmics;

import dataTypes.Data;
import dataTypes.Vector;
import neuralNetworks.objects.basicObjects.Neuron;
import neuralNetworks.objects.basicObjects.Weight;
import neuralNetworks.objects.complexObjects.Layer;
import neuralNetworks.objects.complexObjects.WeightsMat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BackPropagation implements TrainingAlgorithm {

    private final double learningRate;
    private final double acceptedError;

    public BackPropagation(double learningRate, double expectedError) {
        this.learningRate = learningRate;
        this.acceptedError = expectedError;
    }

    @Override
    public List<WeightsMat> computeOutputPattern(List<Layer> layers, List<WeightsMat> weightMats, Data outputPattern) {
        List<Double> errors = getOutputErrors(layers.get(layers.size()-1), outputPattern.getOutputPoints());
        prepareForBackProp(layers, weightMats, outputPattern);

        List<WeightsMat> result = layers.stream()
                .map(l -> layers.indexOf(l) == layers.size()-1 ? null : getCorrectedWeights(getNextLayer(layers, l), l, getCorrespondingWeights(weightMats, layers, l), errors))
                .collect(Collectors.toList());
        Collections.reverse(weightMats);

        return result;
    }

    private void prepareForBackProp(List<Layer> layers, List<WeightsMat> weightMats, Data outputPattern) {
        layers.get(layers.size()-1).updateLayer(new Vector<>(outputPattern.getOutputPoints()));
        Collections.reverse(layers);
        Collections.reverse(weightMats);
    }

    private Layer getNextLayer(List<Layer> layers, Layer layer) {
        return layers.get(layers.indexOf(layer)+1);
    }

    private WeightsMat getCorrespondingWeights(List<WeightsMat> weightMats, List<Layer> layers, Layer layer) {
        return weightMats.get(layers.indexOf(layer));
    }

    @Override
    public boolean hasLearned(Layer outputLayer, Data outputPattern) {
        return outputLayer.getNeurons().stream()
                .map(Neuron::new)
                .allMatch(n -> constrained(n.get(),
                        outputPattern.getOutputPoint(outputLayer.indexOf(n)) - acceptedError,
                        outputPattern.getOutputPoint(outputLayer.indexOf(n)) + acceptedError));
    }

    private boolean constrained(double val, double min, double max) {
        return val >= min && val <= max;
    }

    private WeightsMat getCorrectedWeights(Layer inputLayer,Layer outputLayer, WeightsMat correspondingWeights, List<Double> outputErrors) {
        return new WeightsMat(correspondingWeights.stream()
                    .map(vw -> getCorrectedWeightsVector(vw, inputLayer, outputLayer, outputErrors))
                    .collect(Collectors.toList()));
    }

    private List<Double> getOutputErrors(Layer resultedLayer, List<Double> expectedLayer) {
        return IntStream.range(0, expectedLayer.size()-1)
                .mapToDouble(v -> expectedLayer.get(v) - resultedLayer.getNeuron(v).get())
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    private Vector<Weight> getCorrectedWeightsVector(Vector<Weight> currentVector, Layer inputLayer, Layer resultedLayer, List<Double> outputErrors) {
        return new Vector<>(currentVector.stream()
                .map(Weight::new)
                .map(w -> calcCorrectWeight(w, gerCorrespondingNeuron(currentVector, w, inputLayer),
                        gerCorrespondingNeuron(currentVector, w, resultedLayer),
                        getCorrespondingError(resultedLayer, gerCorrespondingNeuron(currentVector, w, resultedLayer), outputErrors)))
                .collect(Collectors.toList()));
    }

    private Weight calcCorrectWeight(Weight currWeight, Neuron input, Neuron output, double outputError) {
        return currWeight.sum(learningRate * outputError *input.get() * output.get() * (1 - output.get()));
    }

    private Neuron gerCorrespondingNeuron(Vector<Weight> weights, Weight weight, Layer nextLayer) {
        return nextLayer.getNeuron(weights.indexOf(weight));
    }

    private double getCorrespondingError(Layer resultedLayer, Neuron neuron, List<Double> errors) {
        return errors.get(resultedLayer.indexOf(neuron));
    }
}
