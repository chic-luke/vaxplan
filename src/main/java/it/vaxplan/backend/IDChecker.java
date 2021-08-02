package it.vaxplan.backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.vaxplan.backend.json.JsonUtils;

public class IDChecker {

    public static boolean isFiscalCodeValid(String idCode) {
        return idCode.matches("^[A-Z][A-Z][A-Z][A-Z][A-Z][A-Z][0-9][0-9][A-Z][0-9][0-9][A-Z][0-9][0-9][0-9][A-Z]$");
    }

    public static boolean isIDRegistered(String idcode) throws JsonProcessingException {
        return JsonUtils.lookUp("User", "fiscalCode", idcode);
    }

}
