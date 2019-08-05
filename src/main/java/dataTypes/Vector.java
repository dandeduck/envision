package dataTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class Vector<V>{

    private final List<V> list;

    public Vector() {
        list = new ArrayList<>();
    }

    public Vector(List<V> list) {
        this();
        list.addAll(list);
    }

    public Vector(int dimensions, V value) {
        this();
        list.addAll(generateSameList(dimensions, value));
    }

    private List<V> generateSameList(int dimensions, V value) {
        return IntStream.range(0, dimensions)
                .mapToObj(e -> value)
                .collect(Collectors.toList());
    }

    protected List<V> generateList(int dimensions) {
        return IntStream.range(0, dimensions)
                .mapToObj(this::generateValue)
                .collect(Collectors.toList());
    }

    protected abstract V generateValue(int index);

    public V get(int index) {
        return list.get(index);
    }

    public Stream<V> stream() {
        return list.stream();
    }

    public IntStream lengthStream() {
        return IntStream.range(0, list.size());
    }
    public int dimensions() {
        return list.size();
    }

    public List<V> multiply(Vector<V> list) {
        return lengthStream()
                .mapToObj(index -> multiplyValues(get(index), list.get(index)))
                .collect(Collectors.toList());
    }

    protected abstract V multiplyValues(V v1, V v2);

    public List<V> scale(V scalar) {
        return stream()
                .map(value -> multiplyValues(value, scalar))
                .collect(Collectors.toList());
    }


    public List<V> add(Vector<V> list) {
        return lengthStream()
                .mapToObj(index -> addValues(get(index), list.get(index)))
                .collect(Collectors.toList());
    }

    protected abstract V addValues(V v1, V v2);

    public List<V> subtract(Vector<V> list) {
        return lengthStream()
                .mapToObj(index -> subtractValues(get(index), list.get(index)))
                .collect(Collectors.toList());
    }

    protected abstract V subtractValues(V v1, V v2);

    public List<V> applyFunction(Function<V,V> func) {
        return stream()
                .map(func)
                .collect(Collectors.toList());
    }
}
