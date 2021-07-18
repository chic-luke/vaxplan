package it.vaxplan.model;

import java.time.ZonedDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Booking {

    private final UUID uuid = UUID.randomUUID();

    @NotNull
    private Patient patient;

    @NotNull
    private Vaccine vaccine;

    @NotNull
    private ZonedDateTime date;

    @NotBlank
    private String location;

}
