package ru.mikhailov.otus.task7.domain.dto;


import ru.mikhailov.otus.task7.domain.model.Author;
import ru.mikhailov.otus.task7.domain.model.Book;
import ru.mikhailov.otus.task7.domain.model.Genre;

public record BookDto(
        Long id,
        String name,
        Author author,
        Genre genre
) {

    public BookDto(Book book) {
        this(book.getId(), book.getName(), book.getAuthor(), book.getGenre());
    }

}
