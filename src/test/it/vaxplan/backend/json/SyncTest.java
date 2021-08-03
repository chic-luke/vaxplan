package it.vaxplan.backend.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.vaxplan.backend.Vaccine;
import it.vaxplan.backend.VaccineCampaign;
import it.vaxplan.backend.service.VaccineCampaignService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class SyncTest {

    @Test
    void vaccineCampaignServiceToFileSuccess() throws IOException {
        var newCampaign = VaccineCampaign.builder()
                .name("Example")
                .availableDoses(100)
                .dailyStartTime(LocalTime.of(8, 30))
                .dailyEndTime(LocalTime.of(20, 30))
                .startDate(LocalDate.of(2021, 8, 1))
                .endDate(LocalDate.of(2022, 1, 1))
                .vaccine(Vaccine.COVID)
                .build();

        var newCampaign2 = VaccineCampaign.builder()
                .name("COVID")
                .availableDoses(111)
                .dailyStartTime(LocalTime.of(8, 30))
                .dailyEndTime(LocalTime.of(20, 30))
                .startDate(LocalDate.of(2021, 8, 1))
                .endDate(LocalDate.of(2022, 1, 1))
                .vaccine(Vaccine.COVID)
                .build();

        VaccineCampaignService.addCampaign(newCampaign);
        VaccineCampaignService.addCampaign(newCampaign2);

        Sync.writeVaccineCampaignServiceToJson();
    }
}