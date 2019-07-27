package dataTypes;

import java.util.ArrayDeque;
import java.util.Collection;

public class Vector extends ArrayDeque<Double> {

    private static final String ILLEGAL_VECTOR_EXCEPTION_MSG = "Vectors must be the same length for operations.";

    public Vector(Collection collection) {
        this.addAll(collection);
    }

    public void sum(Vector v) {
        checkIfVectorValid(this,v);
        v.stream()
                .forEach(e -> this.push(this.pop() + v.pop()));
    }

    public Vector sumObj(Vector v) {
        sum(v);

        return this;
    }

    public void mul(Vector v) {
        checkIfVectorValid(this,v);
        v.stream()
                .forEach(e -> this.push(this.pop() * v.pop()));
    }

    public Vector mulObj(Vector v) {
        mul(v);

        return this;
    }

    private void checkIfVectorValid(Vector v, Vector v1) throws IllegalArgumentException{
        if(v.size() != v1.size())
            throw new IllegalArgumentException(ILLEGAL_VECTOR_EXCEPTION_MSG);
    }

    public double getSum() {
        return this.stream()
                .mapToDouble(e->e)
                .sum();
    }
}
