package it.vaxplan.backend;

public class Main {

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
	    Covid19Vaccine p1_covid = new Covid19Vaccine(p1);
		System.out.println("Posti disponibili: "+p1_covid.getAvailableSlots());
	    boolean prenotazione_andata = p1_covid.bookCovidVaccine("Vicenza", "12.6.2021");
		System.out.println("Posti disponibili: "+p1_covid.getAvailableSlots());
	    if(prenotazione_andata) System.out.println("Prenotazione andata a buon fine");
	    else System.out.println("Errore nella prenotazione");
    }
}
