package it.vaxplan.backend.json;

import org.junit.jupiter.api.Test;

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

}
