package it.vaxplan.backend;

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
    public static ZonedDateTime parse(String date, String time) {

        var ld = LocalDate.parse(
                date,
                DateTimeFormatter.ofPattern("dd/MM/uuuu")
        );

        var lt = LocalTime.of(0, 0);
        if (time != null)
            lt = LocalTime.parse(time);

        return ZonedDateTime.of(ld, lt, ZoneId.of("GMT+1"));
    }

}
