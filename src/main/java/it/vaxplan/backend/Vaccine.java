package it.vaxplan.backend;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

public enum Vaccine {
    CHICKENPOX("Varicella", Patient::isAdult),
    COVID("Covid-19", u -> u.getIntAge() > 23 && u.isHealthCareWorker()),
    MEASLES("Non lo so", null);

    @Getter
    @NotBlank
    private final String virusName;

    private final Function<Patient, Boolean> patientElegibilityChecker;

    Vaccine(String virusName, Function<Patient, Boolean> patientElegibilityChecker) {
        this.virusName = virusName;
        this.patientElegibilityChecker = patientElegibilityChecker;
    }

    public boolean isPatientElegible(Patient patient) {
        return Objects.isNull(patientElegibilityChecker) || patientElegibilityChecker.apply(patient);
    }

    public List<Vaccine> getVaccinesByVirusName(String virusName) {
        return Stream.of(Vaccine.values()).filter(v -> v.getVirusName().equals(virusName)).collect(Collectors.toList());
    }
}
