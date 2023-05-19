package ru.mikhailov.otus.task5.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailov.otus.task5.dao.AuthorDao;
import ru.mikhailov.otus.task5.dao.GenreDao;
import ru.mikhailov.otus.task5.domain.dto.BookDto;
import ru.mikhailov.otus.task5.domain.model.Author;
import ru.mikhailov.otus.task5.domain.model.Book;
import ru.mikhailov.otus.task5.domain.model.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Book service tests")
@SpringBootTest
@Transactional
public class BookServiceTests {

    private static final int EXPECTED_BOOKS_COUNT = 1;

    private static final Long EXISTING_AUTHOR_ID = 1L;

    private static final Long EXISTING_GENRE_ID = 1L;

    private static final Long EXISTING_BOOK_ID = 1L;

    private static final Book EXISTING_BOOK = new Book(
            EXISTING_BOOK_ID,
            "Капитанская дочка",
            new Author(EXISTING_AUTHOR_ID, "Пушкин"),
            new Genre(EXISTING_GENRE_ID, "Роман")
    );


    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private GenreDao genreDao;

    @DisplayName("Should save new book")
    @Test
    public void shouldSaveNewBook() {
        var bookDto = new BookDto("Дубровский", EXISTING_AUTHOR_ID, EXISTING_GENRE_ID);

        var savedBook = bookService.save(bookDto);

        var actualBook = bookService.findById(savedBook.getId());

        assertThat(actualBook)
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(savedBook);
    }

    @DisplayName("Should update book name")
    @Test
    public void shouldUpdateBookName() {
        var bookDto = new BookDto("Дубровский", null, null);

        bookService.updateById(EXISTING_BOOK_ID, bookDto);

        var actualBook = bookService.findById(EXISTING_BOOK_ID);

        assertThat(actualBook)
                .isNotNull()
                .hasFieldOrPropertyWithValue("name", "Дубровский");
    }

    @DisplayName("Should update book author")
    @Test
    public void shouldUpdateBookAuthor() {

        var newAuthor = new Author(null, "Достоевский");

        var authorId = authorDao.save(newAuthor).getId();

        var bookDto = new BookDto(null, authorId, null);

        bookService.updateById(EXISTING_BOOK_ID, bookDto);

        var actualBook = bookService.findById(EXISTING_BOOK_ID);

        assertThat(actualBook).isNotNull();

        assertThat(actualBook.getAuthor())
                .isNotNull()
                .hasFieldOrPropertyWithValue("name", "Достоевский");
    }

    @DisplayName("Should update book genre")
    @Test
    public void shouldUpdateBookGenre() {

        var newGenre = new Genre(null, "Стихотворение");

        var genreId = genreDao.save(newGenre).getId();

        var bookDto = new BookDto(null, null, genreId);

        bookService.updateById(EXISTING_BOOK_ID, bookDto);

        var actualBook = bookService.findById(EXISTING_BOOK_ID);

        assertThat(actualBook).isNotNull();

        assertThat(actualBook.getGenre())
                .isNotNull()
                .hasFieldOrPropertyWithValue("name", "Стихотворение");
    }

    @DisplayName("Should return existing book by id")
    @Test
    public void shouldReturnExistingBookById() {
        var actualBook = bookService.findById(EXISTING_BOOK_ID);

        assertThat(actualBook)
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(EXISTING_BOOK);
    }

    @DisplayName("Should return all books")
    @Test
    public void shouldReturnAllBooks() {
        var books = bookService.findAll();

        assertThat(books)
                .isNotNull()
                .hasSize(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("Should delete existing book by id")
    @Test
    public void shouldDeleteExistingBookById() {
        var booksBefore = bookService.findAll();

        bookService.deleteById(EXISTING_BOOK_ID);

        var booksAfter = bookService.findAll();

        assertThat(booksBefore)
                .isNotNull()
                .hasSize(EXPECTED_BOOKS_COUNT);

        assertThat(booksAfter)
                .isNotNull()
                .isEmpty();;
    }


}
