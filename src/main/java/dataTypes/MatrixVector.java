package dataTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MatrixVector extends Vector<Matrix> {

    private final List<Integer> matSizes;
    private final List<Integer> vectorSizes;

    public MatrixVector() {
        super();

        matSizes = null;
        vectorSizes = null;
    }

    public MatrixVector(List<Integer> matSizes, List<Integer> vectorSizes) {
        super();

        this.matSizes = new ArrayList<>(matSizes);
        this.vectorSizes = new ArrayList<>(vectorSizes);
        generateList(matSizes.size());
    }

    public MatrixVector(List<Matrix> list) {
        super(list);

        matSizes = null;
        vectorSizes = null;
    }

    public MatrixVector multiply(MatrixVector vector) {
        return new MatrixVector(super.multiply(vector));
    }

    public MatrixVector add(MatrixVector vector) {
        return new MatrixVector(super.add(vector));
    }

    public MatrixVector subtract(MatrixVector vector) {
        return new MatrixVector(super.subtract(vector));
    }

    @Override
    protected Matrix generateValue(int index) {
        return new Matrix(matSizes.get(index), vectorSizes.get(index));
    }

    @Override
    protected Matrix multiplyValues(Matrix v1, Matrix v2) {
        return v1.multiply(v2);
    }

    @Override
    protected Matrix addValues(Matrix v1, Matrix v2) {
        return v1.add(v2);
    }

    @Override
    protected Matrix subtractValues(Matrix v1, Matrix v2) {
        return v1.subtract(v2);
    }

    public MatrixVector scale(Double scalar) {
        return new MatrixVector(stream()
                .map(matrix -> matrix.scale(scalar))
                .collect(Collectors.toList()));
    }

    public MatrixVector addMatrix(Matrix matrix) {
        return new MatrixVector(super.addValue(matrix));
    }
}
