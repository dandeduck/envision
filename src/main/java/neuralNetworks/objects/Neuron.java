package neuralNetworks.objects;

public class Neuron {

    private double val;
    private double bias;
    private final Vector weights;

    public Neuron(double bias, Vector weights) {
        this.bias = bias;
        this.weights = weights;
    }

    public Neuron(Vector weights) {
        this(0, weights);
    }

    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
    }

    public Vector getWeights() {
        return weights;
    }

    public double getBias() {
        return bias;
    }
}
