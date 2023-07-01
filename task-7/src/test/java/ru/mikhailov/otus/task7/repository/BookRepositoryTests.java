package ru.mikhailov.otus.task7.repository;

import jakarta.persistence.Query;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.mikhailov.otus.task7.domain.model.Author;
import ru.mikhailov.otus.task7.domain.model.Book;
import ru.mikhailov.otus.task7.domain.model.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Book Repository Tests")
@DataJpaTest
public class BookRepositoryTests {

    private static final int EXPECTED_BOOKS_COUNT = 1;

    public static final Long EXISTING_BOOK_ID = 1L;
    public static final String EXISTING_BOOK_NAME = "Капитанская дочка";

    @Autowired
    private BookRepository repository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("Should return expected books count")
    @Test
    public void shouldReturnExpectedBooksCount() {
        var books = repository.findAll();

        assertThat(books).hasSize(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("Should return expected book by id")
    @Test
    public void shouldReturnExpectedBookById() {
        var actualBook = repository.findById(EXISTING_BOOK_ID);

        var expectedBook = new Book(
                EXISTING_BOOK_ID,
                EXISTING_BOOK_NAME,
                new Author(1L, "Пушкин"),
                new Genre(1L, "Роман")
        );

        assertThat(actualBook)
                .isPresent()
                .contains(expectedBook);
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

        var savedBook = repository.save(newBook);

        em.clear();

        var actualBook = em.find(Book.class, savedBook.getId());

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

        repository.save(updatedBook);

        var actualBook = em.find(Book.class, EXISTING_BOOK_ID);

        assertThat(actualBook)
                .isNotNull()
                .isEqualTo(updatedBook);
    }

    @DisplayName("Should delete existing book")
    @Test
    public void shouldDeleteExistingBook() {
        Query query = em.getEntityManager().createQuery("select count(b) from Book b");

        var booksBefore = (Long) query.getSingleResult();

        repository.deleteById(1L);

        var booksAfter = (Long) query.getSingleResult();

        assertThat(booksBefore)
                .isEqualTo(1L);

        assertThat(booksAfter)
                .isEqualTo(0L);
    }
}
