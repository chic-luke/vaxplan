package it.vaxplan.backend.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateHandler {

    /**
     * Parses a date string into a ZoneDateTime object
     * @param date String formatted like "dd/mm/yyyy"
     * @return Corresponding ZoneDateTime object
     */
    public static ZonedDateTime parseDate(String date, String time) {

        var ld = LocalDate.parse(
                date,
                DateTimeFormatter.ofPattern("dd/MM/uuuu")
        );

        var lt = LocalTime.of(0, 0);
        if (time != null)
            lt = LocalTime.parse(time);

        return ZonedDateTime.of(ld, lt, ZoneId.of("UTC+2"));
    }

    /**
     * Returns a ZoneDateTime object given a LocalDate and a LocalTime in UTC+2 timezone.
     * @param date LocalDate to use as date
     * @param time LocalTime to use as time
     * @return Resulting ZoneDateTime object
     */
    public static ZonedDateTime joinDateTime(LocalDate date, LocalTime time) {
        return ZonedDateTime.of(date, time, ZoneId.of("UTC+2"));
    }



}
