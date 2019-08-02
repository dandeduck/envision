package dataTypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Vector<V extends Value> extends ArrayList<Value> implements Value<Vector<? extends Value>>{
    private static final String ILLEGAL_VECTOR_EXCEPTION_MSG = "Vectors must be the same length for operations.";

    public Vector(Collection collection) {
        addAll(collection);
    }

    public Vector() {
        this(new ArrayList());
    }

    public Vector(int size, Supplier<V> constructor) {
        this();
        addAll(generateVector(size, constructor));
    }

    public Vector(Value<? extends ArrayList<Value>> value) {
        this(value.get());
    }

    private List<V> generateVector(int size, Supplier<V> constructor) {
        return IntStream.range(0, size)
                .mapToObj(e -> constructor.get())
                .collect(Collectors.toList());
    }

    public Vector(int size, V val) {
        this();
        IntStream.range(0, size)
                .forEach(e -> add(val));
    }

    @Override
    public void set(Vector<? extends Value> arg) {
        this.clear();
        this.addAll(arg);
    }

    @Override
    public Vector<V> get() {
        return this;
    }

    @Override
    public Vector<V> sum(Vector<? extends Value> v) {
        checkIfVectorValid(v);
        return IntStream.range(0, v.size())
                .mapToObj(e -> v.get(e).sum(get(e)))
                .collect(Collectors.toCollection(Vector::new));
    }

    @Override
    public Vector<V> mul(Vector<? extends Value> v) {//just dot
        checkIfVectorValid(v);
        return IntStream.range(0, v.size())
                .mapToObj(e -> v.get(e).mul(get(e)))
                .collect(Collectors.toCollection(Vector::new));
    }

    @Override
    public Vector<V> sub(Vector<? extends Value> v) {
        checkIfVectorValid(v);
        return IntStream.range(0, v.size())
                .mapToObj(e -> v.get(e).sub(get(e)))
                .collect(Collectors.toCollection(Vector::new));
    }

    public Vector<V> scale(Value val) {
        return stream()
                .map(e -> e.mul(val))
                .collect(Collectors.toCollection(Vector::new));
    }

    public Vector<V> applyFunc(Function<Value, Value> func) {
        return stream()
                .map(func)
                .collect(Collectors.toCollection(Vector::new));
    }

    private void checkIfVectorValid(Vector<? extends Value> v) throws IllegalArgumentException{
        if(this.size() != v.size())
            throw new IllegalArgumentException(ILLEGAL_VECTOR_EXCEPTION_MSG);
    }

    public Value getSum() {
        Value sum = get(0);

        stream()
                .skip(1)
                .forEach(v -> sum.set(sum.sum((Value) v.get()).get()));

        return sum;
    }

    @Override
    public V get(int index) {
        return (V) super.get(index);
    }
}
