package it.vaxplan.backend.json.pojo;

import it.vaxplan.backend.Patient;
import it.vaxplan.backend.VaccineSite;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class BookingPOJO {

    @Getter
    @Setter
    private Patient patient;

    @Getter
    @Setter
    private UUID campaignUUID;

    @Getter
    @Setter
    private LocalDate date;

    @Getter
    @Setter
    private LocalDateTime time;

    @Getter
    @Setter
    private VaccineSite location;

}
