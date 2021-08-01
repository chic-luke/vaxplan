package it.vaxplan.backend.json;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IOHandlerTest {

    private final String jsonTestString = "[\n" +
            "  {\n" +
            "    \"title\": \"test\",\n" +
            "    \"isTest\": true\n" +
            "  }\n" +
            "]";

    @Test
    void jsonToString() {
        var jsonInputHandler = new IOHandler();
        var jsonAsString = jsonInputHandler.jsonToString("test");
        System.out.println(jsonAsString);
        assertEquals(jsonTestString, jsonAsString);
    }
}
