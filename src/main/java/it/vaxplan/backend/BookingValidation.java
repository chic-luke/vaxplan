package it.vaxplan.backend;

public class BookingValidation {

    /**
     * This is an utility class, it's not meant to be instantiated.
     */
    private BookingValidation() {
        throw new IllegalArgumentException("Utility class");
    }

    /**
     * Checks whether a Patient is eligible for a given Vaccine Campaign
     * @param patient Patient to check
     * @param campaign Vaccine campaign to check against
     * @return Whether patient is eligible
     */
    public static boolean isPatientEligible(Patient patient, VaccineCampaign campaign) {

        var patientCategories = campaign.getPatientCategories();

        if (patientCategories.contains(PatientCategories.EVERYONE)) {
            return true;
        }

        if (patientCategories.contains(PatientCategories.AT_HIGH_RISK)) {
            if (patient.isAtHighRisk())
                return true;
        }

        if (patientCategories.contains(PatientCategories.ADULT)) {
            if (patient.isAdult())
                return true;
        }

        if (patientCategories.contains(PatientCategories.AGE_OVER_80)) {
            if (patient.getIntAge() >= 80)
                return true;
        }

        if (patientCategories.contains(PatientCategories.AGE_70_79)) {
            if (patient.getIntAge() >= 70 && patient.getIntAge() <= 79)
                return true;
        }

        if (patientCategories.contains(PatientCategories.AGE_60_69)) {
            if (patient.getIntAge() >= 60 && patient.getIntAge() <= 69)
                return true;
        }

        if (patientCategories.contains(PatientCategories.CARETAKER)) {
            if (patient.isCaretaker())
                return true;
        }

        if (patientCategories.contains(PatientCategories.COHABITING)) {
            if (patient.isCohabiting())
                return true;
        }

        if (patientCategories.contains(PatientCategories.HEALTH_WORKER)) {
            if (patient.isHealthCareWorker())
                return true;
        }

        if (patientCategories.contains(PatientCategories.SCHOOL_WORKER)) {
            if (patient.isSchoolWorker())
                return true;
        }

        if (patientCategories.contains(PatientCategories.LAW_ENFORCEMENT_WORKER)) {
            if (patient.isLawEnforcementWorker())
                return true;
        }


        return false;
    }

}
