package neuralNetworks.objects;

import dataTypes.Value;

public class Bias implements Value<Double> {

    private double bias;

    public Bias() {
        set(1.0);
    }

    @Override
    public void set(Double arg) {
        bias = arg;
    }

    @Override
    public Double get() {
        return bias;
    }

    @Override
    public Value sum(Double arg) {
        return bias + arg;
    }

    @Override
    public Value mul(Double arg) {
        return bias * arg;
    }

    @Override
    public Double sub(Double arg) {
        return bias - arg;
    }
}
