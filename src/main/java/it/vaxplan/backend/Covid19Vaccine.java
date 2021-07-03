package it.vaxplan.backend;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.LinkedList;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Accessors(chain = true)
@AllArgsConstructor
@Builder
public class Covid19Vaccine extends Vaccine {
    private User user;
    private int availableDoses;
    private int availableSlots;
    private LinkedList<String> vaccinationSites = new LinkedList<>();
    private LinkedList<Date> vaccinationSlots = new LinkedList<>();

    /**
     * Create a new booking for a COVID-19 vaccine shot.
     * @param site Selected vaccine site
     * @param date Selected vaccine date
     * @return Success status
     */
    @Override
    public boolean book(String site, String date) {
        if (isEligible()) {
            if (availableSlots > 0) {
                var b1 = Booking.builder()
                        .site(site)
                        .date(date)
                        .currentBooking("COVID-19")
                        .user(user)
                        .build();
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
