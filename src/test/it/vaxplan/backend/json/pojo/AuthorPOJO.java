package it.vaxplan.backend.json.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class AuthorPOJO {

    @Getter
    @Setter
    private String authorName;

    @Getter
    @Setter
    private List<BookPOJO> books;

}
