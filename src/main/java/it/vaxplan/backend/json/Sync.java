package it.vaxplan.backend.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import it.vaxplan.backend.Patient;
import it.vaxplan.backend.PatientCategories;
import it.vaxplan.backend.VaccineCampaign;
import it.vaxplan.backend.json.pojo.VaccineCampaignPOJO;
import it.vaxplan.backend.service.BookingService;
import it.vaxplan.backend.service.VaccineCampaignService;

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

}
