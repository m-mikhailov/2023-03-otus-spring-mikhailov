package ru.mikhailov.otus.task11.migration;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import ru.mikhailov.otus.task11.domain.model.Author;
import ru.mikhailov.otus.task11.domain.model.Book;
import ru.mikhailov.otus.task11.domain.model.Genre;
import ru.mikhailov.otus.task11.repository.AuthorRepository;
import ru.mikhailov.otus.task11.repository.BookRepository;
import ru.mikhailov.otus.task11.repository.GenreRepository;

@ChangeUnit(id = "books-init", order = "1", author = "mmikhailov")
public class BooksMigrationChangeUnit {

    @Execution
    public void execution(
            AuthorRepository authorRepository,
            GenreRepository genreRepository,
            BookRepository bookRepository) {
        var author = authorRepository.save(new Author("Пушкин"))
                .block();
        var genre = genreRepository.save(new Genre("Роман"))
                .block();
        bookRepository.save(new Book("Капитанская дочка", author, genre))
                .block();
    }

    @RollbackExecution
    public void rollback() {

    }

}
