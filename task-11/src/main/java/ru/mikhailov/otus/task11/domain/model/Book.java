package ru.mikhailov.otus.task11.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"author", "genre"})
@Document("books")
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    private String id;

    private String name;

    private Author author;

    private Genre genre;

    public Book(String name, Author author, Genre genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public Book(String id) {
        this.id = id;
    }
}
