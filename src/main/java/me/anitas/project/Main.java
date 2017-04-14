package me.anitas.project;

import me.anitas.fields.ConstField;
import me.anitas.fields.RuntimeField;

public class Main {

    public static void main(String[] args) {

        Person n1 = new Person();

        Person n2 = new Person();

        n1.setName("Anita");
        n2.setName("Simran");

        // What if I want to add a new field to Person, but only a few of my classes are
        // interested in it?
        //
        // Perhaps some part of my code sets it, and another reads it.
        // Normally you would create a PhoneNumber :: Map<Person, String>
        //
        // Java Public / Private doesn't cut it.
        //
        // This works fine in practice except may be there may be a lot of contention on that map
        // (And perhaps there is a more time-efficient solution at the cost of space)
        //
        // The cost of extra space is only if only a few of the Person's gets the field otherwise
        // it is negligible.
        //
        // The idea is create a "Runtime Field". anyone who has access to this object can access
        // the field. Just like Map<> except that it is a bit more efficient, and if you are
        // never operating on the same object simultaneously, then there is no concurrency
        // contention too.
        //
        // For example you can create a runtime field called phone number as:
        //
        // How does  it work internally? each field gets an index to lookup in an array inside
        // the Person object.
        //
        // You could pass this object around (inject them?) just like one would have done the
        // Map<>.
        RuntimeField<Person, Long> phoneNumber = Person.FIELDS.newField();

        // And set them.
        n1.field(phoneNumber).set(9123456789L);
        n2.field(phoneNumber).set(8012345678L);

        // You can print them using .get() operation.
        System.out.println("Phone Numbers:");
        System.out.println("\t" + n1.field(phoneNumber).get());
        System.out.println("\t" + n2.field(phoneNumber).get());


        // The other nice thing you can do is create a "Readonly" view of the field,
        // ConstField will not have a set operation so it is not a RuntimeException like in
        // the case of a UnmodifiableMap, but it will be a compile time error.
        ConstField<Person, Long> phoneReadonly = phoneNumber.readonly();
        // For example the following line is an compile time error.
        // n1.field(phoneReadonly).set(1231412L);

        System.out.println("Readonly Phone numbers:");
        System.out.println("\t" + n1.field(phoneReadonly).get());
        System.out.println("\t" + n2.field(phoneReadonly).get());

        // You can also name your fields if it helps debugging, I don't recommend it though.
        RuntimeField<Person, String> profession = Person.FIELDS.newField("profession");
        n1.field(profession).set("Engineer");
        n2.field(profession).set("Doctor");

        System.out.println("Field Names:");
        System.out.println("\t" + profession);
        System.out.println("\t" + phoneNumber);

        // Now the name is used along with the value.
        System.out.println("Field Names with values:");
        System.out.println("\t" + n1.field(profession));
        System.out.println("\t" + n1.field(phoneNumber));

        // This will print the actual values not the Value holder.
        System.out.println("Actual values:");
        System.out.println("\t" + n1.field(profession).get());
        System.out.println("\t" + n2.field(profession).get());
    }
}
