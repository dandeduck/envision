package neuralNetworks.objects;

import dataTypes.Vector;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Layer {

    private final List<Neuron> neurons;

    public Layer(int size) {
        neurons = initNeurons(size);
    }

    private List<Neuron> initNeurons(int size) {
        return IntStream.range(1, size)
                .mapToObj(n -> new Neuron())
                .collect(Collectors.toList());
    }

    public Vector getValues() {
        return new Vector(neurons.stream()
                .map(n -> n.getVal())
                .collect(Collectors.toList()));
    }

    public int getLayerSize() {
        return neurons.size();
    }

    public void updateLayer(Vector newValues) {
        IntStream.range(1, newValues.size())
                .forEach(i -> neurons.get(i).setVal(newValues.pop()));
    }
}
