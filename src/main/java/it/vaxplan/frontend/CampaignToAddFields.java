package it.vaxplan.frontend;

import it.vaxplan.backend.Booking;
import it.vaxplan.backend.PatientCategories;
import it.vaxplan.backend.Vaccine;
import it.vaxplan.backend.VaccineSite;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Accessors(chain = true)
@Builder
public class CampaignToAddFields {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Vaccine vaccine;

    @Getter
    @Setter
    private Integer availableDoses;

    @Getter
    @Setter
    private LocalDate startDate;

    @Getter
    @Setter
    private LocalDate endDate;

    @Getter
    @Setter
    private LocalTime dailyStartTime;

    @Getter
    @Setter
    private LocalTime dailyEndTime;

    @Getter
    @Setter
    public Set<VaccineSite> availableSites = new HashSet<>();

    @Getter
    @Setter
    private Set<PatientCategories> patientCategories = new HashSet<>();

    @Getter
    @Setter
    private List<Booking> listOfBookings = new LinkedList<>();

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Vaccine: " + vaccine + "\n" +
                "Available doses: " + availableDoses + "\n" +
                "Start date: " + startDate + "\n" +
                "End date: " + endDate + "\n" +
                "Daily start time: " + dailyStartTime + "\n" +
                "Daily end time: " + dailyEndTime + "\n" +
                "Available sites: " + availableSites + "\n";
    }
}
