package it.vaxplan.frontend;

import it.vaxplan.backend.Patient;
import it.vaxplan.backend.VaccineCampaign;
import lombok.Getter;
import lombok.Setter;

public class CitizenBookingFields {

    private CitizenBookingFields() {
        throw new IllegalArgumentException("Utility class");
    }

    @Getter
    @Setter
    private static Patient currentPatient;

    @Getter
    @Setter
    private static VaccineCampaign selectedCampaign;

}
