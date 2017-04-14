package me.anitas.fields;

public interface Value<V> {

    V get();

    void set(V val);
}
