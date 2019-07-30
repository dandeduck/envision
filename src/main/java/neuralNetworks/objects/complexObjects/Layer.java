package neuralNetworks.objects.complexObjects;

import dataTypes.Vector;
import neuralNetworks.objects.basicObjects.Neuron;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Layer {

    private final Vector<Neuron> neurons;
    private final double bias;

    public Layer(int size, double bias) {
        neurons = initNeurons(bias > 0.0 ? size+1 : size);
        this.bias = bias;
    }

    public Layer(int size, boolean hasBias) {
        this(size, hasBias ? 1.0 : 0.0);
    }

    public Layer(int size) {
        this(size, 1.0);
    }

    private Vector<Neuron> initNeurons(int size) {
        return IntStream.range(0, size)
                .mapToObj(n -> new Neuron())
                .collect(Collectors.toCollection(Vector::new));
    }

    public Vector<Neuron> getNeurons() {
        return neurons;
    }

    private void resetBias() {
        if(bias > 0.0) {
            neurons.remove(neurons.size() - 1);
            neurons.add(new Neuron(bias));
        }
    }

    public void updateLayer(Vector<Neuron> newValues) {
        System.out.println(toString());
        for (Object n: neurons) {
            System.out.println(n.getClass());
        }
        neurons.clear();
        neurons.addAll(newValues);
        for (Object n: neurons) {
            System.out.println(n.getClass());
        }
        System.out.println(toString());
        resetBias();
    }

    public Neuron getNeuron(int index) {
        return new Neuron(neurons.get(index));
    }

    public int size() {
        return neurons.size();
    }

    public int indexOf(Neuron neuron) {
        return neurons.indexOf(neuron);
    }

    @Override
    public String toString() {
        return neurons.toString();
    }
}
