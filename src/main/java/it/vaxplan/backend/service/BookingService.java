package it.vaxplan.backend.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import it.vaxplan.backend.Booking;
import lombok.Getter;

public class BookingService {

    @Getter
    private final List<Booking> bookings;

    public BookingService() {
        bookings = new LinkedList<>();
    }

    public void addBooking(Booking booking) {
        if (booking.getVaccine().isPatientElegible(booking.getPatient())) {
            throw new IllegalArgumentException("Non sei idoneo per questo vaccino");
        }

        bookings.add(booking);
    }

    /**
     * Remove booking from the list of bookings according to the Booking object
     * @param booking Booking to remove
     */
    public void removeBooking(Booking booking) {
        bookings.remove(booking);
    }

    /**
     * Remove booking from the list of bookings according to the UUID of the Booking
     * to remove
     * @param uuid UUID of the Booking to remove
     */
    public void removeBooking(UUID uuid) {
        Objects.requireNonNull(uuid);
        bookings.stream().filter(b -> b.getUuid().equals(uuid)).findFirst().ifPresent(bookings::remove);
    }

}
