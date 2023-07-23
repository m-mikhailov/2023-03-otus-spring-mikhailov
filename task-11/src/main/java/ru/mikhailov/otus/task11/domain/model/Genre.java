package ru.mikhailov.otus.task11.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@EqualsAndHashCode
@Document("genres")
@AllArgsConstructor
@NoArgsConstructor
public class Genre {

    @Id
    private String id;

    private String name;

    public Genre(String name) {
        this.name = name;
    }
}
