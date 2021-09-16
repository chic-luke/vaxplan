package it.vaxplan.backend.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.vaxplan.backend.Patient;
import it.vaxplan.backend.exceptions.CitizenNotFoundException;
import it.vaxplan.backend.json.JsonUtils;
import it.vaxplan.backend.service.CitizenService;
import it.vaxplan.backend.service.PatientService;

public class IDChecker {

    /**
     * This is an utility class, it's not meant to be instantiated.
     */
    private IDChecker() {
        throw new IllegalArgumentException("Utility class");
    }

    /**
     * Checks of a given Italian fiscal code is valid according to a regular expression
     * @param fiscalCode Fiscal code to validate
     * @return Whether the input fiscal code is valid
     */
    public static boolean isFiscalCodeValid(String fiscalCode) {
        return fiscalCode.matches("^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$");
    }

    /**
     * Looks up an input fiscal code in the User.json file to check whether the
     * code is registered
     * @param fiscalCode Fiscal code to look up in the file
     * @return Whether an object with the matching fiscal code was found
     * @throws JsonProcessingException If JSON processing fails
     */
    public static boolean isInDemographic(String fiscalCode) throws JsonProcessingException {
        return JsonUtils.lookUp("Citizens", "fiscalCode", fiscalCode);
    }

    public static boolean isRegistered(String fiscalCode) throws JsonProcessingException {
        return JsonUtils.lookUp("Patient", "fiscalCode", fiscalCode);
    }

    public static boolean isRegisterDataCorrect(Patient patient) throws JsonProcessingException {
        if (!isInDemographic(patient.getFiscalCode())) {
            return false;
        }

        var citizen = getCitizen(patient.getFiscalCode());

        return patient.getFirstName().equals(citizen.getFirstName()) &&
                patient.getLastName().equals(citizen.getLastName()) &&
                patient.getHealthCardNumber().equals(citizen.getHealthCardNumber()) &&
                patient.getBirthPlace().equals(citizen.getBirthPlace()) &&
                patient.getBirthDay().equals(citizen.getBirthDay()) &&
                patient.getSex().equals(citizen.getSex());
    }

    /**
     * Returns a citizen according to fiscal code to caller.
     * @param fiscalCode Key to look citizens up
     * @return Corresponding citizen if found
     */
    public static Patient getCitizen(String fiscalCode) {
        for (Patient citizen: CitizenService.getCitizens()) {
            if (citizen.getFiscalCode().equals(fiscalCode))
                return citizen;
        }

        throw new CitizenNotFoundException();
    }

    /**
     * Checks whether a citizen is already registered to the system using the fiscal code to compare.
     * @param fiscalCode Fiscal code to check in PatientService
     * @return Whether citizen with fiscal code fiscalCode is registered
     */
    public static boolean isCitizenRegistered(String fiscalCode) {
        for (Patient patient: PatientService.getPatients()) {
            if (patient.getFiscalCode().equals(fiscalCode))
                return true;
        }

        return false;
    }

}
