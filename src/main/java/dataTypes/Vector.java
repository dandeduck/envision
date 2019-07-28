package dataTypes;

import java.util.ArrayDeque;
import java.util.Collection;

public class Vector<V> extends ArrayDeque<Value<V>> implements Value<Vector<V>>{

    private static final String ILLEGAL_VECTOR_EXCEPTION_MSG = "Vectors must be the same length for operations.";

    public Vector(Collection collection) {
        this.addAll(collection);
    }

    @Override
    public Vector<V> sum(Vector<V> v) {
        Vector<V> tmp = new Vector<>(this);
        checkIfVectorValid(this,v);
        v.stream()
                .forEach(e -> tmp.push(tmp.pop().sum(v.pop().get())));

        return tmp;
    }

    @Override
    public Vector<V> mul(Vector<V> v) {
        Vector<V> tmp = new Vector<>(this);
        checkIfVectorValid(this,v);
        v.stream()
                .forEach(e -> tmp.push(tmp.pop().mul(v.pop().get())));

        return tmp;
    }

    @Deprecated
    @Override
    public Value sub(Vector<V> arg) {
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

    public Value<V> getSum() {
        Value<V> sum = this.getFirst();

        this.stream()
                .forEach(v -> sum.set((V) sum.sum(v.get()).get()));//OK?

        return sum;
    }
}
