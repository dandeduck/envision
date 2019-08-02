package neuralNetworks.algorithmics;

import dataTypes.Matrix;
import dataTypes.Vector;
import neuralNetworks.objects.basicObjects.Bias;
import neuralNetworks.objects.basicObjects.Neuron;
import neuralNetworks.objects.basicObjects.Weight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BackPropagation extends TrainingAlgorithm {

    private final double learningRate;
    private final double acceptedError;

    public BackPropagation(double learningRate, double acceptedError) {
        this.learningRate = learningRate;
        this.acceptedError = acceptedError;
    }

    @Override
    public double adjustToOutput(Matrix<Neuron> layers, Matrix<Bias> biases, Vector<Matrix<Weight>> weightMats, Vector<Neuron> wantedOutput) {
        Matrix<Neuron> layerAdditions = new Matrix<>();
        Matrix<Bias> biasAdditions = new Matrix<>();
        Vector<Matrix<Weight>> weightAdditions = new Vector<>();

        reverseNetwork(layers, biases, weightMats);

        IntStream.range(0, layers.size())
                .forEach(l -> {

                });

        reverseNetwork(layers, biases, weightMats);

        return calcCost(layers.get(layers.size()-1), wantedOutput);
    }

    private void reverseNetwork(Matrix<Neuron> layers, Matrix<Bias> biases, Vector<Matrix<Weight>> weightMats) {
        Collections.reverse(layers);
        Collections.reverse(biases);
        Collections.reverse(weightMats);
    }

    //prevErrors as in the last calculated errors but the rest are chronologically
    private Vector getErrors(Vector<Neuron> prevErrors, Matrix<Weight> nextWeights, Vector<Neuron> prevLayer) {
        return nextWeights.mulByVector(prevErrors).mul(new Vector<>(prevLayer.size(), new Weight(1.0)).sub(prevLayer));
    }
}
