package neuralNetworks.algorithmics;

import dataTypes.Matrix;
import dataTypes.Vector;
import neuralNetworks.objects.basicObjects.Bias;
import neuralNetworks.objects.basicObjects.Neuron;
import neuralNetworks.objects.basicObjects.Weight;
import neuralNetworks.objects.complexObjects.NetworkDescent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BackPropagation extends TrainingAlgorithm {

    private final double learningRate;

    public BackPropagation(double learningRate) {
        this.learningRate = learningRate;
    }

    @Override
    public NetworkDescent calcDescent(Matrix<Neuron> layers, Matrix<Bias> biases, Vector<Matrix<Weight>> weightMats, Vector<Neuron> wantedOutput) {
        Matrix<Neuron> layersDescent = new Matrix<>();
        Matrix<Bias> biasesDescent = new Matrix<>();
        Vector<Matrix<Weight>> weightsDescent = new Vector<>();

        reverseNetwork(layers, biases, weightMats);

        IntStream.range(0, layers.size())
                .forEach(l -> );

        reverseNetwork(layers, biases, weightMats);

        return new NetworkDescent();
    }

    private void reverseNetwork(Matrix<Neuron> layers, Matrix<Bias> biases, Vector<Matrix<Weight>> weightMats) {
        Collections.reverse(layers);
        Collections.reverse(biases);
        Collections.reverse(weightMats);
    }

    private void

    //prevErrors as in the last calculated errors but the rest are by how they're located in the network
    private Vector getErrors(Vector<Neuron> prevErrors, Matrix<Weight> nextWeights, Vector<Neuron> layer) {
        return nextWeights.mulByVector(prevErrors).mul(new Vector<>(layer.size(), new Weight(1.0)).sub(layer));
    }
}
