package neuralNetworks.objects.basicObjects;

import dataTypes.Value;

public class Neuron implements Value<Double> {

    private double val;

    public Neuron(double val) {
        set(val);
    }

    public Neuron(Value<Double> val) {
        this(val.get());
    }

    public Neuron() {
        this(0);
    }

    @Override
    public void set(Double arg) {
        val = arg;
    }

    @Override
    public Double get() {
        return val;
    }

    @Override
    public Neuron sum(Double arg) {
        return new Neuron(val + arg);
    }

    @Override
    public Neuron mul(Double arg) {
        return new Neuron(val * arg);
    }

    @Override
    public Neuron sub(Double arg) {
        return new Neuron(val - arg);
    }

    @Override
    public boolean equals(Object obj) {
        return val == ((Value<Double>) obj).get();
    }

    @Override
    public String toString() {
        return Double.toString(val);
    }
}
