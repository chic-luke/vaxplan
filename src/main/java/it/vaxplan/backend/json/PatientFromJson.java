package it.vaxplan.backend.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.vaxplan.backend.Patient;

import java.time.ZonedDateTime;

public class PatientFromJson {

    public static Patient createPatient() throws JsonProcessingException {
        String patientTest = "{\n" +
                "    \"firstName\": \"Mario\",\n" +
                "    \"lastName\": \"Rossi\",\n" +
                "    \"fiscalCode\": \"RSSMRA86D05F205W\",\n" +
                "    \"birthPlace\": \"Milano\",\n" +
                "    \"birthDay\": \"05/04/1986\",\n" +
                "    \"sex\": \"MALE\",\n" +
                "    \"code\": \"205W\",\n" +
                "    \"isHealthCareWorker\": false\n" +
                "  }";

        var node = Json.parse(patientTest);
        var pojo = Json.fromJson(node, PatientObject.class);

        var newPatient = new Patient(pojo.getFirstName(), pojo.getLastName(), pojo.getFiscalCode(),
                pojo.getBirthPlace(), ZonedDateTime.now(), pojo.getSex(), pojo.isHealthCareWorker());

        System.out.println(newPatient);

        return newPatient;
    }

}
