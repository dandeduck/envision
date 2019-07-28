package dataTypes;

import java.util.ArrayDeque;
import java.util.Collection;

public class Vector<V> extends ArrayDeque<Value> {

    private static final String ILLEGAL_VECTOR_EXCEPTION_MSG = "Vectors must be the same length for operations.";

    public Vector(Collection collection) {
        this.addAll(collection);
    }

    public Vector<V> sum(Vector<V> v) {
        Vector<V> tmp = new Vector<>(this);
        checkIfVectorValid(this,v);
        v.stream()
                .forEach(e -> tmp.push(tmp.pop().add(v.pop())));

        return tmp;
    }

    public Vector<V> mul(Vector<V> v) {
        Vector<V> tmp = new Vector<>(this);
        checkIfVectorValid(this,v);
        v.stream()
                .forEach(e -> tmp.push(tmp.pop().mul(v.pop())));

        return tmp;
    }

    private void checkIfVectorValid(Vector<V> v, Vector<V> v1) throws IllegalArgumentException{
        if(v.size() != v1.size())
            throw new IllegalArgumentException(ILLEGAL_VECTOR_EXCEPTION_MSG);
    }

    public void set(Vector<V> newVector) {
        this.clear();
        this.addAll(newVector);
    }
}
