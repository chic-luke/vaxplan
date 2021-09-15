package it.vaxplan.backend.json;

import lombok.SneakyThrows;

import java.io.*;

public class JsonIOHandler {

    private static final String BASE_DIR = "data/";

    /**
     * Read a JSON file placed in the resources folder and return its contents
     * as String
     * @param filename Name of the JSON file to parse (without extension)
     * @return Contents of filename as String
     */
    @SneakyThrows
    public String jsonToString(String filename) {
        var out = new StringBuilder();
        var path = JsonIOHandler.BASE_DIR + filename + ".json";

        // Make sure JSON InputStream is not null
        if (!new File(path).exists()) {
            throw new IllegalArgumentException("File not found! " + path);
        }

        // Get JSON file as InputStream from resources folder
        var inputStream = new FileInputStream(path);

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
        var path = JsonIOHandler.BASE_DIR + filename + ".json";
        var file = new File(path);

        if (file.exists()) {
            // Overwrite the existing file
            System.out.println("File exists!");
            try (FileOutputStream fos = new FileOutputStream(path, false)) {
                fos.write(json.getBytes());
            }
        }

        // Create a new file
        try {
            System.out.println(file.createNewFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write content of json to file
        try (var writer = new FileWriter(file)) {
            writer.write(json);
        }

    }

}
