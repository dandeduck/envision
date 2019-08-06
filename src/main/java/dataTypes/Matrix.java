package dataTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Matrix extends Vector<DoubleVector> {

    private final List<Integer> vectorsDimensions;

    public Matrix() {
        super();

        vectorsDimensions = null;
    }

    public Matrix(List<DoubleVector> list) {
        super(list);

        vectorsDimensions = null;
    }

    public Matrix(int size, List<Integer> vectorsDimensions) {
        super();

        this.vectorsDimensions = new ArrayList<>(vectorsDimensions);
        generateList(size);
    }

    public Matrix(int matSize, int vectorSize) {
        super();

        vectorsDimensions = createSameList(matSize, vectorSize);
        generateList(matSize);
    }

    private List<Integer> createSameList(int size, int value) {
        return IntStream.range(0, size)
                .mapToObj(e -> value)
                .collect(Collectors.toList());
    }

    public Matrix multiply(Matrix matrix) {
        return new Matrix(super.multiply(matrix));
    }

    public Matrix add(Matrix matrix) {
        return new Matrix(super.add(matrix));
    }

    public Matrix subtract(Matrix matrix) {
        return new Matrix(super.subtract(matrix));
    }

    @Override
    protected DoubleVector generateValue(int index) {
        return new DoubleVector(vectorsDimensions.get(index));
    }

    @Override
    protected DoubleVector multiplyValues(DoubleVector v1, DoubleVector v2) {
        return v1.multiply(v2);
    }

    @Override
    protected DoubleVector addValues(DoubleVector v1, DoubleVector v2) {
        return v1.add(v2);
    }

    @Override
    protected DoubleVector subtractValues(DoubleVector v1, DoubleVector v2) {
        return v1.subtract(v2);
    }

    public DoubleVector multiplyByVector(DoubleVector vector) {
        return new DoubleVector(stream()
                .map(matVector -> matVector.multiply(vector).sum())
                .collect(Collectors.toList()));
    }

    public Matrix transpose() {
        return new Matrix(get(0).lengthStream()//won't work if not all vectors are the same lengths
                .mapToObj(innerIndex -> new DoubleVector(lengthStream()
                        .mapToObj(outerIndex -> get(outerIndex).get(innerIndex))
                        .collect(Collectors.toList())))
                .collect(Collectors.toList()));
    }

    public Matrix scale(Double scalar) {
        return new Matrix(stream()
                .map(vector -> vector.scale(scalar))
                .collect(Collectors.toList()));
    }

    public Matrix addVector(DoubleVector vector) {
        return new Matrix(super.addValue(vector));
    }
}
