package be.mbict;

import java.time.LocalDate;
import groovy.transform.Canonical;

@Canonical
public class StructuredPerson {

    Name name
    String email
    LocalDate birthDay
}
