package it.vaxplan.backend;

public class IDChecker {

    public static boolean isValidID(String idCode) {
        //if(idCode.equals("/^(?:[A-Z][AEIOU][AEIOUX]|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}(?:[\\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04L Q][1-9 MNP-V]|[15 MR][\\dLMNP-V]|[26 NS][0-8L MNP-U])|[DHPS][37 PT][0L]|[ACELMRT][37 PT][01L M]|[AC-EHLMPR-T][26 NS][9 V])|(?:[02468L NQSU][048L QU]|[13579 MPRTV][26 NS])B[26 NS][9 V])(?:[A-MZ][1-9 MNP-V][\\dLMNP-V]{2}|[A-M][0L](?:[1-9 MNP-V][\\dLMNP-V]|[0L][1-9 MNP-V]))[A-Z]$/i"))
        return true;
    }

    public static boolean isIDRegistered(String idcode) {
        return false;
    }

}
