package dataTypes;

import neuralNetworks.objects.basicObjects.Neuron;

public class Value {

    double val;

    public Value(double val) {
        set(val);
    }

    public Value(Value val) {
        set(val.get());
    }

    public void set(Double arg) {
        val = arg;
    }

    public Double get() {
        return val;
    }

    public Value sum(Value arg) {
        return new Value(val + arg.get());
    }

    public Value mul(Value arg) {
        return new Value(val * arg.get());
    }

    public Value sub(Value arg) {
        return new Value(val - arg.get());
    }

    @Override
    public boolean equals(Object obj) {
        return val == ((Value) obj).get();
    }

    @Override
    public String toString() {
        return Double.toString(val);
    }
}
