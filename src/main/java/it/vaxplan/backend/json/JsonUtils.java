package it.vaxplan.backend.json;

import com.fasterxml.jackson.core.JsonProcessingException;

public class JsonUtils {

    /**
     * Try to find a match between a query and the value of a given field in a given JSON
     * @param filename JSON to search through
     * @param field JSON field to evaluate
     * @param query Query to check against
     * @return Whether query was found in filename as field
     * @throws JsonProcessingException If JSON processing fails
     */
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
