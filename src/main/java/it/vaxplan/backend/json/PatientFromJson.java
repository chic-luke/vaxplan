package it.vaxplan.backend.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import it.vaxplan.backend.Patient;
import it.vaxplan.backend.json.pojo.PatientPOJO;
import it.vaxplan.backend.service.PatientService;

import java.time.ZonedDateTime;

public class PatientFromJson {

    public static void getPatientsFromJson() throws JsonProcessingException {
        // Get JSON file and save it in a String
        var jsonInputHandler = new IOHandler();
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

    public static Patient createPatient(JsonNode node) throws JsonProcessingException {
        var pojo = Json.fromJson(node, PatientPOJO.class);

        return new Patient(pojo.getFirstName(), pojo.getLastName(), pojo.getFiscalCode(),
                pojo.getBirthPlace(), ZonedDateTime.now(), pojo.getSex(), pojo.isHealthCareWorker());
    }

}
