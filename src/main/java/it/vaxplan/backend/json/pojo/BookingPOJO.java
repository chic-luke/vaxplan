package it.vaxplan.backend.json.pojo;

import it.vaxplan.backend.Patient;
import it.vaxplan.backend.VaccineCampaign;
import it.vaxplan.backend.VaccineSite;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

public class BookingPOJO {

    @Getter
    @Setter
    private Patient patient;

    @Getter
    @Setter
    private UUID vaccineCampaignUUID;

    @Getter
    @Setter
    private LocalDate date;

    @Getter
    @Setter
    private VaccineSite location;

}
