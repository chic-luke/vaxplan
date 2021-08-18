package it.vaxplan.frontend;

import it.vaxplan.backend.Sex;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class RegistrationFields {

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private LocalDate birthDay;

    @Getter
    @Setter
    private String birthPlace;

    @Getter
    @Setter
    private String fiscalCode;

    @Getter
    @Setter
    private Sex sex;

}
