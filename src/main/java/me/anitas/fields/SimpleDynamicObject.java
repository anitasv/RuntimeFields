package me.anitas.fields;

public class SimpleDynamicObject<A> implements DynamicObject<A> {

    private final Assoc __assoc = new Assoc();

    @Override
    public Assoc getAssoc() {
        return __assoc;
    }
}
