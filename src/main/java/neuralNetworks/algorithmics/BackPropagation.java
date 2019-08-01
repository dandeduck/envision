package neuralNetworks.algorithmics;

import dataTypes.Data;
import neuralNetworks.objects.basicObjects.Neuron;
import neuralNetworks.objects.complexObjects.Layer;
import neuralNetworks.objects.complexObjects.WeightsMat;

import java.util.List;

public class BackPropagation implements TrainingAlgorithm {

    private final double learningRate;
    private final double acceptedError;

    public BackPropagation(double learningRate, double acceptedError) {
        this.learningRate = learningRate;
        this.acceptedError = acceptedError;
    }

    @Override
    public double getOutput(List<Layer> layers, List<WeightsMat> weightMats, Layer wantedOutput) {
        return 0;
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
