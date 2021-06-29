package it.vaxplan.backend;

import java.util.ArrayList;

public class Booking {

    String site, date, booking;
    User user;
    ArrayList<Booking> bookings = new ArrayList<>();

    public Booking(String site, String date, String booking, User user)
    {
        this.site = site;
        this.date = date;
        this.booking = booking;
        this.user = user;
    }

    public String getSite()
    {
        return site;
    }

    public String getDate()
    {
        return date;
    }

    public String getBooking()
    {
        return booking;
    }

    public void addBooking(Booking booking)
    {
        bookings.add(booking);
    }
}
