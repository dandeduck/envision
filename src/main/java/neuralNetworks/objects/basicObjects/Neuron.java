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
        set(val + arg);
        return this;
    }

    @Override
    public Neuron mul(Double arg) {
        set(val * arg);
        return this;
    }

    @Override
    public Neuron sub(Double arg) {
        set(val - arg);
        return this;
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
