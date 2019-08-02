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

    public Matrix(int inputSize, int outputSize, Supplier<V> constructor) {
        this();
        addAll(generateMatrix(inputSize, outputSize, constructor));
    }

    private Matrix<V> generateMatrix(int inputSize, int outputSize, Supplier<V> constructor) {
        return IntStream.range(0, inputSize)
                .mapToObj(v -> new Vector<>(outputSize, constructor))
                .collect(Collectors.toCollection(Matrix::new));
    }

    public Vector<V> mulByVector(Vector<? extends Value> v) {
        return stream()
                .map(Vector::new)
                .map(wv -> wv.mul(v).getSum())
                .collect(Collectors.toCollection(Vector::new));
    }

    public Matrix<V> transpose() {
        return IntStream.range(0, get(0).size())
                .mapToObj(i ->IntStream.range(0, size())
                        .mapToObj(j -> get(j).get(i))
                        .collect(Collectors.toCollection(Vector::new)))
                .collect(Collectors.toCollection(Matrix::new));
    }
}
