package ru.mikhailov.otus.task8.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import ru.mikhailov.otus.task8.domain.model.Author;
import ru.mikhailov.otus.task8.domain.model.Book;
import ru.mikhailov.otus.task8.domain.model.Comment;
import ru.mikhailov.otus.task8.domain.model.Genre;
import ru.mikhailov.otus.task8.mongoevent.MongoBookCascadeDeleteEventListener;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Book Repository Tests")
@Import(MongoBookCascadeDeleteEventListener.class)
public class BookRepositoryTests extends AbstractRepositoryTest {
    public static final String EXISTING_BOOK_NAME = "Капитанская дочка";

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private CommentRepository commentRepository;

    @DisplayName("Should return expected books count")
    @Test
    public void shouldReturnExpectedBooksCount() {
        var books = bookRepository.findAll();

        assertThat(books).hasSizeGreaterThan(1);
    }

    @DisplayName("Should return expected book by id")
    @Test
    public void shouldReturnExpectedBookById() {
        var existingBook = bookRepository.findAll().get(0);

        var actualBook = bookRepository.findById(existingBook.getId());

        var expectedBook = Optional.of(new Book(
                existingBook.getId(),
                EXISTING_BOOK_NAME,
                new Author("Пушкин"),
                new Genre("Роман")
        ));

        assertThat(actualBook)
                .isPresent()
                .usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expectedBook);
    }

    @DisplayName("Should return empty optional")
    @Test
    public void shouldReturnEmptyOptional() {
        var actual = bookRepository.findById("1");

        assertThat(actual)
                .isNotPresent();
    }

    @DisplayName("Should save new book")
    @Test
    public void shouldSaveNewBook() {
        var author = authorRepository.findAll().get(0);
        var genre = genreRepository.findAll().get(0);

        var newBook = new Book(
                "Дубровский",
                author,
                genre
        );

        var savedBook = bookRepository.save(newBook);

        assertThat(savedBook)
                .isNotNull()
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(newBook);
    }

    @DisplayName("Should update book name")
    @Test
    public void shouldUpdateBookName() {
        var existingBook = bookRepository.findAll().get(0);

        var updatedBook = new Book(
                existingBook.getId(),
                "Дубровский",
                existingBook.getAuthor(),
                existingBook.getGenre()
        );

        var savedBook = bookRepository.save(updatedBook);

        assertThat(savedBook)
                .isNotNull()
                .isEqualTo(updatedBook);
    }

    @DisplayName("Should delete book with comments")
    @Test
    public void shouldDeleteExistingBook() {
        var author = authorRepository.findAll().get(0);
        var genre = genreRepository.findAll().get(0);

        var newBook = new Book(
                "Дубровский",
                author,
                genre
        );

        var savedBook = bookRepository.save(newBook);

        var bookComment = new Comment("Супер!", savedBook);

        var savedComment = commentRepository.save(bookComment);

        bookRepository.deleteById(savedBook.getId());

        var actualBook = bookRepository.findById(savedBook.getId());

        assertThat(actualBook)
                .isNotPresent();

        var actualComment = commentRepository.findById(savedComment.getId());

        assertThat(actualComment)
                .isNotPresent();
    }
}
