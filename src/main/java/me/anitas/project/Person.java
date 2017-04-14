package me.anitas.project;

import lombok.Data;
import me.anitas.fields.RuntimeFields;
import me.anitas.fields.SimpleDynamicObject;

@Data
public class Person extends SimpleDynamicObject<Person> {

    public static final RuntimeFields<Person> FIELDS = new RuntimeFields<>();

    private String name;

}
