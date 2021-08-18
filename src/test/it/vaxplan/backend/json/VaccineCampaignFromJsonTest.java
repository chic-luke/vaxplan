package it.vaxplan.backend.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VaccineCampaignFromJsonTest {

    @Test
    void vaccineCampaignJsonToServiceSuccess() throws JsonProcessingException {
        VaccineCampaignFromJson.getVaccineCampaignsFromJson();
    }

}