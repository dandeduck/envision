package dataTypes;

import java.util.ArrayList;
import java.util.List;

public class MatrixVector extends Vector<Matrix> {

    private final List<Integer> matSizes;
    private final List<Integer> vectorSizes;

    public MatrixVector(List<Integer> matSizes, List<Integer> vectorSizes) {
        super();

        this.matSizes = new ArrayList<>(matSizes);
        this.vectorSizes = new ArrayList<>(vectorSizes);
        generateList(matSizes.size());
    }

    @Override
    protected Matrix generateValue(int index) {
        return new Matrix(matSizes.get(index), vectorSizes.get(index));
    }

    @Override
    protected Matrix multiplyValues(Matrix v1, Matrix v2) {
        return new Matrix(v1.multiply(v2));
    }

    @Override
    protected Matrix addValues(Matrix v1, Matrix v2) {
        return new Matrix(v1.add(v2));
    }

    @Override
    protected Matrix subtractValues(Matrix v1, Matrix v2) {
        return new Matrix(v1.subtract(v2));
    }
}
