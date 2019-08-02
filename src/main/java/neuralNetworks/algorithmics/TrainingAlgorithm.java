package neuralNetworks.algorithmics;

import dataTypes.Matrix;
import neuralNetworks.objects.basicObjects.Bias;
import neuralNetworks.objects.basicObjects.Neuron;
import neuralNetworks.objects.complexObjects.Layer;
import neuralNetworks.objects.complexObjects.WeightsMat;

import java.util.List;

public abstract class TrainingAlgorithm {
    abstract double adjustToOutput(List<Layer> layers, Matrix<Bias> biases, List<WeightsMat> weightMats, Layer desiredOutput);

    public double calcCost(Layer outputLayer, Layer desiredOutput) {
        return new Neuron(desiredOutput.sub(outputLayer).getSum()).get();
    }
}
