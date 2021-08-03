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

    public void writeJsonToFile(String json, String filename) throws IOException {
        var file = new File("src/main/resources/" + filename + ".json");

        if (file.exists()) {
            // Overwrite the existing file
            System.out.println("File exists!");
            FileOutputStream fos = new FileOutputStream(file, false);
            fos.write(json.getBytes());
            fos.close();
        } else {
            // Create a new file
            try {
                System.out.println(file.createNewFile());
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Write content of json to file
            var writer = new FileWriter(file);
            writer.write(json);
            writer.close();
        }

    }

}
