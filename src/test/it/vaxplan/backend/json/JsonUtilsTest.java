package it.vaxplan.backend.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonUtilsTest {

    @Test
    void fiscalCodeFoundInUserJson() throws JsonProcessingException {
        var filename = "User";
        var field = "fiscalCode";
        var query = "GRDGNN76T04H501O";

        assertTrue(JsonUtils.lookUp(filename, field, query));
    }
}