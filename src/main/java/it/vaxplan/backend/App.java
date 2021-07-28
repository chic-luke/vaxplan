package it.vaxplan.backend;

import it.vaxplan.backend.service.BookingService;
import it.vaxplan.backend.utils.DateHandler;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );


        Patient p1 = Patient.builder()
                .firstName("Mario")
                .lastName("Rossi")
                .birthDay(DateHandler.parseDate("05/04/1986", null))
                .sex(Sex.MALE)
                .fiscalCode("RSSMRA86D05F205W")
                .birthPlace("Milano")
                .healthCareWorker(false)
                .build();

        Booking b1 = Booking.builder()
                .patient(p1)
                .vaccine(Vaccine.COVID)
                .date(DateHandler.parseDate("01/09/2021", null))
                .location("Fiera")
                .build();

        System.out.println(b1.getDate());

        var service = new BookingService();
        service.addBooking(b1);

        var bks = service.getBookings();
        System.out.println(bks);
        service.removeBooking(b1.getUuid());
        System.out.println(bks);

        VaccineCampaign c1 = VaccineCampaign.builder()
                .name("Campagna vaccinale COVID")
                .vaccine(Vaccine.COVID)
                .availableDoses(100)
                // .startDate(DateHandler.parseDate("15/06/2021", null))
                .startDate(LocalDate.of(2021, 5, 6))
                // .endDate(DateHandler.parseDate("01/01/2022", null))
                .endDate(LocalDate.of(2022, 1, 1))
                .dailyStartTime(LocalTime.of(9, 0))
                .dailyEndTime(LocalTime.of(20, 0))
                .build();

        c1.bookings = new BookingService();
        c1.bookings.addBooking(b1);

//        c1.addVaccinationSite(VaccineSite.builder().name("Ospedale Civile").city("Brescia").build());
//        c1.addVaccinationSite(VaccineSite.builder().name("Niguarda").city("Milano").build());
//        c1.addVaccinationSite(VaccineSite.builder().name("Ospedale Luigi Secco").city("Milano").build());
//        c1.addVaccinationSite(VaccineSite.builder().name("Pronto Soccorso").city("Brescia").build());

        System.out.println(c1.getAvailableSites());

        var mySites = new LinkedList<VaccineSite>();

//        for (VaccineSite site: c1.getAvailableSites())
//            if (site.getCity().equals("Brescia"))
//                mySites.add(site);

        System.out.println("Available sites for Berscia: " + mySites);

        System.out.println();
        System.out.println(c1);
    }
}
