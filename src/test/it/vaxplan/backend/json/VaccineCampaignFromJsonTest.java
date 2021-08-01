package it.vaxplan.backend.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VaccineCampaignFromJsonTest {

    @Test
    void getVaccineCampaignsFromJson() throws JsonProcessingException {
        VaccineCampaignFromJson.getVaccineCampaignsFromJson();
    }

}