package it.vaxplan.backend.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.vaxplan.backend.*;
import it.vaxplan.backend.service.BookingService;
import it.vaxplan.backend.service.PatientService;
import it.vaxplan.backend.service.VaccineCampaignService;
import it.vaxplan.backend.service.VaccineSiteService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertFalse;

class SyncTest {

    @Test
    void vaccineCampaignServiceToFileSuccess() throws IOException {
        var newCampaign = VaccineCampaign.builder()
                .name("Example")
                .availableDoses(100)
                .dailyStartTime(LocalTime.of(8, 30))
                .dailyEndTime(LocalTime.of(20, 30))
                .startDate(LocalDate.of(2021, 8, 1))
                .endDate(LocalDate.of(2022, 1, 1))
                .vaccine(Vaccine.COVID)
                .build();

        var newCampaign2 = VaccineCampaign.builder()
                .name("COVID")
                .availableDoses(111)
                .dailyStartTime(LocalTime.of(8, 30))
                .dailyEndTime(LocalTime.of(20, 30))
                .startDate(LocalDate.of(2021, 8, 1))
                .endDate(LocalDate.of(2022, 1, 1))
                .vaccine(Vaccine.COVID)
                .build();

        VaccineCampaignService.addCampaign(newCampaign);
        VaccineCampaignService.addCampaign(newCampaign2);

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
                .build();

        var c1 = VaccineCampaign.builder()
                .name("Campagna esempio")
                .startDate(LocalDate.of(2021, 1, 1))
                .endDate(LocalDate.of(2021, 2, 2))
                .dailyStartTime(LocalTime.of(9, 0))
                .dailyEndTime(LocalTime.of(20, 0))
                .availableSites(VaccineSiteService.getSites())
                .vaccine(Vaccine.COVID)
                .build();

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

}
