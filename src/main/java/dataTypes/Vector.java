package dataTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class Vector<V>{

    private final List<V> list;

    public Vector(int dimensions) {
        list = new ArrayList<>();
        list.addAll(generateValues(dimensions));
    }

    private List<V> generateValues(int dimensions) {
        return IntStream.range(0, dimensions)
                .mapToObj(this::generateValue)
                .collect(Collectors.toList());
    }

    abstract protected V generateValue(int index);

    public V get(int index) {
        return list.get(index);
    }

    public int dimensions() {
        return list.size();
    }

    public Stream<V> stream() {
        return list.stream();
    }

    public IntStream lengthStream() {
        return IntStream.range(0, dimensions());
    }

    public Vector<V> multiply(Vector<V> otherList) {
        return lengthStream()
                .mapToObj(index -> multiplyValues(get(index), otherList.get(index)))
                .collect(Collectors.toCollection(Vector::new));
    }

    public Vector<V> scale(V val) {
        return lengthStream()
                .mapToObj(index -> multiplyValues(get(index), val))
                .collect(Collectors.toCollection(Vector::new));
    }

    abstract protected V multiplyValues(V v1, V v2);

    public Vector<V> add(Vector<V> otherList) {
        return lengthStream()
                .mapToObj(index -> addValues(get(index), otherList.get(index)))
                .collect(Collectors.toCollection(Vector::new));
    }

    abstract protected V addValues(V v1, V v2);

    public Vector<V> subtract(Vector<V> otherList) {
        return lengthStream()
                .mapToObj(index -> subtractValues(get(index), otherList.get(index)))
                .collect(Collectors.toCollection(Vector::new));
    }

    abstract protected V subtractValues(V v1, V v2);
}
