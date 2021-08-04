package it.vaxplan.backend;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Booking {

    private final UUID uuid = UUID.randomUUID();

    private Patient patient;

    private UUID vaccineCampaignUUID;

    private LocalDate date;

    private VaccineSite location;

}
