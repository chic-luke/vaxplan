package it.vaxplan.backend.json;

import com.fasterxml.jackson.core.JsonProcessingException;

public class JsonUtils {

    public static boolean lookUp(String filename, String field, String query) throws JsonProcessingException {
        var jsonInputHandler = new JsonIOHandler();
        var jsonAsString = jsonInputHandler.jsonToString(filename);

        var node = Json.parse(jsonAsString);

        for (var iterator = node.elements(); iterator.hasNext();) {
            var item = iterator.next();

            if (item.get(field).asText().equals(query)) {
                System.out.println(item.get(field).asText().equals(query));
                return true;
            }

        }

        return false;
    }

}
