package dataTypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Matrix<V extends Value> extends Vector<Vector<V>> {
    public Matrix(Collection collection) {
        super(collection);
    }

    public Matrix() {
        super(new ArrayList<>());
    }

    public Matrix(int layerSize, int nextLayerSize, Supplier<V> constructor) {
        this();

    }

    private Matrix<V> generateMatrix(int layerSize, int nextLayerSize, Supplier<V> constructor) {
        return IntStream.range(0, nextLayerSize)
                .mapToObj(v -> new Vector<>(layerSize, constructor))
                .collect(Collectors.toCollection(Matrix::new));
    }

    public Vector<V> mulByVector(Vector<? extends Value> v) {
        return stream()
                .map(Vector::new)
                .map(wv -> wv.mul(v).getSum())
                .collect(Collectors.toCollection(Vector::new));
    }
}
