package it.vaxplan.backend;

public class ChickenpoxVaccine extends Vaccine {

    User utente;

    public ChickenpoxVaccine(User utente) {
        this.utente = utente;
    }

    public boolean chickenpoxVaccineBooking(String sede, String data) {
        if (isEligible()) {
            if (availableSlots > 0) {
                Booking p1 = new Booking(sede, data, "Varicella", utente);
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
        return utente.isValid() && Integer.parseInt(utente.getYear()) > 2003 && (utente.isHealthCareWorker());
    }
}
