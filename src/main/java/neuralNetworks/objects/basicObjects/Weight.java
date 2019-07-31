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
        return new Weight(weight + arg);
    }

    @Override
    public Weight mul(Double arg) {
        return new Weight(weight * arg);
    }

    @Override
    public Weight sub(Double arg) {
        return new Weight(weight - arg);
    }

    @Override
    public boolean equals(Object obj) {
        return weight == ((Value<Double>) obj).get();
    }

    @Override
    public String toString() {
        return Double.toString(weight);
    }
}
