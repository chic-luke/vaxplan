package it.vaxplan.backend.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import it.vaxplan.backend.VaccineCampaign;
import it.vaxplan.backend.json.pojo.VaccineCampaignPOJO;
import it.vaxplan.backend.service.VaccineCampaignService;

import java.util.HashSet;

public class VaccineCampaignFromJson {

    /**
     * This is an utility class, it's not meant to be instantiated.
     */
    private VaccineCampaignFromJson() {
        throw new IllegalArgumentException("Utility class");
    }

    /**
     * Initialize VaccineCampaignService with a set of Patients with information obtained
     * by parsing VaccineCampaign.json
     * @throws JsonProcessingException If JSON processing fails
     */
    public static void getVaccineCampaignsFromJson() throws JsonProcessingException {
        var jsonInputHandler = new JsonIOHandler();
        var jsonAsString = jsonInputHandler.jsonToString("VaccineCampaign");

        var node = Json.parse(jsonAsString);

        // Temporary VaccineCampaignService until a real one is implemented
        for (var campaignIt = node.elements(); campaignIt.hasNext();) {
            var campaign = campaignIt.next();
            // Handle below
            VaccineCampaignService.addCampaign(createVaccineCampaign(campaign));
        }
    }

    /**
     * Create and return a VaccineCampaign object from a JsonNode
     * @param node JsonNode to bind to POJO class
     * @return Resulting VaccineCampaign object
     * @throws JsonProcessingException If JSON processing fails
     */
    public static VaccineCampaign createVaccineCampaign(JsonNode node) throws JsonProcessingException {
        var pojo = Json.fromJson(node, VaccineCampaignPOJO.class);

        var campaign = new VaccineCampaign(pojo.getName(), pojo.getVaccine(), pojo.getAvailableDoses(),
                pojo.getStartDate(), pojo.getEndDate(), pojo.getDailyStartTime(), pojo.getDailyEndTime(),
                new HashSet<>(), new HashSet<>(), pojo.getListOfBookings());

        System.out.println(campaign);

        return campaign;
    }

}
