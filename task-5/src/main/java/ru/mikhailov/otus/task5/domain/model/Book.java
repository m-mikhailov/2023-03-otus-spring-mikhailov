package ru.mikhailov.otus.task5.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

    private Long id;

    private String name;

    private Author author;

    private Genre genre;

}
