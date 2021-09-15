package it.vaxplan.backend;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Data
@Accessors(chain = true)
public class VaccineCampaign {

    private final UUID uuid = UUID.randomUUID();
    private String name;
    private Vaccine vaccine;
    private Integer availableDoses;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime dailyStartTime;
    private LocalTime dailyEndTime;
    private final Set<VaccineSite> availableSites;
    private Set<PatientCategories> patientCategories;
    private List<Booking> listOfBookings;
    private HashMap<VaccineSite, HashMap<LocalDateTime, Boolean>> slotsPerSite;
    @JsonSerialize(keyUsing = MapSerializer.class)
    private HashMap<VaccineSite, LocalDateTime> bookedSlots;

    /**
     * Short constructor, initializes sets
     */
    public VaccineCampaign(String name, Vaccine vaccine, Integer availableDoses, LocalDate startDate, LocalDate endDate,
                           LocalTime dailyStartTime, LocalTime dailyEndTime) {
        this.name = name;
        this.vaccine = vaccine;
        this.availableDoses = availableDoses;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyStartTime = dailyStartTime;
        this.dailyEndTime = dailyEndTime;

        this.availableSites = new HashSet<>();
        this.patientCategories = new HashSet<>();
        this.listOfBookings = new LinkedList<>();
        this.slotsPerSite = new HashMap<>();
        this.bookedSlots = new HashMap<>();

        initializeTimeSlices();
        initBookedSlots();
    }

    /**
     * Full overloaded constructor, without bookedSlots
     */
    public VaccineCampaign(String name, Vaccine vaccine, int availableDoses, LocalDate startDate, LocalDate endDate,
                           LocalTime dailyStartTime, LocalTime dailyEndTime, Set<VaccineSite> availableSites,
                           Set<PatientCategories> patientCategories, List<Booking> listOfBookings) {
        this.name = name;
        this.vaccine = vaccine;
        this.availableDoses = availableDoses;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyStartTime = dailyStartTime;
        this.dailyEndTime = dailyEndTime;
        this.availableSites = availableSites;
        this.patientCategories = patientCategories;
        this.listOfBookings = listOfBookings;
        this.slotsPerSite = new HashMap<>();
        this.bookedSlots = new HashMap<>();

        initializeTimeSlices();
        initBookedSlots();
    }

    /**
     * Full constructor, also contains bookedSlots
     */
    public VaccineCampaign(String name, Vaccine vaccine, int availableDoses, LocalDate startDate, LocalDate endDate,
                           LocalTime dailyStartTime, LocalTime dailyEndTime, Set<VaccineSite> availableSites,
                           Set<PatientCategories> patientCategories, List<Booking> listOfBookings,
                           HashMap<VaccineSite, LocalDateTime> bookedSlots) {
        this.name = name;
        this.vaccine = vaccine;
        this.availableDoses = availableDoses;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyStartTime = dailyStartTime;
        this.dailyEndTime = dailyEndTime;
        this.availableSites = availableSites;
        this.patientCategories = patientCategories;
        this.listOfBookings = listOfBookings;
        this.slotsPerSite = new HashMap<>();

        this.bookedSlots = bookedSlots;

        initializeTimeSlices();
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
//    public void addVaccinationSite(VaccineSite site) {
//        availableSites.add(site);
//        var map = new HashMap<LocalDateTime, Boolean>();
//        slotsPerSite.put(site, map);
//
//        // Add a time slice every 10 minutes for the whole amount of hours where the vaccination is possible
//        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
//            for (LocalTime time = dailyStartTime; time.isAfter(dailyEndTime); time = time.plusMinutes(10)) {
//                slotsPerSite.get(site).put(LocalDateTime.of(date, time), true);
//            }
//        }
//    }

    /**
     * Remove a site to the list of vaccination sites where this vaccine campaign is available.
     * @param site vaccination site to remove from this campaign
     */
//    public void removeVaccinationSite(VaccineSite site) {
//        availableSites.remove(site);
//        slotsPerSite.remove(site);
//    }

    /**
     * Add a Collection of sites to the list of vaccination sites
     * @param sites Sites to add
     */
    public void addAllVaccinationSites(Collection<VaccineSite> sites) {
        availableSites.addAll(sites);

        for (VaccineSite site: sites) {
            var map = new HashMap<LocalDateTime, Boolean>();
            slotsPerSite.put(site, map);

            // Add a time slice every 10 minutes for the whole amount of hours where the vaccination is possible
            for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
                for (LocalTime time = dailyStartTime; time.isBefore(dailyEndTime); time = time.plusMinutes(10)) {
                    slotsPerSite.get(site).put(LocalDateTime.of(date, time), true);
                }
            }

        }

    }

    /**
     * Add a Booking to the list of associated bookings.
     * @param booking Booking to add
     */
    public void addBooking(Booking booking) {

        if (listOfBookings == null) {
            this.listOfBookings = new ArrayList<>();
        }

        listOfBookings.add(booking);
        bookTimeSlot(booking.getDate(), booking.getTime(), booking.getLocation());
    }


    /**
     * Initialize the HashMap which maps every VaccineCampaign to a map of available time slices
     */
    private void initializeTimeSlices() {
        for (VaccineSite site: this.getAvailableSites()) {
            var map = new HashMap<LocalDateTime, Boolean>();
            slotsPerSite.put(site, map);

            // Add a time slice every 10 minutes for the whole amount of hours where the vaccination is possible
            for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
                for (LocalTime time = dailyStartTime; time.isBefore(dailyEndTime); time = time.plusMinutes(10)) {
                    slotsPerSite.get(site).put(LocalDateTime.of(date, time), true);
                }
            }

        }
    }

    /**
     * Book a vaccination time slot
     * @param date Date of vaccination
     * @param time Time slot of vaccination
     * @param site Physical vaccination site
     */
    public void bookTimeSlot(LocalDate date, LocalTime time, VaccineSite site) {
        var dateTime = LocalDateTime.of(date, time);
        slotsPerSite.get(site).replace(dateTime, false);

        if (bookedSlots == null) {
            this.bookedSlots = new HashMap<>();
        }

        bookedSlots.put(site, dateTime);
    }

    /**
     * Book a vaccination time slot.
     * @param dateTime DateTime of vaccination
     * @param site Physical vaccination site
     */
    public void bookTimeSlot(LocalDateTime dateTime, VaccineSite site) {
        slotsPerSite.get(site).replace(dateTime, false);

        if (bookedSlots == null) {
            this.bookedSlots = new HashMap<>();
        }

        bookedSlots.put(site, dateTime);
    }

    public void bookTimeSlot(LocalDate date, LocalDateTime dateTime, VaccineSite site) {
        slotsPerSite.get(site).replace(dateTime, false);

        if (bookedSlots == null) {
            this.bookedSlots = new HashMap<>();
        }

        bookedSlots.put(site, dateTime);
    }

    /**
     * Fill the bookedSlots map with the slots that are booked
     */
    public void initBookedSlots() {
        // Iterate through outer HashMap
        for (Map.Entry<VaccineSite, HashMap<LocalDateTime, Boolean>> set :
        slotsPerSite.entrySet()) {
            var site = set.getKey();
            var tmpMap = set.getValue();
            LocalDateTime tmpTime;
            Boolean tmpBool;

            // Iterate through inner, nested HashMap
            for (Map.Entry<LocalDateTime, Boolean> innerSet :
            tmpMap.entrySet()) {
                tmpTime = innerSet.getKey();
                tmpBool = innerSet.getValue();

                if (!tmpBool) {
                    bookedSlots.put(site, tmpTime);
                }
            }
        }
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
                "Prenotazioni effettuate: " + listOfBookings + "\n";
    }

}
