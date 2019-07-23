package neuralNetwork.objects;

import java.util.List;

public class Neuron {

    private double val;
    private double bias;
    private final List<Double> weights;

    public Neuron(double bias, List<Double> weights) {
        this.bias = bias;
        this.weights = weights;
    }

    public Neuron(List<Double> weights) {
        this(0, weights);
    }

    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
    }

    public List<Double> getWeights() {
        return weights;
    }
}
