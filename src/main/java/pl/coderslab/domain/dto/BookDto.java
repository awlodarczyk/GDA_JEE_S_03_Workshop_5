package pl.coderslab.domain.dto;

import lombok.Data;

@Data
public class BookDto {

    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private String type;
}
