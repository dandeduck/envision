package neuralNetworks.algorithmics;

import neuralNetworks.objects.Layer;

import java.util.ArrayList;
import java.util.List;

public class BackPropagation implements TrainingAlgorithm {

    private final double learningRate;

    public BackPropagation(double learningRate) {
        this.learningRate = learningRate;
    }

    @Override
    public Layer getCurrectLayer(Layer resultedLayer, Layer expectedLayer) {
        List<Double> errors = new ArrayList<>();

        return null;
    }

    private List<Double> calcErrors(Layer resultedLayer, Layer expectedLayer) {
        return null;
    }
}
