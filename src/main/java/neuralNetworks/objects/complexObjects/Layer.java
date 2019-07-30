package neuralNetworks.objects.complexObjects;

import dataTypes.Vector;
import neuralNetworks.objects.basicObjects.Neuron;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Layer extends Vector<Neuron>{

    public Layer(int size) {
        this.addAll(initNeurons(size));
    }

    private Vector<Neuron> initNeurons(int size) {
        return IntStream.range(0, size)
                .mapToObj(n -> new Neuron())
                .collect(Collectors.toCollection(Vector::new));
    }

    public void updateLayer(Vector<Neuron> newValues) {
        this.clear();
        this.addAll(newValues);
    }
}
