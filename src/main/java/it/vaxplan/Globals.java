package it.vaxplan;

import it.vaxplan.backend.service.PatientService;
import it.vaxplan.backend.service.VaccineCampaignService;

/**
 * This class contains some global variables that are shared throughout the program.
 */
public class Globals {

    /**
     * Vaccination campaign service, used to hold a Collection of running vaccine
     * campaigns
     */
    public static VaccineCampaignService vaccineCampaignService;

    /**
     * Vaccination campaign service, used to hold a Collection of patients
     * registered to the system
     */
    public static PatientService patientService;

}
