package neuralNetworks.objects.basicObjects;

import dataTypes.Value;

public class Bias implements Value<Double> {
    private double val;

    public Bias() {
        val = 1.0;
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
    public Bias sum(Double arg) {
        val += arg;
        return this;
    }

    @Override
    public Bias mul(Double arg) {
        val *= arg;
        return this;
    }

    @Override
    public Bias sub(Double arg) {
        val -= arg;
        return this;
    }
}
