package ru.mikhailov.otus.task11.domain.dto;

import ru.mikhailov.otus.task11.domain.model.Author;
import ru.mikhailov.otus.task11.domain.model.Book;
import ru.mikhailov.otus.task11.domain.model.Genre;

public record BookDto(
        String id,
        String name,
        Author author,
        Genre genre
) {

    public BookDto(Book book) {
        this(book.getId(), book.getName(), book.getAuthor(), book.getGenre());
    }

}
