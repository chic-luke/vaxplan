package it.vaxplan.backend;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.Objects;
import java.util.function.Function;

@Data
@RequiredArgsConstructor
@Accessors(chain = true)
@AllArgsConstructor
@Builder
public class VaccineCampaign {

    @NotNull
    private Vaccine associatedVaccine;

    @NotNull
    private final ZonedDateTime startDate;

    @NotNull
    private final ZonedDateTime endDate;

    @NotNull
    private final LocalTime dailyStartTime;

    @NotNull
    private final LocalTime dailyEndTime;

    @NotEmpty
    private final LinkedList<VaccineSite> availableSites = new LinkedList<>();

    private final Function<Patient, Boolean> patientElegibilityChecker;


    /**
     * Check if patient is eligible for the vaccine campaign.
     * @param patient patient to check for eligibility
     * @return truth value
     */
    public boolean isPatientElegible(Patient patient) {
        return Objects.isNull(patientElegibilityChecker) || patientElegibilityChecker.apply(patient);
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
        availableSites.stream().filter(b -> b.equals(site)).findFirst().ifPresent(availableSites::remove);
    }

}
