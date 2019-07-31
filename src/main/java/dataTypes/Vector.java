package dataTypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Vector<V> extends ArrayList<Value> implements Value<Vector<V>>{
    private static final String ILLEGAL_VECTOR_EXCEPTION_MSG = "Vectors must be the same length for operations.";

    public Vector(Collection collection) {
        this.addAll(collection);
    }

    public Vector() {

    }

    @Override
    public Vector<V> sum(Vector<V> v) {
        checkIfVectorValid(this,v);
        return IntStream.range(0, v.size())
                .mapToObj(e -> v.get(e).sum(this.get(e).get()))
                .collect(Collectors.toCollection(Vector::new));
    }

    @Override
    public Vector<V> mul(Vector<V> v) {
        checkIfVectorValid(this,v);
        return IntStream.range(0, v.size())
                .mapToObj(e -> v.get(e).mul(this.get(e).get()))
                .collect(Collectors.toCollection(Vector::new));
    }

    @Deprecated
    @Override
    public Vector<V> sub(Vector<V> arg) {
        return null;
    }

    private void checkIfVectorValid(Vector<V> v, Vector<V> v1) throws IllegalArgumentException{
        if(v.size() != v1.size())
            throw new IllegalArgumentException(ILLEGAL_VECTOR_EXCEPTION_MSG);
    }

    @Override
    public void set(Vector<V> newVector) {
        this.clear();
        this.addAll(newVector);
    }

    @Override
    public Vector<V> get() {
        return this;
    }

    public Value getSum() {
        Value sum = this.get(0);

        this.stream()
                .skip(1)
                .forEach(v -> sum.set(sum.sum(v.get()).get()));

        return sum;
    }
}
