package it.vaxplan.backend;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

public enum Vaccine {
    CHICKENPOX("Anti-varicella"),
    COVID("Anti-Covid-19"),
    MEASLES("Anti-morbillo"),
    SARS("SARS"),
    FLU("Influenza"),
    POLIO("Anti-poliomielitica"),
    DIPHTHERIA("Anti-difterica"),
    TETHANUS("Anti-tetanica"),
    HEPATITIS_B("Anti-epatite B"),
    PERTUSSIS("Anti-pertosse"),
    HAEMOPHILUS("Anti-Haemophilus influenzae tipo b"),
    RUBELLA("Anti-rosolia"),
    MUMPS("Anti-parotite"),
    MENINGOCOCCAL_B("Anti-meningococcica B"),
    MENINGOCOCCAL_C("Anti-meningococcica C"),
    PNEUMOCOCCAL("Anti-pneumococcica"),
    ROTAVIRUS("Anti-rotavirus");


    @Getter
    @NotBlank
    private final String virusName;

    Vaccine(String virusName) {
        this.virusName = virusName;
    }

    /**
     * Return a human-readable String associated to each enum element as String
     * @return virusName field of enum element
     */
    @Override
    public String toString() {
        return this.virusName;
    }
}
