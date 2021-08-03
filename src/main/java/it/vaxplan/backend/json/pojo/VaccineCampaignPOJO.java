package it.vaxplan.backend.json.pojo;

import it.vaxplan.backend.Vaccine;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

public class VaccineCampaignPOJO {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Vaccine vaccine;

    @Getter
    @Setter
    private int availableDoses;

    @Getter
    @Setter
    private LocalDate startDate;

    @Getter
    @Setter
    private LocalDate endDate;

    @Getter
    @Setter
    private LocalTime dailyStartTime;

    @Getter
    @Setter
    private LocalTime dailyEndTime;

}
