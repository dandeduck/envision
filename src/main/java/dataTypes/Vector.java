package dataTypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Vector<V extends Value> extends ArrayList<Value> {
    private static final String ILLEGAL_VECTOR_EXCEPTION_MSG = "Vectors must be the same length for operations.";

    public Vector(Collection collection) {
        this.addAll(collection);
    }

    public Vector(int size, Value val) {
        createVector(size,val);
    }

    public Vector() { }

    private void createVector(int size, Value v) {
        IntStream.range(0, size)
                .forEach(e -> add(v));
    }

    public Vector<V> sum(Vector<V> v) {
        checkIfVectorValid(v);
        return IntStream.range(0, v.size())
                .mapToObj(e -> v.get(e).sum(get(e)))
                .collect(Collectors.toCollection(Vector::new));
    }

    public Vector<V> dot(Vector<V> v) {
        checkIfVectorValid(v);
        return IntStream.range(0, v.size())
                .mapToObj(e -> v.get(e).mul(get(e)))
                .collect(Collectors.toCollection(Vector::new));
    }

    public Vector<V> sub(Vector<V> v) {
        checkIfVectorValid(v);
        return IntStream.range(0, v.size())
                .mapToObj(e -> v.get(e).sub(get(e)))
                .collect(Collectors.toCollection(Vector::new));
    }

    public Vector<V> scale(Value val) {
        return this.stream()
                .map(e -> e.mul(val))
                .collect(Collectors.toCollection(Vector::new));
    }

    public Vector<V> applyFunc(Function<Value, Value> func) {
        return this.stream()
                .map(func)
                .collect(Collectors.toCollection(Vector::new));
    }

    private void checkIfVectorValid(Vector<V> v1) throws IllegalArgumentException{
        if(this.size() != v1.size())
            throw new IllegalArgumentException(ILLEGAL_VECTOR_EXCEPTION_MSG);
    }

    public Value getSum() {
        return new Value(this.stream()
                .mapToDouble(v -> v.get())
                .sum());
    }
}
