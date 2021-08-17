package it.vaxplan.backend.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.vaxplan.backend.*;
import it.vaxplan.backend.service.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;

class SyncTest {

    @Test
    void vaccineCampaignServiceToFileSuccess() throws IOException {
        var newCampaign = new VaccineCampaign("Example", Vaccine.COVID, 100, LocalDate.of(2021, 8, 1),
                LocalDate.of(2022, 1, 1), LocalTime.of(9, 0), LocalTime.of(20, 0));


        var newCampaign2 = new VaccineCampaign("COVID", Vaccine.COVID, 111, LocalDate.of(2021, 8, 1),
                LocalDate.of(2022, 1, 1), LocalTime.of(8, 30),
                LocalTime.of(20, 30));

        var categories = new HashSet<PatientCategories>();
        categories.add(PatientCategories.AT_HIGH_RISK);
        categories.add(PatientCategories.AGE_OVER_80);

        var sites = new HashSet<VaccineSite>();
        var site = new VaccineSite("Ambulatorio");
        sites.add(site);

        var testCampaign = new VaccineCampaign("Test Case Campaign", Vaccine.COVID, 100, LocalDate.of(2021, 2, 3),
                LocalDate.of(2021, 4, 10), LocalTime.of(8, 30), LocalTime.of(20, 30) );

        VaccineCampaignService.addCampaign(newCampaign);
        VaccineCampaignService.addCampaign(newCampaign2);
        VaccineCampaignService.addCampaign(testCampaign);

        Sync.writeVaccineCampaignServiceToJson();
    }

    @Test
    void initVaccineCampaignServiceSuccess() throws JsonProcessingException {
        Sync.initVaccineCampaignServiceFromJson();
        assertFalse(VaccineCampaignService.isEmpty());
    }

    @Test
    void writePatientServiceToJsonSuccess() throws IOException {
        var patient0 = Patient.builder()
                .firstName("Armando")
                .lastName("Bianchi")
                .birthDay(LocalDate.of(2000, 2, 10))
                .sex(Sex.MALE)
                .fiscalCode("BNCRND00B10F205G")
                .healthCareWorker(true)
                .build();

        var patient1 = Patient.builder()
                .firstName("Elena")
                .lastName("Rossi")
                .birthDay(LocalDate.of(1999, 1, 26))
                .sex(Sex.FEMALE)
                .fiscalCode("RSSLNE99A66F205S")
                .build();

        PatientService.addPatient(patient0);
        PatientService.addPatient(patient1);

        Sync.writePatientServiceToJson();
    }

    @Test
    void initPatientServiceSuccess() throws JsonProcessingException {
        Sync.initPatientServiceFromJson();

        System.out.println(PatientService.getPatients());
        assertFalse(PatientService.getPatients().isEmpty());
    }

    @Test
    void initVaccineSiteServiceSuccess() throws JsonProcessingException {
        Sync.initVaccineSiteServiceFromJson();

        System.out.println(VaccineSiteService.getSites());
        assertFalse(VaccineSiteService.isEmpty());
    }

    @Test
    void writeBookingServiceToJsonSuccess() throws IOException {
        var p1 = Patient.builder()
                .firstName("Mario")
                .lastName("Rossi")
                .birthDay(LocalDate.of(2000, 1, 1))
                .fiscalCode("RSSMRA00A01F205F")
                .sex(Sex.MALE)
                .birthPlace("Milano")
                .healthCareWorker(true)
                .caretaker(false)
                .lawEnforcementWorker(false)
                .cohabiting(false)
                .schoolWorker(false)
                .build();

        var c1 = new VaccineCampaign("Campagna esempio", Vaccine.COVID, 20, LocalDate.of(2021, 1, 1),
                LocalDate.of(2021, 2, 2), LocalTime.of(9, 0), LocalTime.of(20, 0));

        var b1 = Booking.builder()
                .patient(p1)
                .vaccineCampaignUUID(c1.getUuid())
                .date(LocalDate.of(2021, 9, 1))
                .location(new VaccineSite("Ospedale Civile Brescia"))
                .build();

        BookingService.addBooking(b1);

        Sync.writeBookingServiceToJson();
    }

    @Test
    void initBookingServiceFromJsonSuccess() throws JsonProcessingException {
        Sync.initBookingServiceFromJson();

        System.out.println(BookingService.getBookings());
        assertFalse(BookingService.getBookings().isEmpty());
    }

    @Test
    void vaccineCampaignHasBookingsSuccess() throws IOException {
        initBookingServiceFromJsonSuccess();

        var p1 = Patient.builder()
                .firstName("Mario")
                .lastName("Rossi")
                .birthDay(LocalDate.of(2000, 1, 1))
                .fiscalCode("RSSMRA00A01F205F")
                .sex(Sex.MALE)
                .birthPlace("Milano")
                .healthCareWorker(true)
                .caretaker(false)
                .lawEnforcementWorker(false)
                .cohabiting(false)
                .schoolWorker(false)
                .build();

        var sites = new HashSet<VaccineSite>();
        sites.add(new VaccineSite("Fiera"));

        var categories = new HashSet<PatientCategories>();
        categories.add(PatientCategories.EVERYONE);

        var b1 = Booking.builder()
                .patient(p1)
                .date(LocalDate.of(2021, 9, 1))
                .time(LocalTime.of(11, 30))
                .build();

        var bookings = new LinkedList<Booking>();
        bookings.add(b1);

        var c1 = new VaccineCampaign("Campagna esempio", Vaccine.COVID, 20, LocalDate.of(2021, 1, 1),
                LocalDate.of(2021, 2, 2), LocalTime.of(9, 0), LocalTime.of(20, 0));

//        b1.setVaccineCampaignUUID(c1.getUuid());
//        BookingService.addBooking(b1);
//        c1.setListOfBookings(c1.returnBookings());

        c1.addBooking(b1);
        System.out.println(c1.debugPrint());
        VaccineCampaignService.addCampaign(c1);
        Sync.writeVaccineCampaignServiceToJson();
    }

    @Test
    void addBookingToCampaignAndSyncSuccess() throws IOException {
        initVaccineCampaignServiceSuccess();

        var p1 = Patient.builder()
                .firstName("Mario")
                .lastName("Rossi")
                .birthDay(LocalDate.of(2000, 1, 1))
                .fiscalCode("RSSMRA00A01F205F")
                .sex(Sex.MALE)
                .birthPlace("Milano")
                .healthCareWorker(true)
                .caretaker(false)
                .lawEnforcementWorker(false)
                .cohabiting(false)
                .schoolWorker(false)
                .build();

        var b1 = Booking.builder()
                .patient(p1)
                .date(LocalDate.of(2021, 9, 1))
                .time(LocalTime.of(11, 30))
                .build();

        for (VaccineCampaign c: VaccineCampaignService.getCampaigns()) {
            if (c.getName().equals("Cosa")) {
                var booking = Booking.builder()
                        .patient(p1)
                        .date(LocalDate.now())
                        .vaccineCampaignUUID(c.getUuid())
                        .build();
                BookingService.addBooking(booking);
            }
        }

        Sync.writeVaccineCampaignServiceToJson();
    }

    @Test
    void simplerBookingImplementation() throws IOException {
        var p1 = Patient.builder()
                .firstName("Mario")
                .lastName("Rossi")
                .birthDay(LocalDate.of(2000, 1, 1))
                .fiscalCode("RSSMRA00A01F205F")
                .sex(Sex.MALE)
                .birthPlace("Milano")
                .healthCareWorker(true)
                .caretaker(false)
                .lawEnforcementWorker(false)
                .cohabiting(false)
                .schoolWorker(false)
                .build();

        var b1 = Booking.builder()
                .patient(p1)
                .date(LocalDate.of(2021, 9, 1))
                .build();

        var sites = new HashSet<VaccineSite>();
        sites.add(new VaccineSite("Fiera"));

        var categories = new HashSet<PatientCategories>();
        categories.add(PatientCategories.EVERYONE);

        var c1 = new VaccineCampaign("Campagna esempio", Vaccine.COVID, 20, LocalDate.of(2021, 1, 1),
                LocalDate.of(2021, 2, 2), LocalTime.of(9, 0), LocalTime.of(20, 0));


        VaccineCampaignService.addCampaign(c1);
        Sync.writeVaccineCampaignServiceToJson();

        c1.addBooking(b1);
        Sync.writeVaccineCampaignServiceToJson();

    }

    @Test
    void initCitizenServiceFromJsonCorrect() throws JsonProcessingException {
        Sync.initCitizenServiceFromJson();

        System.out.println(CitizenService.getCitizens());

        assertFalse(CitizenService.getCitizens().isEmpty());
    }

    @Test
    void serializeMaps() throws IOException {
        var startDate = LocalDate.of(2021, 9, 1);
        var endDate = LocalDate.of(2021, 9, 10);
        var dailyStartTime = LocalTime.of(10, 0);
        var dailyEndTime = LocalTime.of(17, 0);

        Set<VaccineSite> sites = new HashSet<>();
        var site = new VaccineSite("Sito esempio");
        sites.add(site);

        var campaign = new VaccineCampaign("Test", Vaccine.FLU, 10, startDate, endDate,
                dailyStartTime, dailyEndTime, sites, new HashSet<>(), new LinkedList<>());

        campaign.bookTimeSlot(LocalDate.of(2021, 9, 4), LocalTime.of(10, 0),  site);

        System.out.println(campaign.getSlotsPerSite());
        System.out.println("BOOKED SLOTS: \n" + campaign.getBookedSlots());

        VaccineCampaignService.addCampaign(campaign);
        Sync.writeVaccineCampaignServiceToJson();
    }

    @Test
    void deserializeMaps() throws JsonProcessingException {
        Sync.initVaccineCampaignServiceFromJson();
        for (VaccineCampaign c: VaccineCampaignService.getCampaigns()) {
            System.out.println(c.getBookedSlots());
        }
    }

}
