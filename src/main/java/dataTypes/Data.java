package dataTypes;

import neuralNetworks.objects.basicObjects.Neuron;

import java.util.*;
import java.util.stream.Collectors;

public class Data {

    private static final String ILLEGAL_DATA_MSG = "There must be the same number of input and output data points";

    private List<Double> input;
    private List<Double> output;

    public Data(List<Double> input, List<Double> output) {
        this.input = input;
        this.output = output;

        checkIfDataValid(input,output);
    }

    public Data(int inputSize, int outputSize) {
        this(new ArrayList<>(inputSize),new ArrayList<>(outputSize));
    }


    public double getOutputPoint(int index) {
        return output.get(index);
    }

    public void addOutput(double outputPoint) {
        output.add(outputPoint);
    }

    public void addInputs(List<Double> inputs) {
        input.addAll(inputs);
    }

    public void addInputs(Double... inputs) {
        addInputs(Arrays.asList(inputs));
    }

    private void checkIfDataValid(List input, List output) throws IllegalArgumentException {
        if(input.size() != output.size())
            throw new IllegalArgumentException(ILLEGAL_DATA_MSG);
    }

    public Vector<Neuron> getInputPointsAsNeurons() {
        return input.stream()
                .map(Neuron::new)
                .collect(Collectors.toCollection(Vector::new));

    }

    public Vector<Neuron> getOutputPointsAsNeurons() {
        return output.stream()
                .map(Neuron::new)
                .collect(Collectors.toCollection(Vector::new));
    }

    @Override
    public String toString() {
        return String.format("input: %s ,output: %s",input,output);
    }
}
