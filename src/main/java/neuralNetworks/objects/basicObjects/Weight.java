package neuralNetworks.objects.basicObjects;

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

    public Weight(Value<Double> value) {
        this(value.get());
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
    public Weight sum(Double arg) {
        set(weight + arg);
        return this;
    }

    @Override
    public Weight mul(Double arg) {
        set(weight * arg);
        return this;
    }

    @Override
    public Weight sub(Double arg) {
        set(weight - arg);
        return this;
    }
}
