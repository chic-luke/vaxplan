package it.vaxplan.backend;

public class Main {
	// TODO: Clean up this mess

    public static void main(String[] args) {
		var p1 = User.builder()
				.firstName("Mario")
				.lastName("Rossi")
				.fiscalCode("RSSMRA86D05F205W")
				.birthPlace("Milano")
				.birthDay("05/04/1986")
				.sex("M")
				.code("205W")
				.healthCareWorker(false)
				.build();

	    p1.isValid();
	    var p1Covid = Covid19Vaccine.builder().user(p1).build();
	    p1Covid.setAvailableSlots(6);
		System.out.println("Posti disponibili: " + p1Covid.getAvailableSlots());
	    boolean bookingStatus = p1Covid.book("Vicenza", "12.6.2021");
		System.out.println("Posti disponibili: " + p1Covid.getAvailableSlots());

	    if (bookingStatus)
	    	System.out.println("Prenotazione andata a buon fine");
	    else
	    	System.out.println("Errore nella prenotazione");
    }
}
