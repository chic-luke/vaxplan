package it.vaxplan.backend;

import it.vaxplan.backend.service.VaccineCampaignService;

import java.util.HashSet;
import java.util.Set;

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

        if (patientCategories == null) {
            throw new IllegalArgumentException("patientCategories is null!");
        }

        if (patientCategories.contains(PatientCategories.EVERYONE)) {
            return true;
        }

        if (patientCategories.contains(PatientCategories.AT_HIGH_RISK) && patient.isAtHighRisk()) {
                return true;
        }

        if (patientCategories.contains(PatientCategories.ADULT) && patient.isAdult()) {
                return true;
        }

        if (patientCategories.contains(PatientCategories.AGE_OVER_80) && patient.getIntAge() >= 80) {
                return true;
        }

        if (patientCategories.contains(PatientCategories.AGE_70_79) && (patient.getIntAge() >= 70 && patient.getIntAge() <= 79)) {
                return true;
        }

        if (patientCategories.contains(PatientCategories.AGE_60_69) && (patient.getIntAge() >= 60 && patient.getIntAge() <= 69)) {
                return true;
        }

        if (patientCategories.contains(PatientCategories.CARETAKER) && patient.isCaretaker()) {
                return true;
        }

        if (patientCategories.contains(PatientCategories.COHABITING) && patient.isCohabiting()) {
                return true;
        }

        if (patientCategories.contains(PatientCategories.HEALTH_WORKER) && patient.isHealthCareWorker()) {
                return true;
        }

        if (patientCategories.contains(PatientCategories.SCHOOL_WORKER) && patient.isSchoolWorker()) {
                return true;
        }

        return patientCategories.contains(PatientCategories.LAW_ENFORCEMENT_WORKER) && patient.isLawEnforcementWorker();
    }

    /**
     * Return a Set of vaccine campaigns for whom the patient is eligible
     * @param patient Patient who needs to check what vaccine campaigns they are eligible for
     * @return Set of VaccineCampaign which patient is eligible for
     */
    public static Set<VaccineCampaign> availableCampaignsForPatient(Patient patient) {
        var availableCampaigns = new HashSet<VaccineCampaign>();

        for (VaccineCampaign c: VaccineCampaignService.getCampaigns()) {
            if (isPatientEligible(patient, c)) {
                availableCampaigns.add(c);
            }
        }

        return availableCampaigns;
    }

}
