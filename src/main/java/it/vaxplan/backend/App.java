package it.vaxplan.backend;

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
                .birthDay(DateHandler.parse("05/04/1986", null))
                .sex(Sex.MALE)
                .fiscalCode("RSSMRA86D05F205W")
                .birthPlace("Milano")
                .healthCareWorker(false)
                .build();

        Booking b1 = Booking.builder()
                .patient(p1)
                .vaccine(Vaccine.COVID)
                .date(DateHandler.parse("01/09/2021", null))
                .location("Milan")
                .build();

        System.out.println(b1.getDate());
    }
}
