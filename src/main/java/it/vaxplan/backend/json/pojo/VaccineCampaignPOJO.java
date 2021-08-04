package it.vaxplan.backend.json.pojo;

import it.vaxplan.backend.PatientCategories;
import it.vaxplan.backend.Vaccine;
import it.vaxplan.backend.VaccineSite;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

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

    @Getter
    @Setter
    private Set<VaccineSite> availableSites;

    @Getter
    @Setter
    private Set<PatientCategories> patientCategories;

}
