package it.vaxplan.backend.json;

import com.fasterxml.jackson.databind.JsonNode;
import it.vaxplan.backend.VaccineCampaign;
import it.vaxplan.backend.json.pojo.VaccineCampaignPOJO;
import it.vaxplan.backend.service.VaccineCampaignService;

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

            // Add POJO to file JSON array
            serviceNode = Json.addPojoToJsonArray(pojo, serviceNode);
        }

        // Convert node into String
        var output = Json.prettyPrint(serviceNode);

        // Write said JSON String to file
        jio.writeJsonToFile(output, "VaccineCampaign");
    }

}
