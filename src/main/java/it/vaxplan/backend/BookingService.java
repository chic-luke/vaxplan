package it.vaxplan.backend;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

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

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
    }

    public void removeBooking(UUID uuid) {
        Objects.requireNonNull(uuid);
        bookings.stream().filter(b -> b.getUuid().equals(uuid)).findFirst().ifPresent(bookings::remove);
    }

}
