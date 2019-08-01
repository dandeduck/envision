package neuralNetworks.algorithmics;

import dataTypes.Data;
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
        return false;
    }
}
