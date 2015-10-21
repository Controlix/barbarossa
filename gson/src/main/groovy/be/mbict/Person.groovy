package be.mbict;

import java.time.LocalDate;
import groovy.transform.Canonical;

@Canonical
public class Person {
    String firstName
    String lastName
    String nickName
    String email
    LocalDate birthDay
}
