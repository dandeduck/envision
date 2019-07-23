package neuralNetwork.objects;

import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Layer {

    private final List<Neuron> neurons;

    public Layer(int size, int prevSize) {
        neurons = initNeurons(size, prevSize);
    }

    private List<Neuron> initNeurons(int size, int prevSize ) {
        return createEmptyStack(size).stream()
                .map(n -> new Neuron(createEmptyStack(prevSize).stream()
                        .map(w -> new Random().nextDouble())
                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    private List<?> createEmptyStack(int size) {
        Stack<?> stack = new Stack();

        IntStream.range(1, size)
                .forEach(i -> stack.add(null));
        return stack;
    }

    public List<List<Double>> getWeightsMat() {
        return neurons.stream()
                .map(n -> n.getWeights())
                .collect(Collectors.toList());
    }
}
