package it.vaxplan.backend;

public enum PatientCategories {

    AT_HIGH_RISK("A elevata fragilità"),
    AT_RISK("A rischio"),
    AGE_OVER_80("Over 80"),
    AGE_70_79("70-79 anni"),
    AGE_60_69("60-69 anni"),
    CARETAKER("Caretaker"),
    COHABITING("Convivente"),
    HEALTH_WORKER("Personale sanitario"),
    SCHOOL_WORKER("Personale scolastico"),
    LAW_ENFORCEMENT_WORKER("Forze dell'ordine");

    private final String string;

    PatientCategories(String name) {
        string = name;
    }

    @Override
    public String toString() {
        return string;
    }

}
