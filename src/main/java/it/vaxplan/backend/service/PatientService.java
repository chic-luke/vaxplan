package it.vaxplan.backend.service;

import it.vaxplan.backend.Patient;
import lombok.Getter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PatientService {

    @Getter
    private final Set<Patient> patients = new HashSet<>();

    /**
     * Add a patient to the set of patients
     * @param patient Patient to add
     */
    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    /**
     * Remove a patient from the set of patients
     * @param patient Patient to remove
     */
    public void removePatient(Patient patient) {
        patients.remove(patient);
    }

    /**
     * Add all the Patients in a Collection to the set of patients
     * @param patientsToAdd Collection of Patients to add
     */
    public void addPatients(Collection<Patient> patientsToAdd) {
        patients.addAll(patientsToAdd);
    }

    /**
     * Remove all the Patients in a Collection from the set of patients
     * @param patientsToRemove Collection of Patients to remove
     */
    public void removePatients(Collection<Patient> patientsToRemove) {
        patients.removeAll(patientsToRemove);
    }

}
