package it.vaxplan.backend.json.pojo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class BookPOJO {

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private boolean inPrint;

    @Getter
    @Setter
    private LocalDate publishDate;

}
