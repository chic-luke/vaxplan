package it.vaxplan.backend.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonIOHandlerTest {

    @Test
    void jsonConvertsToStringCorrectly() {
        var jsonInputHandler = new JsonIOHandler();
        var jsonAsString = jsonInputHandler.jsonToString("test");
        System.out.println(jsonAsString);
        String jsonTestString = "[\n" +
                "  {\n" +
                "    \"title\": \"test\",\n" +
                "    \"isTest\": true\n" +
                "  }\n" +
                "]";
        assertEquals(jsonTestString, jsonAsString);
    }

    /**
     * If this test doesn't pass after a change, it may be necessary to run it again.
     * @throws IOException If file creation fails
     */
    @Test
    void writeJsonToFileSuccess() throws IOException, InterruptedException {
        // Get existing JSON file into String
        var io = new JsonIOHandler();
        var json = io.jsonToString("User");

        io.writeJsonToFile(json, "TestJson2");

        assertEquals(json, io.jsonToString("TestJson"));
    }

}
