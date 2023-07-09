package ru.mikhailov.otus.task8.domain.dto;


import ru.mikhailov.otus.task8.domain.model.Author;

public record AuthorDto(
        String id,
        String name
) {

    public AuthorDto(Author author) {
        this(author.getId(), author.getName());
    }

}
