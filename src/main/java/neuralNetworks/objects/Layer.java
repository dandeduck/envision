package neuralNetworks.objects;

import dataTypes.Vector;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Layer {

    private final Vector<Neuron> neurons;

    public Layer(int size) {
        neurons = initNeurons(size);
    }

    private Vector<Neuron> initNeurons(int size) {
        return new Vector<Neuron>(IntStream.range(1, size)
                .mapToObj(n -> new Neuron())
                .collect(Collectors.toList()));
    }

    public Vector<Neuron> getValues() {
        return neurons;
    }

    public int getLayerSize() {
        return neurons.size();
    }

    public void updateLayer(Vector<Neuron> newValues) {
        neurons.clear();
        neurons.addAll(newValues);
    }
}
