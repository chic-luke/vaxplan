package it.vaxplan.backend.json;

import java.io.*;

public class JsonIOHandler {

    /**
     * Read a JSON file placed in the resources folder and return its contents
     * as String
     * @param filename Name of the JSON file to parse (without extension)
     * @return Contents of filename as String
     */
    public String jsonToString(String filename) {
        var out = new StringBuilder();

        // Get JSON file as InputStream from resources folder
        var inputStream = this.getClass().getClassLoader()
                .getResourceAsStream(filename + ".json");

        // Make sure JSON InputStream is not null
        if (inputStream == null) {
            throw new IllegalArgumentException("File not found! " + filename + ".json");
        }

        // Copy JSON InputStream to a String
        var line = "";
        try (var reader = new BufferedReader(new InputStreamReader(inputStream))) {
            while ((line = reader.readLine()) != null) {
                out.append(line);
                out.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Delete the last new line character
        out.deleteCharAt(out.length() - 1);

        return out.toString();
    }

}
