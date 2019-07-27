package neuralNetworks.objects;


import dataTypes.Matrix;
import dataTypes.Vector;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Network {

    private List<Layer> layers;

    public Network(Integer... layerSizes) {
        layers = initLayers(Arrays.asList(layerSizes));
    }

    private List<Layer> initLayers(List<Integer> layerSizes) {
        List<Layer> tmp = layerSizes.stream()
                .skip(1)
                .map(l -> new Layer(getPrevInteger(layerSizes,l),l))
                .collect(Collectors.toList());
        tmp.add(new Layer(layerSizes.get(-1),0));

        return tmp;
    }

    private void feedFarward(Vector input) {
        updateInputNeurons(input);
        layers.stream()
                .skip(1)
                .forEach(l -> feedNextLayer(getPrevLayer(layers,l),l));
    }

    private void updateInputNeurons(Vector input) {
        layers.get(0).updateLayer(input);
    }

    private Integer getPrevInteger(List<Integer> list, Integer currentInteger) {
        return list.get(list.indexOf(currentInteger)-1);
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
        return new Vector(W.mul(a).sumObj(b));
    }
}
