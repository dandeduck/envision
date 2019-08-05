package neuralNetworks.objects;


import dataTypes.DoubleVector;

import java.util.List;

public class NetworkPattern {

    private final DoubleVector input;
    private final DoubleVector output;

    public NetworkPattern(List<Double> input, List<Double> output) {
        this.input = new DoubleVector(input);
        this.output = new DoubleVector(output);
    }

    public DoubleVector getInput() {
        return input;
    }

    public DoubleVector getOutput() {
        return output;
    }
}
