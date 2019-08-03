package dataTypes;

import java.util.Random;

public class DoubleVector extends Vector<Double> {
    public DoubleVector(int dimensions) {
        super(dimensions);
    }

    @Override
    protected Double generateValue(int index) {
        Random rnd = new Random();
        return rnd.nextDouble() - rnd.nextDouble();
    }

    @Override
    protected Double multiplyValues(Double v1, Double v2) {
        return v1*v2;
    }

    @Override
    protected Double addValues(Double v1, Double v2) {
        return v1+v2;
    }

    @Override
    protected Double subtractValues(Double v1, Double v2) {
        return v1-v2;
    }
}
