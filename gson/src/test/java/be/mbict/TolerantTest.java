package be.mbict;

import com.google.gson.Gson;
import java.time.LocalDate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TolerantTest {

    @Test
    public void exactCopy() {
        Person in = new Person("John", "Doe", "den traktor", "john.doe@gmail.com", LocalDate.of(1965, 3, 17));

        String encoded = new Gson().toJson(in);

        Person out = new Gson().fromJson(encoded, Person.class);

        System.out.println(in);
        System.out.println(encoded);
        System.out.println(out);

        assertEquals(out, in);
    }

    @Test
    public void partialCopy() {
        Person in = new Person("John", "Doe", "den traktor", "john.doe@gmail.com", LocalDate.of(1965, 3, 17));

        String encoded = new Gson().toJson(in);

        StructuredPerson out = new Gson().fromJson(encoded, StructuredPerson.class);

        System.out.println(in);
        System.out.println(encoded);
        System.out.println(out);

        assertEquals(out, in);
    }

    @Test
    public void partial2() {
        StructuredPerson in = new StructuredPerson(new Name("John", "Doe", "den tuub"), "john.doe@gmail.com", LocalDate.of(1974, 12, 6));

        String encoded = new Gson().toJson(in);

        Person out = new Gson().fromJson(encoded, Person.class);

        System.out.println(in);
        System.out.println(encoded);
        System.out.println(out);

        assertEquals(out, in);
    }
}
