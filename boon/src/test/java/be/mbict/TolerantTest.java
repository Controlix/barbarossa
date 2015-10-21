package be.mbict;

import java.time.LocalDate;
import org.boon.Boon;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TolerantTest {

    @Test
    public void exactCopy() {
        Person in = new Person("John", "Doe", "den traktor", "john.doe@gmail.com");

        String encoded = Boon.toJson(in);

        Person out = Boon.fromJson(encoded, Person.class);

        System.out.println(in);
        System.out.println(encoded);
        System.out.println(out);

        assertEquals(in, out);
    }

    @Test
    public void partialCopy() {
        Person in = new Person("John", "Doe", "den traktor", "john.doe@gmail.com");

        String encoded = Boon.toJson(in);

        StructuredPerson out = Boon.fromJson(encoded, StructuredPerson.class);

        System.out.println(in);
        System.out.println(encoded);
        System.out.println(out);

        assertEquals(in, out);
    }

    @Test
    public void partial2() {
        StructuredPerson in = new StructuredPerson(new Name("John", "Doe", "den tuub"), "john.doe@gmail.com");

        String encoded = Boon.toJson(in);

        Person out = Boon.fromJson(encoded, Person.class);

        System.out.println(in);
        System.out.println(encoded);
        System.out.println(out);

        assertEquals(in, out);
    }
}
