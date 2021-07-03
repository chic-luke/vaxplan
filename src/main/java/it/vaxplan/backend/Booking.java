package it.vaxplan.backend;

import java.util.LinkedList;

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
    private LinkedList<Booking> bookings = new LinkedList<>();

    public void addBooking(Booking booking)
    {
        bookings.add(booking);
    }
}
