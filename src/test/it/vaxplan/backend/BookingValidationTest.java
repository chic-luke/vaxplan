package it.vaxplan.backend;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

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

        var campaignHighRisk = VaccineCampaign.builder()
                .name("High Risk Only")
                .patientCategories(highRisk)
                .build();

        var critWorkers = new HashSet<PatientCategories>();
        critWorkers.add(PatientCategories.HEALTH_WORKER);
        critWorkers.add(PatientCategories.SCHOOL_WORKER);
        critWorkers.add(PatientCategories.LAW_ENFORCEMENT_WORKER);

        var campaignWorkers = VaccineCampaign.builder()
                .name("Critical workers only")
                .patientCategories(critWorkers)
                .build();

        var allCategories = new HashSet<PatientCategories>();
        allCategories.addAll(Arrays.asList(PatientCategories.values()));

        var campaignEveryone = VaccineCampaign.builder()
                .name("Everyone")
                .patientCategories(allCategories)
                .build();

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