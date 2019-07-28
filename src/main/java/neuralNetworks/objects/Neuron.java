package neuralNetworks.objects;

import dataTypes.Value;

public class Neuron implements Value<Double> {

    private double val;

    public Neuron(double val) {
        set(val);
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
    public Value sum(Double arg) {
        set(val + arg);
        return this;
    }

    @Override
    public Value mul(Double arg) {
        set(val * arg);
        return this;
    }

    @Override
    public Value sub(Double arg) {
        set(val - arg);
        return this;
    }
}
