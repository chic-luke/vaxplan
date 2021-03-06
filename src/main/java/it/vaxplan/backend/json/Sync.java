package it.vaxplan.backend.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import it.vaxplan.backend.Patient;
import it.vaxplan.backend.VaccineCampaign;
import it.vaxplan.backend.VaccineSite;
import it.vaxplan.backend.json.pojo.PatientPOJO;
import it.vaxplan.backend.json.pojo.VaccineCampaignPOJO;
import it.vaxplan.backend.json.pojo.VaccineSitePOJO;
import it.vaxplan.backend.service.CitizenService;
import it.vaxplan.backend.service.PatientService;
import it.vaxplan.backend.service.VaccineCampaignService;
import it.vaxplan.backend.service.VaccineSiteService;

import java.io.IOException;

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
            pojo.setAvailableSites(c.getAvailableSites());
            pojo.setPatientCategories(c.getPatientCategories());
            pojo.setListOfBookings(c.getListOfBookings());
            pojo.setBookedSlots(c.getBookedSlots());

            // Add POJO to file JSON array
            Json.addPojoToJsonArray(pojo, serviceNode);
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

        // Clear VaccineCampaignService to avoid double entries
        VaccineCampaignService.getCampaigns().clear();

        for (var campaignIt = node.elements(); campaignIt.hasNext();) {
            var campaign = campaignIt.next();

            var pojo = Json.fromJson(campaign, VaccineCampaignPOJO.class);

            var newCampaign = new VaccineCampaign(pojo.getName(), pojo.getVaccine(), pojo.getAvailableDoses(),
                    pojo.getStartDate(), pojo.getEndDate(), pojo.getDailyStartTime(), pojo.getDailyEndTime(),
                    pojo.getAvailableSites(), pojo.getPatientCategories(), pojo.getListOfBookings(),
                    pojo.getBookedSlots());

            // Add campaign created above to VaccineCampaignService
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
            pojo.setHealthCardNumber(p.getHealthCardNumber());
            pojo.setBirthPlace(p.getBirthPlace());
            pojo.setBirthDay(p.getBirthDay());
            pojo.setSex(p.getSex());
            pojo.setAtHighRisk(p.isAtHighRisk());
            pojo.setHealthCareWorker(p.isHealthCareWorker());
            pojo.setSchoolWorker(p.isSchoolWorker());
            pojo.setLawEnforcementWorker(p.isLawEnforcementWorker());
            pojo.setCaretaer(p.isCaretaker());
            pojo.setCohabiting(p.isCohabiting());

            // Add POJO to file JSON array
            Json.addPojoToJsonArray(pojo, serviceNode);
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

        // Clear PatientService to avoid double entries
        PatientService.getPatients().clear();

        for (var patientIt = node.elements(); patientIt.hasNext();) {
            var patient = patientIt.next();

            var pojo = Json.fromJson(patient, PatientPOJO.class);

            var newPatient = new Patient(pojo.getFirstName(), pojo.getLastName(), pojo.getFiscalCode(),
                    pojo.getHealthCardNumber(), pojo.getBirthPlace(), pojo.getBirthDay(), pojo.getSex(), pojo.isAtHighRisk(), pojo.isHealthCareWorker(),
                    pojo.isSchoolWorker(), pojo.isLawEnforcementWorker(), pojo.isCaretaer(),
                    pojo.isCohabiting());

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

        // Clear VaccineSiteService to avoid double entries
        VaccineSiteService.getSites().clear();

        for (var siteIt = node.elements(); siteIt.hasNext();) {
            var site = siteIt.next();

            var pojo = Json.fromJson(site, VaccineSitePOJO.class);

            var newSite = new VaccineSite(pojo.getName());

            VaccineSiteService.addSite(newSite);
        }

    }

    /**
     * Initialize CitizenService with contents from Citizens.json
     */
    public static void initCitizenServiceFromJson() throws JsonProcessingException {
        var jio = new JsonIOHandler();
        var fileOutput = jio.jsonToString("Citizens");
        var node = Json.parse(fileOutput);

        // Clear CitizenService to avoid double entries
        CitizenService.getCitizens().clear();

        for (var bookingIt = node.elements(); bookingIt.hasNext();) {
            var citizen = bookingIt.next();

            var pojo = Json.fromJson(citizen, PatientPOJO.class);

            var newCitizen = new Patient(pojo.getFirstName(), pojo.getLastName(), pojo.getFiscalCode(),
                    pojo.getHealthCardNumber(), pojo.getBirthPlace(), pojo.getBirthDay(), pojo.getSex(), pojo.isAtHighRisk(),
                    pojo.isHealthCareWorker(), pojo.isSchoolWorker(), pojo.isLawEnforcementWorker(), pojo.isCaretaer(),
                    pojo.isCohabiting());

            CitizenService.addCitizen(newCitizen);
        }
    }

}
