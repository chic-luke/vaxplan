package it.vaxplan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class VaccineSite {

    @NotNull
    private String name;

    @NotNull
    private String city;

}
