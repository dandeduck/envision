package dataTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Matrix extends Vector<DoubleVector> {

    private final List<Integer> vectorsDimensions;

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
        super(matSize, new DoubleVector(vectorSize));

        vectorsDimensions = null;
    }

    @Override
    protected DoubleVector generateValue(int index) {
        return new DoubleVector(vectorsDimensions.get(index));
    }

    @Override
    protected DoubleVector multiplyValues(DoubleVector v1, DoubleVector v2) {
        return new DoubleVector(v1.multiply(v2));
    }

    @Override
    protected DoubleVector addValues(DoubleVector v1, DoubleVector v2) {
        return new DoubleVector(v1.add(v2));
    }

    @Override
    protected DoubleVector subtractValues(DoubleVector v1, DoubleVector v2) {
        return new DoubleVector(v1.subtract(v2));
    }

    public DoubleVector multiplyByVector(DoubleVector vector) {
        return new DoubleVector(stream()
                .map(matVector -> new DoubleVector(matVector.multiply(vector)).sum())
                .collect(Collectors.toList()));
    }

    public Matrix transpose() {
        return new Matrix(get(0).lengthStream()//won't work if not all vectors are the same lengths
                .mapToObj(innerIndex -> new DoubleVector(lengthStream()
                        .mapToObj(outerIndex -> get(outerIndex).get(innerIndex))
                        .collect(Collectors.toList())))
                .collect(Collectors.toList()));
    }
}
