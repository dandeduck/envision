package neuralNetworks.objects.complexObjects;

import dataTypes.Value;
import dataTypes.Vector;
import neuralNetworks.objects.basicObjects.Neuron;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Layer extends Vector<Neuron>{

    public Layer() {

    }

    public Layer(int size) {
        this.addAll(initNeurons(size));
    }

    private Vector<Neuron> initNeurons(int size) {
        return IntStream.range(0, size)
                .mapToObj(n -> new Neuron())
                .collect(Collectors.toCollection(Vector::new));
    }

    public void updateLayer(Layer newLayer) {
        this.clear();
        this.addAll(newLayer);
    }

    @Override
    public Neuron get(int index) {
        return new Neuron(super.get(index));
    }
}
