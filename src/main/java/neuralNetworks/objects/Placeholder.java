package neuralNetworks.objects;

import dataTypes.DoubleVector;
import dataTypes.Vector;
import neuralNetworks.algorithmics.ActivationFunction;
import neuralNetworks.constants.enums.ActivationType;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Placeholder extends Vector<Layer> {

    private final ActivationFunction activationFunction;
    private final List<Layer> layers;

    public Placeholder(List<Layer> layers, ActivationFunction activationFunction) {
        this.layers = layers;
        this.activationFunction = activationFunction;
    }

    public Placeholder(ActivationType activationType, List<Integer> layerSizes) {
        activationFunction = new ActivationFunction(activationType);
        layers = initLayers(layerSizes);
    }

    private List<Layer> initLayers(List<Integer> layerSizes) {
        return IntStream.range(0, layerSizes.size())
                .skip(1)
                .mapToObj(index -> new Layer(layerSizes.get(index-1), layerSizes.get(index)))
                .collect(Collectors.toList());
    }

    public Placeholder feedForward(DoubleVector input) {
        List<Layer> fedLayers = IntStream.range(0, layers.size())
                .mapToObj(index -> index != 0 ?
                        layers.get(index).activate(layers.get(index-1).getNeurons(), activationFunction) :
                        layers.get(index).activate(input, activationFunction))
                .collect(Collectors.toList());
        return new Placeholder(fedLayers, activationFunction);
    }

    @Override
    protected Layer multiplyValues(Layer v1, Layer v2) {
        return v1.mul(v2);
    }

    @Override
    protected Layer addValues(Layer v1, Layer v2) {
        return v1.add(v2);
    }

    @Override
    protected Layer subtractValues(Layer v1, Layer v2) {
        return v1.descent(v2);
    }

    public Placeholder scale(double scalar) {
        List<Layer> scaledLayers = layers.stream()
                .map(layer -> layer.scale(scalar))
                .collect(Collectors.toList());
        return new Placeholder(scaledLayers, activationFunction);
    }

    public Placeholder add(Placeholder placeholder) {
        return new Placeholder(super.add(placeholder), activationFunction);
    }

    public Placeholder multiply(Placeholder placeholder) {
        return new Placeholder(super.multiply(placeholder), activationFunction);
    }

    public Placeholder subtract(Placeholder placeholder) {
        return new Placeholder(super.subtract(placeholder), activationFunction);
    }
}
