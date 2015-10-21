package be.mbict;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Person {
    String firstName;
    String lastName;
    String nickName;
    String email;
}
