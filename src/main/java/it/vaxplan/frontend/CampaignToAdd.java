package it.vaxplan.frontend;

import it.vaxplan.backend.Vaccine;
import it.vaxplan.backend.VaccineCampaign;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalTime;


public class CampaignToAdd {

    public static VaccineCampaign campaign;

    @Getter
    @Setter
    private static String name;

    @Getter
    @Setter
    private static Vaccine vaccine;

    @Getter
    @Setter
    public static Integer availableDoses;

    @Getter
    @Setter
    public static LocalDate startDate;

    @Getter
    @Setter
    public static LocalDate endDate;

    @Getter
    @Setter
    public static LocalTime dailyStartTime;
    
    @Getter
    @Setter
    public static LocalTime dailyEndTime;

}
