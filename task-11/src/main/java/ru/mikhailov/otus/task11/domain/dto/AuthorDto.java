package ru.mikhailov.otus.task11.domain.dto;

import ru.mikhailov.otus.task11.domain.model.Author;

public record AuthorDto(
        String id,
        String name
) {

    public AuthorDto(Author author) {
        this(author.getId(), author.getName());
    }

}
