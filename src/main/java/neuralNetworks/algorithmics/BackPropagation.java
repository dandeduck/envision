package neuralNetworks.algorithmics;

import dataTypes.DoubleVector;
import dataTypes.Matrix;
import dataTypes.MatrixVector;

import neuralNetworks.objects.NetworkGradient;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BackPropagation extends TrainingAlgorithm {

    private final double learningRate;

    private Matrix biasLayersDescent;
    private MatrixVector weightsDescent;
    private DoubleVector prevErrors;

    public BackPropagation(double learningRate) {
        this.learningRate = learningRate;
        renewStoredValues();
    }

    private void renewStoredValues() {
        biasLayersDescent = new Matrix();
        weightsDescent = new MatrixVector();
        prevErrors = new DoubleVector();
    }

    @Override
    public NetworkGradient calcGradientDescentStep(Matrix neuronLayers, MatrixVector weightMats, DoubleVector outputPattern) {
        Matrix reversedNeuronLayers = new Matrix(neuronLayers.reverse());
        MatrixVector reversedWeightMats = new MatrixVector(weightMats.reverse());
        renewStoredValues();

        IntStream.range(0, neuronLayers.dimensions()-1)
                .forEach(i -> backProp(reversedNeuronLayers, reversedWeightMats, outputPattern, i));

        biasLayersDescent = new Matrix(biasLayersDescent.reverse());
        weightsDescent = new MatrixVector(weightsDescent.reverse());

        return new NetworkGradient(biasLayersDescent, weightsDescent);
    }

    private void backProp(Matrix neuronLayers, MatrixVector weightMats, DoubleVector outputPattern, int index) {
        if(isOutputLayer(index))
            backPropOutputLayer(neuronLayers, outputPattern);
        else
            normalBackProp(neuronLayers, weightMats, index);
    }

    private boolean isOutputLayer(int index) {
        return index == 0;
    }

    private void backPropOutputLayer(Matrix neuronLayers, DoubleVector outputPattern) {
        DoubleVector output = neuronLayers.get(0);
        DoubleVector input = neuronLayers.get(1);
        DoubleVector errors = getOutputError(output, outputPattern);

        updateDescent(input, errors);
        prevErrors = errors;
    }

    private DoubleVector getOutputError(DoubleVector outputLayer, DoubleVector desiredOutput) {
        return outputLayer.subtract(desiredOutput); // A - Y
    }

    private void normalBackProp(Matrix neuronLayers, MatrixVector weightMats, int index) {
        Matrix nextWeights = weightMats.get(index-1);
        DoubleVector input = neuronLayers.get(index);
        DoubleVector errors = calcErrors(prevErrors, nextWeights, input);

        updateDescent(input, errors);
        prevErrors = errors;
    }

    private void updateDescent(DoubleVector input, DoubleVector errors) {
        weightsDescent = weightsDescent.addMatrix(calcWeightsDescent(input, errors));
        biasLayersDescent = biasLayersDescent.addVector(calcBiasLayersDescent(errors));
    }

    private Matrix calcWeightsDescent(DoubleVector input, DoubleVector errors) {
        return new Matrix(input.stream()
                .map(neuron -> errors.scale(neuron).scale(learningRate))//a*dW = input * e * eta
                .collect(Collectors.toList()));
    }

    private DoubleVector calcBiasLayersDescent(DoubleVector errors) {
        return errors.scale(learningRate);
    }

    //prevErrors as in the last calculated errors but the rest are by how they're located in the network (before reversing)
    private DoubleVector calcErrors(DoubleVector prevErrors, Matrix nextWeights, DoubleVector currentLayer) {
        return nextWeights.multiplyByVector(prevErrors).multiply(new DoubleVector(currentLayer.dimensions(), 1.0).subtract(currentLayer.multiply(currentLayer)));
    }
}