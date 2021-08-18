package it.vaxplan.backend;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Booking {

    private final UUID uuid = UUID.randomUUID();

    private Patient patient;

    private UUID campaignUUID;

    private LocalDate date;

    private LocalDateTime time;

    private VaccineSite location;

    @Override
    public String toString() {
        return "Patient: " + patient.getFirstName() + " " + patient.getLastName() + "\n" +
                "Campaign: " + campaignUUID + "\n" +
                "Date: " + date + "\n" +
                "TIme: " + time + "\n" +
                "Site: " + location + "\n";
    }

}
