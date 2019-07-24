package neuralNetworks.objects;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Layer {

    private final List<Neuron> neurons;

    public Layer(int size, int nextSize) {
        neurons = initNeurons(size, nextSize);
    }

    private List<Neuron> initNeurons(int size, int nextSize) {
        return IntStream.range(1, size)
                .mapToObj(n -> initNeuron(nextSize))
                .collect(Collectors.toList());
    }

    private Neuron initNeuron(int nextSize) {
        return new Neuron(getNeuronWeights(nextSize));
    }

    private Vector getNeuronWeights(int prevSize) {
        return new Vector(IntStream.range(1, prevSize)
                .mapToObj(w -> new Random().nextDouble())
                .collect(Collectors.toList()));
    }

    public Matrix getWeightsMat() {
        return new Matrix(neurons.stream()
                .map(n -> n.getWeights())
                .collect(Collectors.toList()));
    }

    public Vector getValues() {
        return new Vector(neurons.stream()
                .map(n -> n.getVal())
                .collect(Collectors.toList()));
    }

    public Vector getBiases() {
        return new Vector(neurons.stream()
                .map(n -> n.getBias())
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
