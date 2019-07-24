package neuralNetworks.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Network {

    private List<Layer> layers;

    public Network(List<Integer> layerSizes) {
        layers = new ArrayList<>();

        layers = layerSizes.stream()
                .skip(1)
                .map(l -> new Layer(layerSizes.get(layerSizes.indexOf(l.intValue())-1),l))
                .collect(Collectors.toList());
        layers.remove(0);
    }

    private void feedFarward(Vector input) {
        updateInputNeurons(input);
        layers.stream()
                .skip(1)
                .forEach(l -> feedNextLayer(l));
    }

    private void updateInputNeurons(Vector input) {
        layers.get(0).updateLayer(input);
    }

    private void feedNextLayer(Layer prevLayer) {
        Matrix WeightsMat = prevLayer.getWeightsMat();
        Vector prevValues = prevLayer.getValues();
        Vector biases = prevLayer.getBiases();

        int nextLayerIndex = layers.indexOf(prevLayer)+1;
        if(nextLayerIndex == layers.size())
            return;

        Layer currLayer = layers.get(nextLayerIndex);
        currLayer.updateLayer(calcNextValues(WeightsMat, prevValues, biases));
    }

    private Vector calcNextValues(Matrix W, Vector a, Vector b) {
        return new Vector(W.mul(a).sumObj(b));
    }
}
