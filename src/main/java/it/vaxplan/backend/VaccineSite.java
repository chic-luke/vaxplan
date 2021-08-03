package it.vaxplan.backend;

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

    @Override
    public String toString() {
        return name;
    }

}
