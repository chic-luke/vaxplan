package it.vaxplan.frontend;

import lombok.Getter;
import lombok.Setter;

public class RegistrationFields {

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private Integer birthDay;

    @Getter
    @Setter
    private Integer birthMonth;

    @Getter
    @Setter
    private Integer birthYear;

    @Getter
    @Setter
    private String birthPlace;

    @Getter
    @Setter
    private String fiscalCode;

}
