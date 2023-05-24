package ru.mikhailov.otus.task5.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.mikhailov.otus.task5.domain.model.Author;
import ru.mikhailov.otus.task5.domain.model.Book;
import ru.mikhailov.otus.task5.domain.model.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Book DAO Tests")
@JdbcTest
@Import(BookDaoJdbc.class)
public class BookDaoJdbcTests {

    private static final int EXPECTED_BOOKS_COUNT = 1;

    public static final Long EXISTING_BOOK_ID = 1L;
    public static final String EXISTING_BOOK_NAME = "Капитанская дочка";

    @Autowired
    private BookDao bookDao;

    @DisplayName("Should return expected books count")
    @Test
    public void shouldReturnExpectedBooksCount() {
        var authors = bookDao.findAll();

        assertThat(authors).hasSize(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("Should return expected book by id")
    @Test
    public void shouldReturnExpectedBookById() {
        var actualBook = bookDao.findById(EXISTING_BOOK_ID);

        var expectedBook = new Book(
                EXISTING_BOOK_ID,
                EXISTING_BOOK_NAME,
                new Author(1L, "Пушкин"),
                new Genre(1L, "Роман")
        );

        assertThat(actualBook)
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("Should save new book")
    @Test
    public void shouldSaveNewBook() {
        var newBook = new Book(
                null,
                "Дубровский",
                new Author(1L, "Пушкин"),
                new Genre(1L, "Роман")
        );

        var savedBook = bookDao.save(newBook);

        var actualBook = bookDao.findById(savedBook.getId());

        assertThat(actualBook)
                .isNotNull()
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(newBook);
    }

    @DisplayName("Should update book name")
    @Test
    public void shouldUpdateBookName() {
        var updatedBook = new Book(
                EXISTING_BOOK_ID,
                "Дубровский",
                new Author(1L, "Пушкин"),
                new Genre(1L, "Роман")
        );

        bookDao.update(updatedBook);

        var actualBook = bookDao.findById(EXISTING_BOOK_ID);

        assertThat(actualBook)
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(updatedBook);
    }

    @DisplayName("Should delete existing book")
    @Test
    public void shouldDeleteExistingBook() {
        var booksBefore = bookDao.findAll();

        bookDao.deleteById(1L);

        var booksAfter = bookDao.findAll();

        assertThat(booksBefore)
                .hasSize(EXPECTED_BOOKS_COUNT);

        assertThat(booksAfter)
                .isEmpty();
    }
}
