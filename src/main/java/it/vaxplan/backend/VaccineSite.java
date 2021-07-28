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
public class VaccineSite implements Comparable<String> {

    @NotNull
    private String name;

    @NotNull
    private String city;

    @Override
    public int compareTo(String s) {
        return 0;
    }
}
