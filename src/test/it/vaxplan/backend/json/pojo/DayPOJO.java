package it.vaxplan.backend.json.pojo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class DayPOJO {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private LocalDate date;

}
