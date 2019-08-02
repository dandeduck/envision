package dataTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class Vector<V>{

    private final List<V> list;

    public Vector() {
        list = new ArrayList<>();
    }

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

    public List<V> multiply(List<V> otherList) {
        return lengthStream()
                .mapToObj(index -> multiplyValues(get(index), otherList.get(index)))
                .collect(Collectors.toList());
    }

    public List<V> scale(V val) {
        return lengthStream()
                .mapToObj(index -> multiplyValues(get(index), val))
                .collect(Collectors.toList());
    }

    abstract protected V multiplyValues(V v1, V v2);

    public List<V> add(List<V> otherList) {
        return lengthStream()
                .mapToObj(index -> addValues(get(index), otherList.get(index)))
                .collect(Collectors.toList());
    }

    abstract protected V addValues(V v1, V v2);

    public List<V> subtract(List<V> otherList) {
        return lengthStream()
                .mapToObj(index -> subtractValues(get(index), otherList.get(index)))
                .collect(Collectors.toList());
    }

    abstract protected V subtractValues(V v1, V v2);
}
