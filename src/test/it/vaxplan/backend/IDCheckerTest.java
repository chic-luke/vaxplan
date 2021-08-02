package it.vaxplan.backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.vaxplan.backend.json.IOHandler;
import it.vaxplan.backend.json.Json;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class IDCheckerTest {

    @Test
    void fiscalCodeJsonValidityChecker() throws JsonProcessingException {
        var jsonInputHandler = new IOHandler();
        var jsonAsString = jsonInputHandler.jsonToString("User");

        var node = Json.parse(jsonAsString);

        for (var userIt = node.elements(); userIt.hasNext();) {
            var user = userIt.next();
            System.out.println(IDChecker.isFiscalCodeValid(user.get("fiscalCode").asText()));
            assertTrue(IDChecker.isFiscalCodeValid(user.get("fiscalCode").asText()));
        }
    }

    @Test
    void checkFalsePositives() {
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

}
