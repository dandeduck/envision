package neuralNetworks.algorithmics;

import dataTypes.Data;
import dataTypes.Vector;
import neuralNetworks.objects.basicObjects.Neuron;
import neuralNetworks.objects.basicObjects.Weight;
import neuralNetworks.objects.complexObjects.Layer;
import neuralNetworks.objects.complexObjects.WeightVector;
import neuralNetworks.objects.complexObjects.WeightsMat;

import java.util.ArrayList;
import java.util.Collection;
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
        Layer errors = getOutputErrors(layers.get(layers.size()-1), outputPattern.getOutputPointsAsNeurons());

        updateOutputLayer(layers, outputPattern);
        reverseLayersAndWeights(layers, weightMats);

        List<WeightsMat> result = layers.stream()
                .limit(layers.size()-1)
                .map(l -> getCorrectedWeights(getNextLayer(layers, l), l, getCorrespondingWeights(weightMats, layers, l), errors))
                .collect(Collectors.toList());
        reverseLayersAndWeights(layers, weightMats);

        return result;
    }

    private void updateOutputLayer(List<Layer> layers, Data outputPattern) {
        layers.get(layers.size()-1).updateLayer(outputPattern.getOutputPointsAsNeurons());
    }

    private void reverseLayersAndWeights(List<Layer> layers, List<WeightsMat> weightMats) {
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
        return outputLayer.stream()
                .map(Neuron::new)
                .allMatch(n -> constrained(n.get(),
                        outputPattern.getOutputPoint(outputLayer.indexOf(n)) - acceptedError,
                        outputPattern.getOutputPoint(outputLayer.indexOf(n)) + acceptedError));
    }

    private boolean constrained(double val, double min, double max) {
        return val >= min && val <= max;
    }

    private WeightsMat getCorrectedWeights(Layer inputLayer,Layer outputLayer, WeightsMat correspondingWeights, Layer outputErrors) {
        return correspondingWeights.stream()
                    .map(vw -> getCorrectedWeightsVector(new WeightVector(vw), inputLayer, outputLayer, outputErrors))
                    .collect(Collectors.toCollection(WeightsMat::new));
    }

    private Layer getOutputErrors(Layer resultedLayer, Layer expectedLayer) {
        return IntStream.range(0, expectedLayer.size())
                .mapToObj(v -> expectedLayer.get(v).sub(resultedLayer.get(v).get()))
                .collect(Collectors.toCollection(Layer::new));
    }

    private WeightVector getCorrectedWeightsVector(WeightVector currentWeights, Layer inputLayer, Layer outputLayer, Layer outputErrors) {
        return IntStream.range(0,currentWeights.size())
                .mapToObj(i -> calcCorrectWeight(currentWeights.get(i), inputLayer.get(i), outputLayer.get(i), outputErrors.get(i).get()))
                .collect(Collectors.toCollection(WeightVector::new));
    }

    private Weight calcCorrectWeight(Weight currWeight, Neuron input, Neuron output, double outputError) {
        return currWeight.sum(learningRate * outputError *input.get() * output.get() * (1 - output.get()));
    }
}
