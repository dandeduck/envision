package dataTypes;

public interface Value<T> {
    void set(T arg);
    T get();
    Value sum(T arg);
    Value mul(T arg);
    Value sub(T arg);
}
