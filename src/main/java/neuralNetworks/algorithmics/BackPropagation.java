package neuralNetworks.algorithmics;

import dataTypes.Data;
import neuralNetworks.objects.basicObjects.Neuron;
import neuralNetworks.objects.basicObjects.Weight;
import neuralNetworks.objects.complexObjects.BiasWeightPair;
import neuralNetworks.objects.complexObjects.Layer;
import neuralNetworks.objects.complexObjects.WeightVector;
import neuralNetworks.objects.complexObjects.WeightsMat;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BackPropagation implements TrainingAlgorithm {

    private final double learningRate;
    private final double acceptedError;

    public BackPropagation(double learningRate, double acceptedError) {
        this.learningRate = learningRate;
        this.acceptedError = acceptedError;
    }

    @Override
    public List<WeightsMat> getAdjustedWeights(List<Layer> layers, List<WeightsMat> weightMats, Data outputPattern) {
        reverseLayersAndWeights(layers, weightMats);

        List<WeightsMat> result = IntStream.range(0, layers.size())
                .limit(layers.size()-1)
                .mapToObj(i -> {
                    Layer output = layers.get(i);
                    Layer input = layers.get(i+1);
                    Layer errors = i == 0 ? getOutputErrors(output,outputPattern.getOutputPointsAsNeurons()) : getExpectedLayerErrors(output, layers.get(i-1), weightMats.get(i-1));

                    return getCorrectedWeights(input, output, weightMats.get(i), errors);
                })
                .collect(Collectors.toList());

        reverseLayersAndWeights(layers, result);
        return result;
    }
    @Override
    public List<WeightVector> getAdjustedBiasWeightList(List<BiasWeightPair> biasWeightPairs, List<Layer> layers, Data outputPattern) {

        Collections.reverse(biasWeightPairs);
        Collections.reverse(layers);

        List<WeightVector> result = IntStream.range(0, biasWeightPairs.size())
                .mapToObj(i ->  {
                    BiasWeightPair currentPair = biasWeightPairs.get(i);
                    Layer output = layers.get(i);

                    return getAdjustedBiasWeights(currentPair, output, i == 0 ? getOutputErrors(output, outputPattern.getOutputPointsAsNeurons()) : getExpectedLayerErrorsSingleInput(output, layers.get(i-1), currentPair.getWeights()));
                })
                .collect(Collectors.toList());
        Collections.reverse(result);
        Collections.reverse(layers);

        return result;
    }

    public WeightVector getAdjustedBiasWeights(BiasWeightPair biasWeightPair, Layer output, Layer errors) {
        return IntStream.range(0, errors.size())
                .mapToObj(i -> calcCorrectWeight(biasWeightPair.getWeights().get(i), new Neuron(biasWeightPair.getBias()),output.get(i), errors.get(i).get()))
                .collect(Collectors.toCollection(WeightVector::new));
    }

    private Layer getOutputErrors(Layer resultedLayer, Layer expectedLayer) {
        return IntStream.range(0, resultedLayer.size())
                .mapToObj(v -> expectedLayer.get(v).sub(resultedLayer.get(v).get()))
                .collect(Collectors.toCollection(Layer::new));
    }

    private Layer getExpectedLayerErrors(Layer input, Layer output, WeightsMat weights) {
        Layer tmp = new Layer(input.size());

        IntStream.range(0, output.size())
                .forEach(i -> tmp.set(tmp.sum(calcExpectedNeuronError(weights.get(i), output.get(i), input))));
        return tmp;
    }

    private Layer getExpectedLayerErrorsSingleInput(Layer input, Layer output, WeightVector weights) {
        Layer tmp = new Layer(input.size());

        IntStream.range(0, output.size())
                .forEach(i -> tmp.sum(calcExpectedNeuronError(weights, output.get(i), input)));
        return tmp;
    }

    private Layer calcExpectedNeuronError(WeightVector connectedWeights, Neuron output, Layer input) {
        return IntStream.range(0, input.size())
                .mapToObj(i -> new Neuron(output.sub(input.get(i).get()).mul(connectedWeights.get(i).get()).get()))
                .collect(Collectors.toCollection(Layer::new));
    }

    private WeightsMat getCorrectedWeights(Layer input, Layer output, WeightsMat correspondingWeights, Layer outputErrors) {
        return IntStream.range(0, output.size())
                .mapToObj(i -> getCorrectedWeightsVector(correspondingWeights.get(i), input, output.get(i), outputErrors.get(i).get()))
                .collect(Collectors.toCollection(WeightsMat::new));
    }

    private WeightVector getCorrectedWeightsVector(WeightVector currentWeights, Layer inputLayer, Neuron outputNeuron, double outputError) {
        return IntStream.range(0,currentWeights.size())
                .mapToObj(i -> calcCorrectWeight(currentWeights.get(i), inputLayer.get(i), outputNeuron, outputError))
                .collect(Collectors.toCollection(WeightVector::new));
    }

    private Weight calcCorrectWeight(Weight currWeight, Neuron input, Neuron output, double outputError) {
        return currWeight.sum(learningRate * outputError *input.get() * output.get() * (1 - output.get()));
    }

    private void reverseLayersAndWeights(List<Layer> layers, List<WeightsMat> weightMats) {
        Collections.reverse(layers);
        Collections.reverse(weightMats);
    }

    @Override
    public boolean hasLearned(Layer outputLayer, Data outputPattern) {
        return outputLayer.stream()
                .map(Neuron::new)
                .allMatch(n -> constrained(outputLayer.get(outputLayer.size()-1).get(),
                        outputPattern.getOutputPoint(outputLayer.indexOf(n)) - acceptedError,
                        outputPattern.getOutputPoint(outputLayer.indexOf(n)) + acceptedError));
    }

    private boolean constrained(double val, double min, double max) {
        min = min < 0 ? 0 : min;
        max = max > 1 ? 1 : max;
        return val >= min && val <= max;
    }
}
