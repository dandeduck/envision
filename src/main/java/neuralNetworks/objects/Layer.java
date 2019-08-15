package neuralNetworks.objects;

import dataTypes.DoubleVector;
import dataTypes.Matrix;
import neuralNetworks.algorithmics.ActivationFunction;

import java.util.function.DoubleFunction;

public class Layer {

    private final DoubleVector neurons;
    private final DoubleVector biases;
    private final Matrix weightMat;

    private Layer(DoubleVector neurons, DoubleVector biases, Matrix weightMat) {
        this.neurons = neurons;
        this.biases = biases;
        this.weightMat = weightMat;
    }

    public Layer(int prevLayerSize, int layerSize) {
        this(new DoubleVector(layerSize), new DoubleVector(prevLayerSize), new Matrix(layerSize, prevLayerSize));
    }

    public Matrix getWeightMats() {
        return weightMat;
    }

    public DoubleVector getBiases() {
        return biases;
    }

    public DoubleVector getNeurons() {
        return neurons;
    }

    public Layer activate(DoubleVector input, ActivationFunction activationFunction) {
        return new Layer(calcActivations(input, activationFunction), biases, weightMat);
    }

    private DoubleVector calcActivations(DoubleVector input, ActivationFunction activationFunction) {
        return weightMat.transpose().multiplyByVector(input).add(biases).applyFunction((DoubleFunction<Double>) activationFunction::process); // f(W*a+b)
    }

    public Layer descent(Layer descentLayer) {
        return new Layer(neurons.subtract(descentLayer.getNeurons()), biases.subtract(descentLayer.getBiases()), weightMat.subtract(descentLayer.getWeightMats()));
    }

    public Layer scale(double scalar) {
        return new Layer(neurons.scale(scalar), biases.scale(scalar), weightMat.scale(scalar));
    }

    public Layer add(Layer layer) {
        return new Layer(neurons.add(layer.getNeurons()), biases.add(layer.getBiases()), weightMat.add(layer.getWeightMats()));
    }
}
