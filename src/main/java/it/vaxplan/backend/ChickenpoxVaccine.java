package it.vaxplan.backend;

import lombok.*;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Accessors(chain = true)
@AllArgsConstructor
public class ChickenpoxVaccine extends Vaccine {

    User user;

    /**
     * Book a chickenpox vaccine injection.
     * @param site Vaccination site
     * @param date Vaccination date
     * @return Success/failure status of booking attempt
     */
    public boolean chickenpoxVaccineBooking(String site, String date) {
        if (isEligible()) {
            if (availableSlots > 0) {
                // var p1 = new Booking(site, date, "Varicella", utente);
                var p1 = Booking.builder()
                        .site(site)
                        .date(date)
                        .currentBooking("Varicella")
                        .user(user)
                        .build();
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
        return user.isValid() && Integer.parseInt(user.getYear()) > 2003 && (user.isHealthCareWorker());
    }
}
