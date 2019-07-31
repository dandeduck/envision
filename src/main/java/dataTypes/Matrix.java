package dataTypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class Matrix<V> extends ArrayList<Vector<V>> {
    public Matrix(Collection collection) {
        super(collection);
    }

    public Vector<V> mulByVector(Vector<V> v) {
        return this.stream()
                .map(wv -> wv.mul(v).getSum())
                .collect(Collectors.toCollection(Vector::new));
    }
}
