package it.vaxplan.backend.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.vaxplan.backend.Patient;
import it.vaxplan.backend.exceptions.CitizenNotFoundException;
import it.vaxplan.backend.json.JsonUtils;
import it.vaxplan.backend.service.CitizenService;

public class IDChecker {

    /**
     * This is an utility class, it's not meant to be instantiated.
     */
    private IDChecker() {
        throw new IllegalArgumentException("Utility class");
    }

    /**
     * Checks of a given Italian fiscal code is valid according to a regular expression
     * @param idCode Fiscal code to validate
     * @return Whether the input fiscal code is valid
     */
    public static boolean isFiscalCodeValid(String idCode) {
        return idCode.matches("^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$");
    }

    /**
     * Looks up an input fiscal code in the User.json file to check whether the
     * code is registered
     * @param idcode Fiscal code to look up in the file
     * @return Whether an object with the matching fiscal code was found
     * @throws JsonProcessingException If JSON processing fails
     */
    public static boolean isInDemographic(String idcode) throws JsonProcessingException {
        return JsonUtils.lookUp("User", "fiscalCode", idcode);
    }

    public static boolean isRegistered(String idcode) throws JsonProcessingException {
        return JsonUtils.lookUp("RegisteredUsers", "fiscalCode", idcode);
    }

    public static boolean isRegisterDataCorrect(Patient patient) throws JsonProcessingException {
        if (!isInDemographic(patient.getFiscalCode())) {
            return false;
        }

        var citizen = getCitizen(patient.getFiscalCode());

        return patient.getFirstName().equals(citizen.getFirstName()) &&
                patient.getLastName().equals(citizen.getLastName()) &&
                patient.getBirthPlace().equals(citizen.getBirthPlace()) &&
                patient.getBirthDay().equals(citizen.getBirthDay()) &&
                patient.getSex().equals(citizen.getSex());
    }

    /**
     * Returns a citizen according to fiscal code to caller.
     * @param idCode Key to look citizens up
     * @return Corresponding citizen if found
     */
    public static Patient getCitizen(String idCode) {
        for (Patient citizen: CitizenService.getCitizens()) {
            if (citizen.getFiscalCode().equals(idCode))
                return citizen;
        }

        throw new CitizenNotFoundException();
    }

}
