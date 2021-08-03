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
        return idCode.matches("^(?:[A-Z][AEIOU][AEIOUX]|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}(?:[\\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04L Q][1-9 MNP-V]|[15 MR][\\dLMNP-V]|[26 NS][0-8L MNP-U])|[DHPS][37 PT][0L]|[ACELMRT][37 PT][01L M]|[AC-EHLMPR-T][26 NS][9 V])|(?:[02468L NQSU][048L QU]|[13579 MPRTV][26 NS])B[26 NS][9 V])(?:[A-MZ][1-9 MNP-V][\\dLMNP-V]{2}|[A-M][0L](?:[1-9 MNP-V][\\dLMNP-V]|[0L][1-9 MNP-V]))[A-Z]$");
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
