package it.vaxplan.backend.json;

import com.fasterxml.jackson.core.JsonProcessingException;
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

}
