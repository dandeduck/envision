package neuralNetworks.algorithmics;

import dataTypes.Matrix;
import dataTypes.Vector;
import neuralNetworks.objects.basicObjects.Bias;
import neuralNetworks.objects.basicObjects.Neuron;
import neuralNetworks.objects.basicObjects.Weight;

import java.util.List;

public abstract class TrainingAlgorithm {
    abstract double adjustToOutput(Matrix<Neuron> layers, Matrix<Bias> biases, Vector<Matrix<Weight>> weightMats, Vector<Neuron> desiredOutput);

    public double calcCost(Vector<Neuron> outputLayer, Vector<Neuron> desiredOutput) {
        return new Neuron(desiredOutput.sub(outputLayer).getSum()).get();
    }
}
