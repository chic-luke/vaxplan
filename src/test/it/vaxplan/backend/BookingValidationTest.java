package it.vaxplan.backend;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class BookingValidationTest {

    @Test
    void patientEligibilityCheckerSuccess() {

        var p0 = Patient.builder()
                .firstName("Mario")
                .lastName("Rossi")
                .atHighRisk(false)
                .caretaker(true)
                .cohabiting(true)
                .healthCareWorker(false)
                .schoolWorker(true)
                .lawEnforcementWorker(false)
                .build();

        var p1 = Patient.builder()
                .firstName("Elena")
                .lastName("Bianchi")
                .atHighRisk(true)
                .caretaker(false)
                .cohabiting(true)
                .healthCareWorker(false)
                .schoolWorker(false)
                .lawEnforcementWorker(false)
                .build();

        var p2 = Patient.builder()
                .firstName("Maria")
                .lastName("Bianchi")
                .atHighRisk(false)
                .caretaker(false)
                .cohabiting(false)
                .healthCareWorker(true)
                .schoolWorker(true)
                .lawEnforcementWorker(false)
                .build();

        var p3 = Patient.builder()
                .firstName("Maria")
                .lastName("Bianchi")
                .atHighRisk(false)
                .caretaker(false)
                .cohabiting(false)
                .healthCareWorker(false)
                .schoolWorker(false)
                .lawEnforcementWorker(false)
                .build();

        var highRisk = new HashSet<PatientCategories>();
        highRisk.add(PatientCategories.AT_HIGH_RISK);

        var campaignHighRisk = new VaccineCampaign("Campagna esempio", Vaccine.COVID, 20, LocalDate.of(2021, 1, 1),
                LocalDate.of(2021, 2, 2), LocalTime.of(9, 0), LocalTime.of(20, 0),
                new HashSet<>(), highRisk, new LinkedList<>());


        var critWorkers = new HashSet<PatientCategories>();
        critWorkers.add(PatientCategories.HEALTH_WORKER);
        critWorkers.add(PatientCategories.SCHOOL_WORKER);
        critWorkers.add(PatientCategories.LAW_ENFORCEMENT_WORKER);

        var campaignWorkers = new VaccineCampaign("Campagna esempio", Vaccine.COVID, 20, LocalDate.of(2021, 1, 1),
                LocalDate.of(2021, 2, 2), LocalTime.of(9, 0), LocalTime.of(20, 0),
                new HashSet<>(), critWorkers, new LinkedList<>());

        var allCategories = new HashSet<PatientCategories>();
        allCategories.addAll(Arrays.asList(PatientCategories.values()));

        var campaignEveryone = new VaccineCampaign("Campagna esempio", Vaccine.COVID, 20, LocalDate.of(2021, 1, 1),
                LocalDate.of(2021, 2, 2), LocalTime.of(9, 0), LocalTime.of(20, 0),
                new HashSet<>(), allCategories, new LinkedList<>());


        assertFalse(BookingValidation.isPatientEligible(p0, campaignHighRisk));
        assertTrue(BookingValidation.isPatientEligible(p1, campaignHighRisk));
        assertTrue(BookingValidation.isPatientEligible(p0, campaignWorkers));
        assertFalse(BookingValidation.isPatientEligible(p1, campaignWorkers));
        assertTrue(BookingValidation.isPatientEligible(p2, campaignWorkers));
        assertFalse(BookingValidation.isPatientEligible(p3, campaignWorkers));
        assertTrue(BookingValidation.isPatientEligible(p0, campaignEveryone));
        assertTrue(BookingValidation.isPatientEligible(p1, campaignEveryone));
        assertTrue(BookingValidation.isPatientEligible(p2, campaignEveryone));
        assertTrue(BookingValidation.isPatientEligible(p3, campaignEveryone));
    }
}