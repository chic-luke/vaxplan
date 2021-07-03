package it.vaxplan.backend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
abstract class Vaccine {
    /**
     * Book a reservation for a vaccine.
     * @return success status
     */
    public abstract boolean book(String site, String date);

    /**
     * Determines if a patient is eligible for a given vaccine campaign.
     * @return Truth value for whether a patient is eligible or not
     */
    public abstract boolean isEligible();
}
