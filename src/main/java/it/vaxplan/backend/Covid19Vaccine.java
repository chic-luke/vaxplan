package it.vaxplan.backend;

public class Covid19Vaccine extends Vaccine {

    User utente;

    public Covid19Vaccine(User utente) {
        this.utente = utente;
    }

    /**
     * Create a new booking for a COVID-19 vaccine shot.
     * @param sede Selected vaccine site
     * @param data Selected vaccine date
     * @return Success status
     */
    public boolean bookCovidVaccine(String sede, String data) {
        if (isEligible()) {
            if (availableSlots > 0) {
                Booking p1 = new Booking(sede, data, "Covid-19", utente);
                availableSlots--;
                p1.addBooking(p1);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean isEligible() {
        return utente.isValid() && Integer.parseInt(utente.getYear()) < 2009;
    }
}
