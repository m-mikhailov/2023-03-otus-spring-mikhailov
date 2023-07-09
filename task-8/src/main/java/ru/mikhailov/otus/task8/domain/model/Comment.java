package ru.mikhailov.otus.task8.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"book"})
@Document("comments")
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    private String id;

    private String text;

    @DBRef
    private Book book;

    public Comment(String text, Book book) {
        this.text = text;
        this.book = book;
    }
}
