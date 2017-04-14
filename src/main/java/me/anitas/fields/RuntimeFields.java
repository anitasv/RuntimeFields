package me.anitas.fields;

import java.util.concurrent.atomic.AtomicInteger;

public class RuntimeFields<A> {

    private final AtomicInteger index = new AtomicInteger();

    public <V> RuntimeField<A, V> newField() {
        return new RuntimeField<A, V>(index.getAndIncrement());
    }

    public <V> RuntimeField<A, V> newField(String name) {
        return new NamedRuntimeField<A, V>(name, index.getAndIncrement());
    }
}
