package it.vaxplan.backend.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientFromJsonTest {

    @Test
    void userJsonToPatientServiceSuccess() throws JsonProcessingException {
        assertTrue(PatientFromJson.getPatientsFromJson());
    }

}