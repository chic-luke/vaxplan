package it.vaxplan.backend.service;

import java.util.*;

import it.vaxplan.backend.Booking;
import lombok.Getter;

public class BookingService {

    /**
     * This is an utility class, it's not meant to be instantiated.
     */
    private BookingService() {
        throw new IllegalArgumentException("Utility class (global service)");
    }

    @Getter
    private static final List<Booking> bookings = new LinkedList<>();

    public static void addBooking(Booking booking) {
        bookings.add(booking);
    }

    /**
     * Remove booking from the list of bookings according to the Booking object
     * @param booking Booking to remove
     */
    public static void removeBooking(Booking booking) {
        bookings.remove(booking);
    }

    /**
     * Remove booking from the list of bookings according to the UUID of the Booking
     * to remove
     * @param uuid UUID of the Booking to remove
     */
    public static void removeBooking(UUID uuid) {
        Objects.requireNonNull(uuid);
        bookings.stream().filter(b -> b.getUuid().equals(uuid)).findFirst().ifPresent(bookings::remove);
    }

    /**
     * Add a collection of Bookings to the list of bookings
     * @param newBookings Collection of Booking
     */
    public static void addBookings(Collection<Booking> newBookings) {
        bookings.addAll(newBookings);
    }

    /**
     * Remove a collection of Bookings to the list of bookings
     * @param bookingsToRemove Collection of Booking
     */
    public static void removeBookings(Collection<Booking> bookingsToRemove) {
        bookings.removeAll(bookingsToRemove);
    }

}
