package it.vaxplan.backend.json;

import it.vaxplan.backend.Sex;
import lombok.Getter;
import lombok.Setter;

public class PatientPOJO {

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String fiscalCode;

    @Getter
    @Setter
    private String birthPlace;

    @Getter
    @Setter
    private Sex sex;

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private boolean isHealthCareWorker;

    @Override
    public String toString() {
        return "firstName: " + firstName + "\n" +
                "lastName: " + lastName + "\n" +
                "fiscalCode: " + fiscalCode + "\n" +
                "birthPlace: " + birthPlace + "\n" +
                "sex: " + sex + "\n" +
                "code: " + code + "\n" +
                "isHealthCareWorker: " + isHealthCareWorker + "\n";
    }

}
