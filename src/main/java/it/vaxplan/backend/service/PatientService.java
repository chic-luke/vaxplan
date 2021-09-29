package it.vaxplan.backend.service;

import it.vaxplan.backend.Patient;
import lombok.Getter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PatientService {

    /**
     * This is an utility class, it's not meant to be instantiated.
     */
    private PatientService() {
        throw new IllegalArgumentException("Utility class (global service)");
    }

    @Getter
    private static final Set<Patient> patients = new HashSet<>();

    /**
     * Add a patient to the set of patients
     * @param patient Patient to add
     */
    public static void addPatient(Patient patient) {
        patients.add(patient);
    }

    /**
     * Remove a patient from the set of patients
     * @param patient Patient to remove
     */
    public static void removePatient(Patient patient) {
        patients.remove(patient);
    }

    /**
     * Add all the Patients in a Collection to the set of patients
     * @param patientsToAdd Collection of Patients to add
     */
    public static void addPatients(Collection<Patient> patientsToAdd) {
        patients.addAll(patientsToAdd);
    }

    /**
     * Remove all the Patients in a Collection from the set of patients
     * @param patientsToRemove Collection of Patients to remove
     */
    public static void removePatients(Collection<Patient> patientsToRemove) {
        patients.removeAll(patientsToRemove);
    }

}
