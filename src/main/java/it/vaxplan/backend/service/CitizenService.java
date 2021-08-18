package it.vaxplan.backend.service;

import it.vaxplan.backend.Patient;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

public class CitizenService {

    /**
     * This is an utility class, it's not meant to be instantiated.
     */
    private CitizenService() {
        throw new IllegalArgumentException("Utility class (public service)");
    }

    @Getter
    private static final Set<Patient> citizens = new HashSet<>();

    /**
     * Add a citizen (of type Patient) to the list of citizens
     * @param citizen Citizen to add
     */
    public static void addCitizen(Patient citizen) {
        citizens.add(citizen);
    }

}
