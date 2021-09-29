package it.vaxplan.frontend;

import it.vaxplan.backend.Patient;

public class RegistrationData {

    /**
     * This is an utility class, it's not meant to be instantiated.
     */
    private RegistrationData() {
        throw new IllegalArgumentException("Utility class");
    }

    public static RegistrationFields fields;

    public static Patient registeredPatient;

}
