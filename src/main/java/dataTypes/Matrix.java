package dataTypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class Matrix<V extends Value> extends ArrayList<Vector<V>> {
    public Matrix(Collection collection) {
        super(collection);
    }

    public Matrix() {
        super(new ArrayList<>());
    }

    public Vector<V> mulByVector(Vector<V> v) {
        return this.stream()
                .map(wv -> wv.dot(v).getSum())
                .collect(Collectors.toCollection(Vector::new));
    }
}
