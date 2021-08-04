package it.vaxplan.backend.json.pojo;

import it.vaxplan.backend.Sex;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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

    @Setter
    @Getter
    private LocalDate birthDay;

    @Getter
    @Setter
    private Sex sex;

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private boolean atHighRisk;

    @Getter
    @Setter
    private boolean isHealthCareWorker;

    @Getter
    @Setter
    private boolean schoolWorker;

    @Getter
    @Setter
    private boolean lawEnforcementWorker;

    @Getter
    @Setter
    private boolean caretaer;

    @Getter
    @Setter
    private boolean cohabiting;

    @Override
    public String toString() {
        return "firstName: " + firstName + "\n" +
                "lastName: " + lastName + "\n" +
                "fiscalCode: " + fiscalCode + "\n" +
                "birthPlace: " + birthPlace + "\n" +
                "sex: " + sex + "\n" +
                "code: " + code + "\n" +
                "isHealthCareWorker: " + isHealthCareWorker + "\n" +
                "isSchoolWorker: " + schoolWorker + "\n" +
                "isLawEnforcementWorker: " + lawEnforcementWorker + "\n" +
                "isCareTaker: " + caretaer + "\n" +
                "isCohabiting:" + cohabiting + "\n" ;
    }

}
