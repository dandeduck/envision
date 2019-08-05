package dataTypes;

import java.util.List;
import java.util.Random;

public class DoubleVector extends Vector<Double>{

    private final Random rnd;

    public DoubleVector(int dimensions) {
        super();
        rnd = new Random();
        generateList(dimensions);
    }

    public DoubleVector(List<Double> list) {
        super(list);
        rnd = null;
    }

    public DoubleVector(int size, Double value) {
        super(size, value);
        rnd = null;
    }

    @Override
    protected Double generateValue(int index) {
        return rnd.nextDouble() * rnd.nextDouble();
    }

    @Override
    protected Double multiplyValues(Double v1, Double v2) {
        return v1 * v2;
    }

    @Override
    protected Double addValues(Double v1, Double v2) {
        return v1 + v2;
    }

    @Override
    protected Double subtractValues(Double v1, Double v2) {
        return v1 - v2;
    }

    public Double sum() {
        return lengthStream()
                .mapToDouble(this::get)
                .sum();
    }
}
