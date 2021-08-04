package it.vaxplan.backend;

import it.vaxplan.backend.service.BookingService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

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
    private final Set<VaccineSite> availableSites;
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
    public void addVaccinationSite(VaccineSite site) {
        availableSites.add(site);
    }

    /**
     * Remove a site to the list of vaccination sites where this vaccine campaign is available.
     * @param site vaccination site to remove from this campaign
     */
    public void removeVaccinationSite(VaccineSite site) {
        availableSites.remove(site);
    }

    /**
     * Add a Collection of sites to the list of vaccination sites
     * @param sites Sites to add
     */
    public void addAllVaccinationSites(Collection<VaccineSite> sites) {
        availableSites.addAll(sites);
    }

    /**
     * Get a list of Bookings for this VaccineCampaign from the BookingService and return it
     * @return List of bookings associated to this vaccine campaign
     */
    public List<Booking> returnBookings() {
        var tmpList = new LinkedList<Booking>();
        for (Booking b: BookingService.getBookings()) {
            if (b.getVaccineCampaignUUID().equals(this.uuid))
                tmpList.add(b);
        }

        return tmpList;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Return a human-readable summary of the booking to stdout.
     * @return Summary of booking
     */
    public String debugPrint() {
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
                "Prenotazioni effettuate: " + "WIP";
    }

}
