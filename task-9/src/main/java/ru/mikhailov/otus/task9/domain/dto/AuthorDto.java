package ru.mikhailov.otus.task9.domain.dto;

import ru.mikhailov.otus.task9.domain.model.Author;

public record AuthorDto(
        Long id,
        String name
) {

    public AuthorDto(Author author) {
        this(author.getId(), author.getName());
    }

}
