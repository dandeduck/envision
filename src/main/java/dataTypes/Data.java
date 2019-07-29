package dataTypes;

import java.util.ArrayList;
import java.util.List;

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

    public double getInputPoint(int index) {
        return input.get(index);
    }

    public double getOutputPoint(int index) {
        return output.get(index);
    }

    public void addDataPoint(double inputPoint, double outputPoint) {
        input.add(inputPoint);
        output.add(outputPoint);
    }

    private void checkIfDataValid(List input, List output) throws IllegalArgumentException {
        if(input.size() != output.size())
            throw new IllegalArgumentException(ILLEGAL_DATA_MSG);
    }

    public List<Double> getInputPoints() {
        return input;
    }

    public List<Double> getOutputPoints() {
        return output;
    }
}
