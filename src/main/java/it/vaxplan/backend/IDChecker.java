package it.vaxplan.backend;

public class IDChecker {

    public static boolean isFiscalCodeValid(String idCode) {
        return idCode.matches("^[A-Z][A-Z][A-Z][A-Z][A-Z][A-Z][0-9][0-9][A-Z][0-9][0-9][A-Z][0-9][0-9][0-9][A-Z]$");
    }

    public static boolean isIDRegistered(String idcode) {
        return false;
    }

}
