package it.vaxplan.backend;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

public enum Vaccine {
    CHICKENPOX("Anti-varicella", Patient::isAdult),
    COVID("Covid-19", u -> u.getIntAge() > 23 && u.isHealthCareWorker()),
    MEASLES("Anti-morbillo", null),
    SARS("SARS", null),
    FLU("Influenza", null),
    POLIO("Anti-poliomielitica", null),
    DIPHTHERIA("Anti-difterica", null),
    TETHANUS("Anti-tetanica", null),
    HEPATITIS_B("Anti-epatite B", null),
    PERTUSSIS("Anti-pertosse", null),
    HAEMOPHILUS("Anti-Haemophilus influenzae tipo b", null),
    RUBELLA("Anti-rosolia", null),
    MUMPS("Anti-parotite", null),
    MENINGOCOCCAL_B("Anti-meningococcica B", null),
    MENINGOCOCCAL_C("Anti-meningococcica C", null),
    PNEUMOCOCCAL("Anti-pneumococcica", null),
    ROTAVIRUS("Anti-rotavirus", null);


    @Getter
    @NotBlank
    private final String virusName;

    private final Function<Patient, Boolean> patientElegibilityChecker;

    Vaccine(String virusName, Function<Patient, Boolean> patientElegibilityChecker) {
        this.virusName = virusName;
        this.patientElegibilityChecker = patientElegibilityChecker;
    }

    /**
     * Return a human-readable String associated to each enum element as String
     * @return virusName field of enum element
     */
    @Override
    public String toString() {
        return this.virusName;
    }

    /**
     * Checks whether a Patient is eligible for a Vaccine
     * @param patient Patient to check eligibility for
     * @return Whether patient is eligible
     */
    public boolean isPatientElegible(Patient patient) {
        return Objects.isNull(patientElegibilityChecker) || patientElegibilityChecker.apply(patient);
    }

    /**
     * Get a List of all the available vaccines for a given virus
     * @param virusName Name of the virus to return all available vaccines for
     * @return List of available vaccines for a given virus
     */
    public List<Vaccine> getVaccinesByVirusName(String virusName) {
        return Stream.of(Vaccine.values()).filter(v -> v.getVirusName().equals(virusName)).collect(Collectors.toList());
    }
}
