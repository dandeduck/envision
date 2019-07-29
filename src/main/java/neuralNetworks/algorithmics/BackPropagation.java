package neuralNetworks.algorithmics;

import dataTypes.Vector;
import neuralNetworks.objects.basicObjects.Neuron;
import neuralNetworks.objects.basicObjects.Weight;
import neuralNetworks.objects.complexObjects.Layer;
import neuralNetworks.objects.complexObjects.WeightsMat;

import java.util.List;
import java.util.stream.Collectors;

public class BackPropagation implements TrainingAlgorithm {

    private final double learningRate;

    public BackPropagation(double learningRate) {
        this.learningRate = learningRate;
    }

    @Override
    public WeightsMat getWightChanges(Layer inputLayer,Layer resultedLayer, Layer expectedLayer, WeightsMat correspondingWeights) {
        List<Double> errors = calcOutputErrors(resultedLayer, expectedLayer);

        return new WeightsMat(correspondingWeights.stream()
                    .map(vw -> getCorrectedWeightsVector(vw, inputLayer, resultedLayer, expectedLayer,errors))
                    .collect(Collectors.toList()));
    }

    private List<Double> calcOutputErrors(Layer resultedLayer, Layer expectedLayer) {
        return null;
    }

    private Vector<Weight> getCorrectedWeightsVector(Vector<Weight> currentVector, Layer inputLayer, Layer resultedLayer, Layer expectedLayer, List<Double> errors) {
        return new Vector<>(currentVector.stream()
                .map(w -> calcCorrectWeight(new Weight(w), gerCorrespondingNeuron(currentVector, new Weight(w), inputLayer),
                        gerCorrespondingNeuron(currentVector, new Weight(w), resultedLayer),
                        gerCorrespondingNeuron(currentVector, new Weight(w), expectedLayer),
                        getCorrespondingError(resultedLayer, gerCorrespondingNeuron(currentVector, new Weight(w), resultedLayer), errors)))
                .collect(Collectors.toList()));
    }

    private Weight calcCorrectWeight(Weight currWeight, Neuron input, Neuron output, Neuron expected, double error) {
        return currWeight.sum(learningRate * error *input.get() * output.get() * (1 - expected.get()));
    }

    private Neuron gerCorrespondingNeuron(Vector<Weight> weights, Weight weight, Layer nextLayer) {
        return nextLayer.getNeuron(weights.indexOf(weight));
    }

    private double getCorrespondingError(Layer resultedLayer, Neuron neuron, List<Double> errors) {
        return errors.get(resultedLayer.indexOf(neuron));
    }
}
