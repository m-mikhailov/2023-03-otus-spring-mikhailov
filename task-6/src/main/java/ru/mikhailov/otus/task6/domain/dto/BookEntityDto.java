package ru.mikhailov.otus.task6.domain.dto;

import ru.mikhailov.otus.task6.domain.model.Author;
import ru.mikhailov.otus.task6.domain.model.Book;
import ru.mikhailov.otus.task6.domain.model.Genre;

public record BookEntityDto(
        Long id,
        String name,
        Author author,
        Genre genre
) {

    public BookEntityDto(Book book) {
        this(book.getId(), book.getName(), book.getAuthor(), book.getGenre());
    }

}
