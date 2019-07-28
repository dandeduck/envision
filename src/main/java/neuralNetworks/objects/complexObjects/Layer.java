package neuralNetworks.objects.complexObjects;

import dataTypes.Vector;
import neuralNetworks.objects.basicObjects.Neuron;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Layer {

    private final Vector<Neuron> neurons;

    public Layer(int size) {
        neurons = initNeurons(size+1);
    }

    private Vector<Neuron> initNeurons(int size) {
        return new Vector<Neuron>(IntStream.range(1, size)
                .mapToObj(n -> new Neuron())
                .collect(Collectors.toList()));
    }

    public Vector<Neuron> getValues() {
        return neurons;
    }

    private void resetBias() {
        neurons.removeLast();
        neurons.add(new Neuron(1.0));
    }

    public void updateLayer(Vector<Neuron> newValues) {
        neurons.clear();
        neurons.addAll(newValues);
        resetBias();
    }
}
