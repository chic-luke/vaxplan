package it.vaxplan.backend;

import it.vaxplan.backend.service.BookingService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class VaccineCampaign {

    private final UUID uuid = UUID.randomUUID();
    private String name;
    private Vaccine vaccine;
    private int availableDoses;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime dailyStartTime;
    private LocalTime dailyEndTime;
    private final Set<String> availableSites;
    private BookingService bookings;
    private Set<PatientCategories> patientCategories;

    
    /**
     * Check if patient is eligible for the vaccine campaign.
     * @param patient patient to check for eligibility
     * @return truth value
     */
    public boolean isPatientElegible(Patient patient) {
        return vaccine.isPatientElegible(patient);
    }

    /**
     * Add an arbitrary number of doses to this campaign.
     * Useful to log new doses that are bought by the healthcare system.
     * @param doses doses to add
     */
    public void addDoses(int doses) {
        availableDoses += doses;
    }

    /**
     * Add a site to the list of vaccination sites where this vaccine campaign is available.
     * @param site vaccination site to add to this campaign
     */
    public void addVaccinationSite(String site) {
        availableSites.add(site);
    }

    /**
     * Remove a site to the list of vaccination sites where this vaccine campaign is available.
     * @param site vaccination site to remove from this campaign
     */
    public void removeVaccinationSite(String site) {
        availableSites.remove(site);
    }

    /**
     * Return a human-readable summary of the booking to stdout.
     * @return Summary of booking
     */
    public String toString() {
        return "Riassunto campagna vaccinale: \n" +
                "UUID" + uuid + "\n" +
                "Nome: " + name + "\n" +
                "Vaccino: " + vaccine + "\n" +
                "Dosi disponibili: " + availableDoses + "\n" +
                "Data d'inizio: " + startDate + "\n" +
                "Data di fine: " + endDate + "\n" +
                "Dalle ore: " + dailyStartTime + "\n" +
                "Alle ore: " + dailyEndTime + "\n" +
                "Per le seguenti categorie di pazienti: " + patientCategories + "\n" +
                "Nei seguenti ambulatori: " + availableSites + "\n" +
                "Prenotazioni effettuate: " + bookings;
    }

}
