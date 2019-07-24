package neuralNetworks.objects;

import java.util.ArrayDeque;
import java.util.Collection;

public class Matrix extends ArrayDeque<Vector> {

    private static final String ILLEGAL_VECTOR_EXCEPTION_MSG = "Vectors must be the same length for operations.";

    public Matrix(Collection collection) {
        this.addAll(collection);
    }

    public Vector mul(Vector v) {
        checkIfVectorValid(this, v);
        ArrayDeque<Double> newVals = this.stream()
                .mapToDouble(mv -> mv.mulObj(v).getSum())
                .collect(ArrayDeque::new, ArrayDeque::add, ArrayDeque::addAll);

        return new Vector(newVals);
    }

    private void checkIfVectorValid(Matrix m, Vector v) throws IllegalArgumentException{
        if(m.size() != v.size())
            throw new IllegalArgumentException(ILLEGAL_VECTOR_EXCEPTION_MSG);
    }
}
