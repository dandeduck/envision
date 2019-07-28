package neuralNetworks.objects;

import dataTypes.Value;

public class Neuron implements Value<Double> {

    private double val;

    public Neuron() {
        set(val);
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
    public Value add(Double arg) {
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
