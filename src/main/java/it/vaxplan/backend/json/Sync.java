package it.vaxplan.backend.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import it.vaxplan.backend.Booking;
import it.vaxplan.backend.Patient;
import it.vaxplan.backend.VaccineCampaign;
import it.vaxplan.backend.VaccineSite;
import it.vaxplan.backend.json.pojo.BookingPOJO;
import it.vaxplan.backend.json.pojo.PatientPOJO;
import it.vaxplan.backend.json.pojo.VaccineCampaignPOJO;
import it.vaxplan.backend.json.pojo.VaccineSitePOJO;
import it.vaxplan.backend.service.*;

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
            pojo.setAvailableSites(c.getAvailableSites());
            pojo.setPatientCategories(c.getPatientCategories());
            pojo.setListOfBookings(c.getListOfBookings());

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
                    pojo.getAvailableSites(), pojo.getPatientCategories(), pojo.getListOfBookings());

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
            pojo.setAtHighRisk(p.isAtHighRisk());
            pojo.setHealthCareWorker(p.isHealthCareWorker());
            pojo.setSchoolWorker(p.isSchoolWorker());
            pojo.setLawEnforcementWorker(p.isLawEnforcementWorker());
            pojo.setCaretaer(p.isCaretaker());
            pojo.setCohabiting(p.isCohabiting());

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
                    pojo.getBirthPlace(), pojo.getBirthDay(), pojo.getSex(), pojo.isAtHighRisk(), pojo.isHealthCareWorker(),
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

        for (var siteIt = node.elements(); siteIt.hasNext();) {
            var site = siteIt.next();

            var pojo = Json.fromJson(site, VaccineSitePOJO.class);

            var newSite = new VaccineSite(pojo.getName());

            VaccineSiteService.addSite(newSite);
        }

    }

    /**
     * Write the contents of BookingService to Bookings.json
     * (located in resources)
     */
    public static void writeBookingServiceToJson() throws IOException {
        var jio = new JsonIOHandler();
        JsonNode serviceNode = Json.createArrayNode();

        // Create JSON from BookingService
        for (Booking b: BookingService.getBookings()) {
            // Create POJO class from current Patient
            var pojo = new BookingPOJO();
            pojo.setPatient(b.getPatient());
            pojo.setVaccineCampaignUUID(b.getVaccineCampaignUUID());
            pojo.setDate(b.getDate());
            pojo.setLocation(b.getLocation());

            // Add POJO to file JSON array
            serviceNode = Json.addPojoToJsonArray(pojo, serviceNode);
        }

        // Convert node into String
        var output = Json.prettyPrint(serviceNode);

        // Write said JSON String to file
        jio.writeJsonToFile(output, "Bookings");
    }

    /**
     * Initialize BookingService with contents from Bookings.json
     * (located in resources)
     */
    public static void initBookingServiceFromJson() throws JsonProcessingException {
        var jio = new JsonIOHandler();
        var fileOutput = jio.jsonToString("Bookings");
        var node = Json.parse(fileOutput);

        for (var bookingIt = node.elements(); bookingIt.hasNext();) {
            var booking = bookingIt.next();

            var pojo = Json.fromJson(booking, BookingPOJO.class);

            var newBooking = new Booking(pojo.getPatient(), pojo.getVaccineCampaignUUID(), pojo.getDate(),
                    pojo.getLocation());

            BookingService.addBooking(newBooking);
        }

    }

    /**
     * Initialize CitizenService with contents from Citizens.json
     */
    public static void initCitizenServiceFromJson() throws JsonProcessingException {
        var jio = new JsonIOHandler();
        var fileOutput = jio.jsonToString("Citizens");
        var node = Json.parse(fileOutput);

        for (var bookingIt = node.elements(); bookingIt.hasNext();) {
            var booking = bookingIt.next();

            var pojo = Json.fromJson(booking, PatientPOJO.class);

            var newCitizen = new Patient(pojo.getFirstName(), pojo.getLastName(), pojo.getFiscalCode(),
                    pojo.getBirthPlace(), pojo.getBirthDay(), pojo.getSex(), pojo.isAtHighRisk(), pojo.isHealthCareWorker(),
                    pojo.isSchoolWorker(), pojo.isLawEnforcementWorker(), pojo.isCaretaer(),
                    pojo.isCohabiting());

            CitizenService.addCitizen(newCitizen);
        }
    }

}
