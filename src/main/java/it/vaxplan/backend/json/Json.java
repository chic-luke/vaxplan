package it.vaxplan.backend.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Util class to handle JSON Jackson methods
 */
public class Json {

    /**
     * This is an utility class, it's not meant to be instantiated.
     */
    private Json() {
        throw new IllegalArgumentException("Utility class");
    }

    private static ObjectMapper objectMapper = getDefaultObjectMapper();

    /**
     * Instantiate the default ObjectMapper and allow customization
     * @return instantiated ObjectMapper object
     */
    private static ObjectMapper getDefaultObjectMapper() {
        var defaultObjectMapper = new ObjectMapper();
        defaultObjectMapper.registerModule(new JavaTimeModule());
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return defaultObjectMapper;
    }

    /**
     * Parse a JSON string into a JsonNode
     * @param src input JSON string
     * @return Resulting JsonNode
     * @throws JsonProcessingException should the parsing fail
     */
    public static JsonNode parse(String src) throws JsonProcessingException {
        return objectMapper.readTree(src);
    }

    /**
     * Convert JsonNode into POJO class
     * @param node JSON node to bind
     * @param currentClass Class ti bind to
     * @param <A> Input object
     * @return Input object, after binding
     * @throws JsonProcessingException should the parsing fail
     */
    public static <A> A fromJson(JsonNode node, Class<A> currentClass) throws JsonProcessingException {
        return objectMapper.treeToValue(node, currentClass);
    }

    /**
     * Bind data from object to JSON.
     * @param a Object to bindf from
     * @return JSON tree representation of said value
     */
    public static JsonNode toJson(Object a) {
        return objectMapper.valueToTree(a);
    }

    /**
     * Serialize JsonNode Java value as String, raw
     * @param node Node to convert into String
     * @return JsonNode value converted to String
     * @throws JsonProcessingException If errors occoured processing JSON
     */
    public static String stringify(JsonNode node) throws JsonProcessingException {
        return generateString(node, false);
    }

    /**
     * Serialize JsonNode Java value as String, pretty and well-indented
     * @param node Node to convert into String
     * @return JsonNode value converted to String
     * @throws JsonProcessingException If errors occoured processing JSON
     */
    public static String prettyPrint(JsonNode node) throws JsonProcessingException {
        return generateString(node, true);
    }

    /**
     * Core logic for jsonToString and prettyPrint methods
     * @param node Node to convert into String
     * @param pretty Whether to enable INDENT_OUTPUT feature
     * @return JsonNode value converted to String
     * @throws JsonProcessingException If errors occoured processing JSON
     */
    private static String generateString(JsonNode node, boolean pretty ) throws JsonProcessingException {
        var objectWriter = objectMapper.writer();
        if (pretty)
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);

        return objectWriter.writeValueAsString(node);
    }

    /**
     * Add a JSON object to a JSON array
     * @param pojo POJO containing the object to add
     * @param node JsonNode to add the new node to
     */
    public static void addPojoToJsonArray(Object pojo, JsonNode node) {
        if (!(node instanceof ArrayNode)) {
            throw new IllegalArgumentException("Expected an ArrayNode instance as argument, got " + node.getClass().getName());
        }
        ArrayNode aNode = (ArrayNode) node;
        aNode.addPOJO(pojo);
    }

    /**
     * Create a new, empty ArrayNode object
     * @return new ArrayNode
     */
    public static ArrayNode createArrayNode() {
        return objectMapper.createArrayNode();
    }

}
