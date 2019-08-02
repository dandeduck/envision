package dataTypes;

public class NumberValue implements Value<Double> {

    double val;

    public NumberValue(double val) {
        set(val);
    }

    public NumberValue(Value<? extends Double> val) {
        set(val.get());
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
    public NumberValue sum(Double arg) {
        return new NumberValue(val + arg);
    }

    @Override
    public NumberValue mul(Double arg) {
        return new NumberValue(val * arg);
    }

    @Override
    public NumberValue sub(Double arg) {
        return new NumberValue(val - arg);
    }

    @Override
    public boolean equals(Object obj) {
        return val == ((NumberValue) obj).get();
    }

    @Override
    public String toString() {
        return Double.toString(val);
    }
}
