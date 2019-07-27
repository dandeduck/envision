package neuralNetworks.objects;


import dataTypes.Matrix;
import dataTypes.Vector;
import neuralNetworks.algorithmics.ActivationFunction;
import neuralNetworks.constants.enums.ActivationFunctionTypes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Network {

    private final List<Layer> layers;
    private final ActivationFunction activationFunction;

    public Network(ActivationFunctionTypes functionType, Integer... layerSizes) {
        activationFunction = new ActivationFunction(functionType);
        layers = initLayers(Arrays.asList(layerSizes));
    }

    private List<Layer> initLayers(List<Integer> layerSizes) {
        return layerSizes.stream()
                .map(l -> new Layer(l))
                .collect(Collectors.toList());
    }

    private Matrix

    private void feedFarward(Vector input) {
        updateInputNeurons(input);
        layers.stream()
                .skip(1)
                .forEach(l -> feedNextLayer(getPrevLayer(layers,l),l));
    }

    private void updateInputNeurons(Vector input) {
        layers.get(0).updateLayer(input);
    }

    private Layer getPrevLayer(List<Layer> layers, Layer currentLayer) {
        return layers.get(layers.indexOf(currentLayer)-1);
    }


    private void feedNextLayer(Layer prevLayer, Layer nextLayer) {
        Matrix WeightsMat = prevLayer.getWeightsMat();
        Vector prevValues = prevLayer.getValues();
        Vector biases = nextLayer.getBiases();

        nextLayer.updateLayer(calcNextValues(WeightsMat, prevValues, biases));
    }

    private Vector calcNextValues(Matrix W, Vector a, Vector b) {
        return new Vector(new Vector(W.mul(a).sumObj(b)).stream()
                .map(v -> activationFunction.process(v))
                .collect(Collectors.toList()));
    }
}
