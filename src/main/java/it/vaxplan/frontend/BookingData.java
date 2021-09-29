package it.vaxplan.frontend;

import it.vaxplan.backend.Patient;
import it.vaxplan.backend.VaccineCampaign;
import it.vaxplan.backend.VaccineSite;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingData {

    private BookingData() {
        throw new IllegalArgumentException("Utility class");
    }

    @Getter
    @Setter
    private static Patient patient;

    @Getter
    @Setter
    private static VaccineCampaign campaign;

    @Getter
    @Setter
    private static VaccineSite site;

    @Getter
    @Setter
    private static LocalDate day;

    @Getter
    @Setter
    private static LocalDateTime time;

}
