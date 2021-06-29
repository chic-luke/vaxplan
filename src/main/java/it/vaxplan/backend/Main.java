package it.vaxplan.backend;

public class Main {

    public static void main(String[] args) {
	    User p1 = new User("NOME", "COGNOME", "CGNNMO99A01F205A", "205A", "CITTÀ", "01/01/1999", "F", false);

	    User u1 = User.builder()
				.firstName("Mario")
				.lastName("Rossi")
				.fiscalCode("CGNNMO99A01F205A")
				.birthPlace("Milano")
				.code("205A")
				.birthDay("01/01/1999")
				.sex("M")
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
