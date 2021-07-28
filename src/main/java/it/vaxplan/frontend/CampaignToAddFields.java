package it.vaxplan.frontend;

import it.vaxplan.backend.Vaccine;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.TreeSet;

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

    public final Set<String> availableSites = new TreeSet<>();

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
