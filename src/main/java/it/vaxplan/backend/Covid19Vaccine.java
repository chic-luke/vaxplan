package it.vaxplan.backend;

import lombok.*;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Accessors(chain = true)
@AllArgsConstructor
@Builder
public class Covid19Vaccine extends Vaccine {

    User user;

    /**
     * Create a new booking for a COVID-19 vaccine shot.
     * @param site Selected vaccine site
     * @param date Selected vaccine date
     * @return Success status
     */
    public boolean bookCovidVaccine(String site, String date) {
        if (isEligible()) {
            if (availableSlots > 0) {
                var b1 = new Booking(site, date, "Covid-19", user);
                availableSlots--;
                b1.addBooking(b1);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean isEligible() {
        return user.isValid() && Integer.parseInt(user.getYear()) < 2009;
    }
}
