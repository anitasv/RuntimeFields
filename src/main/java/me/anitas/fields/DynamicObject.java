package me.anitas.fields;

public interface DynamicObject<A> {

    Assoc getAssoc();

    default <V> Value<V> field(RuntimeField<A, V> field) {
        return new Value<V>() {

            @Override
            @SuppressWarnings("unchecked")
            public V get() {
                return (V) getAssoc().get(field.getLoc());
            }

            @Override
            public void set(V val) {
                getAssoc().put(field.getLoc(), val);
            }

            public String toString() {
                return field.fmt(get());
            }
        };
    }

    default <V> ConstValue<V> field(ConstField<A, V> field) {
        return new ConstValue<V>() {

            @SuppressWarnings("unchecked")
            @Override
            public V get() {
                return (V) getAssoc().get(field.getLoc());
            }
        };
    }
}
