package it.vaxplan.backend.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.vaxplan.backend.Sex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonTest {

    private String jsonSourceTest = "{\n" +
            "  \"title\": \"Test String\",\n" +
            "  \"author\": \"Test --Author\"\n" +
            "}";

    private String dayScenario1 = "{\n" +
            "  \"date\": \"2021-07-30\",\n" +
            "  \"name\": \"July 30th\"\n" +
            "}";

    private String authorBookScenario = "{\n" +
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

    private String patientTest = "{\n" +
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
    void parse() throws JsonProcessingException {
        var node = Json.parse(jsonSourceTest);
        assertEquals("Test String", node.get("title").asText());
    }

    @Test
    void fromJson() throws JsonProcessingException {
        var node = Json.parse(jsonSourceTest);
        var pojo = Json.fromJson(node, SimpleTestCaseJsonPOJO.class);

        assertEquals("Test String", pojo.getTitle());
    }

    @Test
    void toJson() {
        var pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("New Title set using toJson");

        var node = Json.toJson(pojo);

        assertEquals("New Title set using toJson", node.get("title").asText());
    }

    @Test
    void jsonToString() throws JsonProcessingException {
        var pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("New Title set using toJson");

        var node = Json.toJson(pojo);

        System.out.println(Json.jsonToString(node));
    }

    @Test
    void prettyPrint() throws JsonProcessingException {
        var pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("New Title set using toJson");

        var node = Json.toJson(pojo);

        System.out.println(Json.prettyPrint(node));
    }

    @Test
    void dayTestScenario1() throws JsonProcessingException {
        var node = Json.parse(dayScenario1);
        var pojo = Json.fromJson(node, DayPOJO.class);

        assertEquals("2021-07-30", pojo.getDate().toString());
    }

    @Test
    void setAuthorBookScenario1() throws JsonProcessingException {
        var node = Json.parse(authorBookScenario);
        var pojo = Json.fromJson(node, AuthorPOJO.class);

        System.out.println("Author : " + pojo.getAuthorName());

        for (BookPOJO bp: pojo.getBooks()) {
            System.out.println("Book : " + bp.getTitle());
            System.out.println("Is in print : " + bp.isInPrint());
            System.out.println("Date : " + bp.getPublishDate());
        }
    }

    @Test
    void patientJsonToPOJO() throws JsonProcessingException {
        var node = Json.parse(patientTest);
        var pojo = Json.fromJson(node, PatientObject.class);

        System.out.println(pojo);
        assertEquals("Mario", pojo.getFirstName());
        assertEquals("Rossi", pojo.getLastName());
        assertEquals("RSSMRA86D05F205W", pojo.getFiscalCode());
        assertEquals("Milano", pojo.getBirthPlace());
        assertEquals(Sex.MALE, pojo.getSex());
        assertEquals("205W", pojo.getCode());
        assertEquals(false, pojo.isHealthCareWorker());
    }

    @Test
    void patientPOJOtoClass() throws JsonProcessingException {
        var testPatient = PatientFromJson.createPatient();

        assertEquals("Mario", testPatient.getFirstName());
        assertEquals("Rossi", testPatient.getLastName());
        assertEquals("RSSMRA86D05F205W", testPatient.getFiscalCode());
        assertEquals("Milano", testPatient.getBirthPlace());
        assertEquals(Sex.MALE, testPatient.getSex());
        assertEquals(false, testPatient.isHealthCareWorker());
    }

}
