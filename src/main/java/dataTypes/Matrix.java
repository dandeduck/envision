package dataTypes;

public class Matrix extends Vector<DoubleVector>{
    public Matrix(int dimensions) {
        super(dimensions);
    }

    @Override
    protected DoubleVector generateValue(int index) {
        return null;
    }

    @Override
    protected DoubleVector multiplyValues(DoubleVector v1, DoubleVector v2) {
        return null;
    }

    @Override
    protected DoubleVector addValues(DoubleVector v1, DoubleVector v2) {
        return null;
    }

    @Override
    protected DoubleVector subtractValues(DoubleVector v1, DoubleVector v2) {
        return v1.multiply(v2);
    }
}
