package neuralNetworks.objects;

import dataTypes.Value;

import java.util.Random;

public class Weight implements Value<Double> {

    private double weight;

    public Weight(double weight) {
        this.weight = weight;
    }

    public Weight() {
        this(new Random().nextDouble() - new Random().nextDouble());
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
    public Value sum(Double arg) {
        set(weight + arg);
        return this;
    }

    @Override
    public Value mul(Double arg) {
        set(weight * arg);
        return this;
    }

    @Override
    public Value sub(Double arg) {
        set(weight - arg);
        return this;
    }
}
