package me.anitas.fields;

public class RuntimeField<A, V>  {

    private final int loc;

    public RuntimeField(int loc) {
        this.loc = loc;
    }

    int getLoc() {
        return loc;
    }

    String fmt(V val) {
        return "RuntimeField{" + val + "}";
    }

    public String toString() {
        return "RuntimeField";
    }

    public ConstField<A, V> readonly() {
        return new ConstField<A, V>(loc);
    }
}
