package neuralNetworks.algorithmics;

import dataTypes.Data;
import dataTypes.Matrix;
import dataTypes.Vector;
import neuralNetworks.objects.basicObjects.Bias;
import neuralNetworks.objects.basicObjects.Neuron;
import neuralNetworks.objects.basicObjects.Weight;
import neuralNetworks.objects.complexObjects.Layer;
import neuralNetworks.objects.complexObjects.WeightVector;
import neuralNetworks.objects.complexObjects.WeightsMat;

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
    public double adjustToOutput(List<Layer> layers, Matrix<Bias> biases, List<WeightsMat> weightMats, Layer wantedOutput) {
        List<Layer> layerAdditions = new ArrayList<>();
        Matrix<Bias> biasAdditions = new Matrix<>();
        List<WeightsMat> weightAdditions = new ArrayList<>();

        reverseNetwork(layers, biases, weightMats);

        IntStream.range(0, layers.size())
                .forEach(l -> {

                });

        reverseNetwork(layers, biases, weightMats);

        return calcCost(layers.get(layers.size()-1), wantedOutput);
    }

    private void reverseNetwork(List<Layer> layers, Matrix<Bias> biases, List<WeightsMat> weightMats) {
        Collections.reverse(layers);
        Collections.reverse(biases);
        Collections.reverse(weightMats);
    }

    private void

    //prevErrors as in the last calculated errors but the rest are chronologically
    private Layer getErrors(Layer prevErrors, WeightsMat nextWeights, Layer prevLayer) {
        return new Layer(nextWeights.mulByVector(layerToWeights(prevErrors)).dot(new Vector<Weight>(prevLayer.size(), new Weight(1)).sub(layerToWeights(prevLayer))));
    }

    private WeightVector layerToWeights(Layer layer) {
        return layer.stream()
                .map(Weight::new)
                .collect(Collectors.toCollection(WeightVector::new));
    }
}
