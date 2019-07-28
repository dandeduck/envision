package neuralNetworks.objects;

import dataTypes.Value;

public class Weight implements Value<Double> {

    private double weight;

    public Weight() {
        set(1.0);
    }

    @Override
    public void set(Double arg) {
        weight = arg;
    }

    @Override
    public Double get() {
        return weight;
    }

    @Override
    public Value add(Double arg) {
        return weight + arg;
    }

    @Override
    public Value mul(Double arg) {
        return weight * arg;
    }

    @Override
    public Double sub(Double arg) {
        return weight - arg;
    }
}
