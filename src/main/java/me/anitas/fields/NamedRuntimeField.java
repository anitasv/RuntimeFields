package me.anitas.fields;

public class NamedRuntimeField<A, V> extends RuntimeField<A, V> {

    private final String name;

    public NamedRuntimeField(String name, int loc) {
        super(loc);
        this.name = name;
    }

    public String fmt(V val) {
        return name + "{" + val + "}";
    }

    public String toString() {
        return name;
    }
}
