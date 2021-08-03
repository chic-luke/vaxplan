package it.vaxplan.backend.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import it.vaxplan.backend.Patient;
import it.vaxplan.backend.json.pojo.PatientPOJO;
import it.vaxplan.backend.service.PatientService;

import java.time.ZonedDateTime;

public class PatientFromJson {

    /**
     * This is an utility class, it's not meant to be instantiated.
     */
    private PatientFromJson() {
        throw new IllegalArgumentException("Utility class");
    }

    /**
     * Initialize PatientService with a set of Patients with information obtained
     * by parsing User.json
     * @throws JsonProcessingException If JSON processing fails
     */
    public static void getPatientsFromJson() throws JsonProcessingException {
        // Get JSON file and save it in a String
        var jsonInputHandler = new JsonIOHandler();
        var jsonAsString = jsonInputHandler.jsonToString("User");

        // Get Jackson JsonNode from String
        var node = Json.parse(jsonAsString);

        // Iterate through all the objects in the JSON file and create a corresponsing
        // Patient object for each

        // Temporary PatientService until a real one is implemented
        var testService = new PatientService();
        for (var userIt = node.elements(); userIt.hasNext();) {
            var user = userIt.next();
            // Handle below
            testService.addPatient(createPatient(user));
        }

        System.out.println(testService.getPatients());
    }

    /**
     * Create and return a Patient object from a JsonNode
     * @param node JsonNode to bind to POJO class
     * @return Resulting Patient object
     * @throws JsonProcessingException If JSON processing fails
     */
    public static Patient createPatient(JsonNode node) throws JsonProcessingException {
        var pojo = Json.fromJson(node, PatientPOJO.class);

        return new Patient(pojo.getFirstName(), pojo.getLastName(), pojo.getFiscalCode(),
                pojo.getBirthPlace(), ZonedDateTime.now(), pojo.getSex(), pojo.isHealthCareWorker());
    }

}
