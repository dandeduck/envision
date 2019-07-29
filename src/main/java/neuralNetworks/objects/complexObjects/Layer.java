package neuralNetworks.objects.complexObjects;

import dataTypes.Vector;
import neuralNetworks.objects.basicObjects.Neuron;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Layer {

    private final Vector<Neuron> neurons;
    private final double bias;

    public Layer(int size, double bias) {
        neurons = initNeurons(size+1);
        this.bias = bias;
    }

    public Layer(int size, boolean hasBias) {
        this(size, hasBias ? 1.0 : 0.0);
    }

    public Layer(int size) {
        this(size, 1.0);
    }

    private Vector<Neuron> initNeurons(int size) {
        return new Vector<>(IntStream.range(1, size)
                .mapToObj(n -> new Neuron())
                .collect(Collectors.toList()));
    }

    public Vector<Neuron> getNeurons() {
        return neurons;
    }

    private void resetBias() {
        neurons.remove(-1);
        neurons.add(new Neuron(bias));
    }

    public void updateLayer(Vector<Neuron> newValues) {
        neurons.clear();
        neurons.addAll(newValues);
        resetBias();
    }

    public Neuron getNeuron(int index) {
        return new Neuron(neurons.get(index));
    }

    public int indexOf(Neuron neuron) {
        return neurons.indexOf(neuron);
    }
}
