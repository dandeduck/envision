package neuralNetworks.algorithmics;

import dataTypes.Matrix;
import dataTypes.NumberValue;
import dataTypes.Value;
import dataTypes.Vector;
import neuralNetworks.objects.basicObjects.Bias;
import neuralNetworks.objects.basicObjects.Neuron;
import neuralNetworks.objects.basicObjects.Weight;
import neuralNetworks.objects.complexObjects.NetworkDescent;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BackPropagation extends TrainingAlgorithm {

    private final double learningRate;

    private final Matrix<Neuron> layersDescent;
    private final Matrix<Bias> biasesDescent;
    private final Vector<Matrix<Weight>> weightsDescent;
    private final Vector<Neuron> prevErrors;

    public BackPropagation(double learningRate) {
        this.learningRate = learningRate;

        layersDescent = new Matrix<>();
        biasesDescent = new Matrix<>();
        weightsDescent = new Vector<>();
        prevErrors = new Vector<>();
    }

    @Override
    public NetworkDescent calcDescent(Matrix<Neuron> layers, Matrix<Bias> biases, Vector<Matrix<Weight>> weightMats, Vector<NumberValue> desiredOutput) {
        clearDescents();
        reverseNetwork(layers, biases, weightMats);

        IntStream.range(0, layers.size()-1)
                .forEach(i -> backProp(layers, biases, weightMats, desiredOutput, i));

        reverseNetwork(layers, biases, weightMats);
        reverseNetwork(layersDescent, biasesDescent, weightsDescent);

        return new NetworkDescent(layersDescent, biasesDescent, weightsDescent);
    }

    private void clearDescents() {
        layersDescent.clear();
        biasesDescent.clear();
        weightsDescent.clear();
    }

    private void reverseNetwork(Matrix<Neuron> layers, Matrix<Bias> biases, Vector<Matrix<Weight>> weightMats) {
        Collections.reverse(layers);
        Collections.reverse(biases);
        Collections.reverse(weightMats);
    }

    private void backProp(Matrix<Neuron> layers, Matrix<Bias> biases, Vector<Matrix<Weight>> weightMats, Vector<NumberValue> wantedOutput, int index) {
        if(isOutputLayer(index))
            backPropOutputLayer(layers, biases, weightMats, wantedOutput);
        else
            normalBackProp(layers, biases, weightMats, index);
    }

    private boolean isOutputLayer(int index) {
        return index == 0;
    }

    private void backPropOutputLayer(Matrix<Neuron> layers, Matrix<Bias> biases, Vector<Matrix<Weight>> weightMats, Vector<NumberValue> wantedOutput) {
        Vector<Neuron> output = layers.get(0);
        Vector<Neuron> input = layers.get(1);
        Vector<Neuron> errors = getOutputError(output, wantedOutput);//OK?

        updateDescent(input, errors);
        prevErrors.set(errors);
    }

    private Vector<Neuron> getOutputError(Vector<Neuron> outputLayer, Vector<NumberValue> wantedOutput) {
        return outputLayer.sub(wantedOutput); // A - Y
    }

    private void normalBackProp(Matrix<Neuron> layers, Matrix<Bias> biases, Vector<Matrix<Weight>> weightMats, int index) {
        Vector<Neuron> output = layers.get(index);
        Vector<Neuron> input = layers.get(index+1);
        Vector<Neuron> errors = calcErrors(prevErrors.get(), weightMats.get(index-1), output);

        updateDescent(input, errors);
        prevErrors.set(errors);
    }

    private void updateDescent(Vector<Neuron> input, Vector<Neuron> errors) {
        weightsDescent.add(calcWeightsDescent(input, errors));
        biasesDescent.add(calcBiasesDescent(input, errors));
    }

    private Matrix calcWeightsDescent(Vector<Neuron> input, Vector<Neuron> errors) {
        return IntStream.range(0, input.size())
                .mapToObj(e -> input.mul(errors).scale(new NumberValue(learningRate)))//dW = input * e * eta
                .collect(Collectors.toCollection(Matrix::new));
    }

    private Vector calcBiasesDescent(Vector<Neuron> input, Vector<Neuron> errors) {
        return input.mul(errors.scale(new NumberValue(learningRate)));
    }

    //prevErrors as in the last calculated errors but the rest are by how they're located in the network
    private Vector<Neuron> calcErrors(Vector<Neuron> prevErrors, Matrix<Weight> nextWeights, Vector<Neuron> output) {
        return new Vector<>((Collection) nextWeights.mulByVector(prevErrors).mul(new Vector<>(output.size(), new NumberValue(1.0)).sub(output)));//OK?
    }
}
