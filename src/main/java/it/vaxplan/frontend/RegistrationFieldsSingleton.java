package it.vaxplan.frontend;

public class RegistrationFieldsSingleton {

    /**
     * This is an utility class, it's not meant to be instantiated.
     */
    private RegistrationFieldsSingleton() {
        throw new IllegalArgumentException("Utility class");
    }

    public static RegistrationFields registrationFields;

}
