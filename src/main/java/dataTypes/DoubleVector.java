package dataTypes;

import java.util.List;
import java.util.Random;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public DoubleVector multiply(DoubleVector vector) {
        return new DoubleVector(super.multiply(vector));
    }

    public DoubleVector add(DoubleVector vector) {
        return new DoubleVector(super.add(vector));
    }

    public DoubleVector subtract(DoubleVector vector) {
        return new DoubleVector(super.subtract(vector));
    }

    public DoubleVector applyFunction(DoubleFunction<Double> func) {
        return new DoubleVector(super.applyFunction(aDouble -> func.apply(aDouble)));
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

    public DoubleVector scale(Double scalar) {
        return new DoubleVector(stream()
                .map(value -> multiplyValues(value, scalar))
                .collect(Collectors.toList()));
    }
}
