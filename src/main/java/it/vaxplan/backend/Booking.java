package it.vaxplan.backend;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@AllArgsConstructor
@Builder
public class Booking {

    private String site;
    private String date;
    private String currentBooking;
    private User user;

    ArrayList<Booking> bookings = new ArrayList<>();

    public void addBooking(Booking booking)
    {
        bookings.add(booking);
    }
}
