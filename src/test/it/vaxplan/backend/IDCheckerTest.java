package it.vaxplan.backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.vaxplan.backend.json.JsonIOHandler;
import it.vaxplan.backend.json.Json;
import it.vaxplan.backend.utils.IDChecker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class IDCheckerTest {

    @Test
    void allFiscalCodesInJsonShouldBeValid() throws JsonProcessingException {
        var jsonInputHandler = new JsonIOHandler();
        var jsonAsString = jsonInputHandler.jsonToString("User");

        var node = Json.parse(jsonAsString);

        for (var userIt = node.elements(); userIt.hasNext();) {
            var user = userIt.next();
            System.out.println(IDChecker.isFiscalCodeValid(user.get("fiscalCode").asText()));
            assertTrue(IDChecker.isFiscalCodeValid(user.get("fiscalCode").asText()));
        }
    }

    @Test
    void idCheckerDetectsFalsePositives() {
        var fps = new ArrayList<String>();
        fps.add("TEST");
        fps.add("SPSCRN99P09A944U478FNHUJS");
        fps.add("SPSCRN99P09A944,");
        fps.add("SPSCRN99P58F09A944U");

        for (String fp: fps) {
            System.out.println(IDChecker.isFiscalCodeValid(fp));
            assertFalse(IDChecker.isFiscalCodeValid(fp));
        }
    }

    @Test
    void fiscalCodeIsFoundInJson() throws JsonProcessingException {
        var testFiscalCode = "SPSCRN99P09A944U";
        var jsonInputHandler = new JsonIOHandler();
        var jsonAsString = jsonInputHandler.jsonToString("User");
        var flag = false;

        var node = Json.parse(jsonAsString);

        for (var userIt = node.elements(); userIt.hasNext();) {
            var user = userIt.next();
            System.out.println(user.get("fiscalCode").asText().equals(testFiscalCode));
            flag = user.get("fiscalCode").asText().equals(testFiscalCode);
        }

        assertTrue(flag);
    }

}
