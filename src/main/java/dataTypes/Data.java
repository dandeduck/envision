package dataTypes;

import neuralNetworks.objects.basicObjects.Neuron;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public Vector<NumberValue> getInput() {
        return input.stream()
                .map(NumberValue::new)
                .collect(Collectors.toCollection(Vector::new));

    }

    public Vector<NumberValue> getOutput() {
        return output.stream()
                .map(NumberValue::new)
                .collect(Collectors.toCollection(Vector::new));
    }

    @Override
    public String toString() {
        return String.format("input: %s ,output: %s",input,output);
    }
}
