package it.vaxplan.backend.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.vaxplan.backend.Sex;
import it.vaxplan.backend.json.pojo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class JsonTest {

    private final String jsonTestSource0 = "{\n" +
            "  \"title\": \"Test String\",\n" +
            "  \"author\": \"Test --Author\"\n" +
            "}";

    private final String jsonTestSource3 = "{\n" +
            "    \"firstName\": \"Mario\",\n" +
            "    \"lastName\": \"Rossi\",\n" +
            "    \"fiscalCode\": \"RSSMRA86D05F205W\",\n" +
            "    \"birthPlace\": \"Milano\",\n" +
            "    \"birthDay\": \"05/04/1986\",\n" +
            "    \"sex\": \"MALE\",\n" +
            "    \"code\": \"205W\",\n" +
            "    \"isHealthCareWorker\": false\n" +
            "  }";

    @Test
    void jsonStringToJsonNodeSuccess() throws JsonProcessingException {
        var node = Json.parse(jsonTestSource0);
        assertEquals("Test String", node.get("title").asText());
    }

    @Test
    void jsonNodeToPojoSuccess() throws JsonProcessingException {
        var node = Json.parse(jsonTestSource0);
        var pojo = Json.fromJson(node, SimpleTestCaseJsonPOJO.class);

        assertEquals("Test String", pojo.getTitle());
    }

    @Test
    void pojoToJsonNodeSuccess() {
        var pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("New Title set using toJson");

        var node = Json.toJson(pojo);

        assertEquals("New Title set using toJson", node.get("title").asText());
    }

    @Test
    void pojoToStringSuccess() throws JsonProcessingException {
        var pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("New Title set using toJson");

        var node = Json.toJson(pojo);

        assertEquals("{\"title\":\"New Title set using toJson\"}",
                Json.stringify(node));
    }

    @Test
    void pojoToStringPrettyPrintSuccess() throws JsonProcessingException {
        var pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("New Title set using toJson");

        var node = Json.toJson(pojo);

        var expected = "{\n" +
                "  \"title\" : \"New Title set using toJson\"\n" +
                "}";

        System.out.println(Json.prettyPrint(node));
        assertEquals(expected, Json.prettyPrint(node));
    }

    @Test
    void example1StringToPojoSuccess() throws JsonProcessingException {
        String jsonTestSource1 = "{\n" +
                "  \"date\": \"2021-07-30\",\n" +
                "  \"name\": \"July 30th\"\n" +
                "}";
        var node = Json.parse(jsonTestSource1);
        var pojo = Json.fromJson(node, DayPOJO.class);

        assertEquals("2021-07-30", pojo.getDate().toString());
    }

    @Test
    void jsonStringParseArrayToPojoSuccess() throws JsonProcessingException {
        String jsonTestSource2 = "{\n" +
                "  \"authorName\": \"ExampleAuthor\",\n" +
                "  \"books\": [\n" +
                "    {\n" +
                "      \"title\": \"title1\",\n" +
                "      \"inPrint\": true,\n" +
                "      \"publishDate\": \"2020-06-10\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"title2\",\n" +
                "      \"inPrint\": false,\n" +
                "      \"publishDate\": \"2021-01-01\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        var node = Json.parse(jsonTestSource2);
        var pojo = Json.fromJson(node, AuthorPOJO.class);

        System.out.println("Author : " + pojo.getAuthorName());

        for (BookPOJO bp: pojo.getBooks()) {
            System.out.println("Book : " + bp.getTitle());
            System.out.println("Is in print : " + bp.isInPrint());
            System.out.println("Date : " + bp.getPublishDate());
        }
    }

    @Test
    void patientJsonObjectToPojoSuccess() throws JsonProcessingException {
        var node = Json.parse(jsonTestSource3);
        var pojo = Json.fromJson(node, PatientPOJO.class);

        System.out.println(pojo);
        assertEquals("Mario", pojo.getFirstName());
        assertEquals("Rossi", pojo.getLastName());
        assertEquals("RSSMRA86D05F205W", pojo.getFiscalCode());
        assertEquals("Milano", pojo.getBirthPlace());
        assertEquals(Sex.MALE, pojo.getSex());
        assertEquals("205W", pojo.getCode());
        assertFalse(pojo.isHealthCareWorker());
    }

    @Test
    void jsonObjectToPatientClassSuccess() throws JsonProcessingException {
        var node = Json.parse(jsonTestSource3);
        var testPatient = PatientFromJson.createPatient(node);

        assertEquals("Mario", testPatient.getFirstName());
        assertEquals("Rossi", testPatient.getLastName());
        assertEquals("RSSMRA86D05F205W", testPatient.getFiscalCode());
        assertEquals("Milano", testPatient.getBirthPlace());
        assertEquals(Sex.MALE, testPatient.getSex());
        assertFalse(testPatient.isHealthCareWorker());
    }

    @Test
    void userJsonParsedCorrectly() throws JsonProcessingException {
        var jsonInputHandler = new JsonIOHandler();
        var jsonAsString = jsonInputHandler.jsonToString("User");

        var node = Json.parse(jsonAsString);

        for (var userIt = node.elements(); userIt.hasNext();) {
            var user = userIt.next();
            System.out.println(user.get("firstName"));
        }

    }

    @Test
    void addObjectToJsonNodeSuccess() throws JsonProcessingException {
        var io = new JsonIOHandler();
        var node = Json.parse(io.jsonToString("User"));

        var pojo = new PatientPOJO();
        pojo.setFirstName("Giovanni");
        pojo.setLastName("Giorgio");
        pojo.setFiscalCode("GRGGNN69D26F205B");
        pojo.setBirthPlace("Milano");
        pojo.setSex(Sex.MALE);
        pojo.setHealthCareWorker(false);
        pojo.setCode("205B");

        var finalNode = Json.addPojoToJsonArray(pojo, node);

        System.out.println(Json.prettyPrint(finalNode));

        for (var userIt = finalNode.elements(); userIt.hasNext();) {
            var user = userIt.next();
            System.out.println(user.get("firstName"));
        }

    }

}
