package it.vaxplan.backend.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import it.vaxplan.backend.Patient;
import it.vaxplan.backend.Vaccine;
import it.vaxplan.backend.VaccineCampaign;
import it.vaxplan.backend.VaccineSite;
import it.vaxplan.backend.json.pojo.PatientPOJO;
import it.vaxplan.backend.json.pojo.VaccineCampaignPOJO;
import it.vaxplan.backend.json.pojo.VaccineSitePOJO;
import it.vaxplan.backend.service.BookingService;
import it.vaxplan.backend.service.PatientService;
import it.vaxplan.backend.service.VaccineCampaignService;
import it.vaxplan.backend.service.VaccineSiteService;

import java.io.IOException;
import java.util.HashSet;

public class Sync {

    /**
     * This is an utility class, it's not meant to be instantiated.
     */
    private Sync() {
        throw new IllegalArgumentException("Utility class");
    }

    /**
     * Write the contents of VaccineCampaignService to VaccineCampaign.json
     * (located in resources)
     * @throws IOException if I/O operations fail
     */
    public static void writeVaccineCampaignServiceToJson() throws IOException {
        var jio = new JsonIOHandler();
        JsonNode serviceNode = Json.createArrayNode();

        // Create JSON from VaccineCampaignService
        for (VaccineCampaign c: VaccineCampaignService.getCampaigns()) {
            // Create POJO class from current VaccineCampaign
            var pojo = new VaccineCampaignPOJO();
            pojo.setName(c.getName());
            pojo.setVaccine(c.getVaccine());
            pojo.setAvailableDoses(c.getAvailableDoses());
            pojo.setStartDate(c.getStartDate());
            pojo.setEndDate(c.getEndDate());
            pojo.setDailyStartTime(c.getDailyStartTime());
            pojo.setDailyEndTime(c.getDailyEndTime());

            // Add POJO to file JSON array
            serviceNode = Json.addPojoToJsonArray(pojo, serviceNode);
        }

        // Convert node into String
        var output = Json.prettyPrint(serviceNode);

        // Write said JSON String to file
        jio.writeJsonToFile(output, "VaccineCampaign");
    }

    /**
     * Initialize VaccineCampaignService with contents from VaccineCampaign.json
     * (located in resources)
     * @throws JsonProcessingException if JSON processing fails
     */
    public static void initVaccineCampaignServiceFromJson() throws JsonProcessingException {
        var jio = new JsonIOHandler();
        var fileOutput = jio.jsonToString("VaccineCampaign");
        var node = Json.parse(fileOutput);

        for (var campaignIt = node.elements(); campaignIt.hasNext();) {
            var campaign = campaignIt.next();

            var pojo = Json.fromJson(campaign, VaccineCampaignPOJO.class);

            var newCampaign = new VaccineCampaign(pojo.getName(), pojo.getVaccine(), pojo.getAvailableDoses(),
                    pojo.getStartDate(), pojo.getEndDate(), pojo.getDailyStartTime(), pojo.getDailyEndTime(),
                    new HashSet<>(), new BookingService(), new HashSet<>());

            VaccineCampaignService.addCampaign(newCampaign);
        }

    }

    /**
     * Write the contents of PatientService to Patient.json
     * (located in resources)
     * @throws IOException if I/O operations fail
     */
    public static void writePatientServiceToJson() throws IOException {
        var jio = new JsonIOHandler();
        JsonNode serviceNode = Json.createArrayNode();

        // Create JSON from PatientService
        for (Patient p: PatientService.getPatients()) {
            // Create POJO class from current Patient
            var pojo = new PatientPOJO();
            pojo.setFirstName(p.getFirstName());
            pojo.setLastName(p.getLastName());
            pojo.setFiscalCode(p.getFiscalCode());
            pojo.setBirthPlace(p.getBirthPlace());
            pojo.setBirthDay(p.getBirthDay());
            pojo.setSex(p.getSex());
            pojo.setCode(p.getFiscalCode());
            pojo.setHealthCareWorker(p.isHealthCareWorker());

            // Add POJO to file JSON array
            serviceNode = Json.addPojoToJsonArray(pojo, serviceNode);
        }

        // Convert node into String
        var output = Json.prettyPrint(serviceNode);

        // Write said JSON String to file
        jio.writeJsonToFile(output, "Patient");
    }

    /**
     * Initialize PatientService with contents from VaccineCampaign.json
     * (located in resources)
     */
    public static void initPatientServiceFromJson() throws JsonProcessingException {
        var jio = new JsonIOHandler();
        var fileOutput = jio.jsonToString("Patient");
        var node = Json.parse(fileOutput);

        for (var patientIt = node.elements(); patientIt.hasNext();) {
            var patient = patientIt.next();

            var pojo = Json.fromJson(patient, PatientPOJO.class);

            var newPatient = new Patient(pojo.getFirstName(), pojo.getLastName(), pojo.getFiscalCode(),
                    pojo.getBirthPlace(), pojo.getBirthDay(), pojo.getSex(), pojo.isHealthCareWorker());

            PatientService.addPatient(newPatient);
        }

    }

    /**
     * Initialize VaccineSiteService with contents from VaccineSite.json
     * (located in resources)
     */
    public static void initVaccineSiteServiceFromJson() throws JsonProcessingException {
        var jio = new JsonIOHandler();
        var fileOutput = jio.jsonToString("VaccineSites");
        var node = Json.parse(fileOutput);

        for (var siteIt = node.elements(); siteIt.hasNext();) {
            var site = siteIt.next();

            var pojo = Json.fromJson(site, VaccineSitePOJO.class);

            var newSite = new VaccineSite(pojo.getName());

            VaccineSiteService.addSite(newSite);
        }

    }

}
