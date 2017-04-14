package me.anitas.fields;

import java.util.ArrayList;
import java.util.List;

public class Assoc {

    private final List<Object> map = new ArrayList<>();

    synchronized Object get(int i) {
        if (i < map.size()) {
            return map.get(i);
        } else {
            return null;
        }
    }

    synchronized void put(int i, Object obj) {
        int len = map.size();
        if (i < len) {
            map.set(i, obj);
        } else {
            for (int j = len; j < i; j++) {
                map.add(null);
            }
            map.add(obj);
        }
    }
}
