package neuralNetworks;

import dataTypes.DoubleVector;
import dataTypes.Matrix;
import dataTypes.MatrixVector;
import neuralNetworks.algorithmics.ActivationFunction;
import neuralNetworks.constants.enums.ActivationFunctionTypes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NeuralNetwork {

    private final ActivationFunction activationFunction;

    private Matrix neuronLayers;
    private Matrix biasLayers;
    private MatrixVector weightMats;

    public NeuralNetwork(ActivationFunctionTypes activationType, Integer... layerSizes) {
        this(activationType, Arrays.asList(layerSizes));
    }

    public NeuralNetwork(ActivationFunctionTypes activationType, List<Integer> layerSizes) {
        activationFunction = new ActivationFunction(activationType);

        neuronLayers = new Matrix(layerSizes.size(), layerSizes);
        biasLayers = new Matrix(layerSizes.size(), layerSizes);
        weightMats = new MatrixVector(innerSizes(layerSizes), outerSizes(layerSizes));
    }

    private List<Integer> innerSizes(List<Integer> layerSizes) {
        return IntStream.range(0, layerSizes.size())
                .filter(index -> index%2 != 0)
                .mapToObj(layerSizes::get)
                .collect(Collectors.toList());
    }

    private List<Integer> outerSizes(List<Integer> layerSizes) {
        return IntStream.range(0, layerSizes.size())
                .filter(index -> index%2 == 0)
                .mapToObj(layerSizes::get)
                .collect(Collectors.toList());
    }

    private void feedForward(DoubleVector inputValues) {
        neuronLayers = new Matrix(neuronLayers.lengthStream()
                .mapToObj(index -> feedNext(inputValues, index))
                .collect(Collectors.toList()));
    }

    private DoubleVector feedNext(DoubleVector inputValues, int index) {
        if(isFirstLayer(index))
            return inputValues;
        else {
            index--;
            return calcNextNeuronLayer(neuronLayers.get(index), weightMats.get(index), biasLayers.get(index));
        }
    }

    private boolean isFirstLayer(int index) {
        return index == 0;
    }

    private DoubleVector calcNextNeuronLayer(DoubleVector inputLayer, Matrix weightsMat, DoubleVector inputLayerBiases) {
        return new DoubleVector(new DoubleVector(weightsMat.transpose().multiplyByVector(inputLayer).add(inputLayerBiases)).applyFunction(activationFunction::process));
    }
}
