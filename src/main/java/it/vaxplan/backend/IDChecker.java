package it.vaxplan.backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.vaxplan.backend.json.JsonUtils;

public class IDChecker {

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
    public static boolean isIDRegistered(String idcode) throws JsonProcessingException {
        return JsonUtils.lookUp("User", "fiscalCode", idcode);
    }

}
