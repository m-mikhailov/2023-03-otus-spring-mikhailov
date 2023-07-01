package ru.mikhailov.otus.task7.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.mikhailov.otus.task7.domain.dto.BookDto;
import ru.mikhailov.otus.task7.domain.dto.BookEntityDto;
import ru.mikhailov.otus.task7.domain.model.Author;
import ru.mikhailov.otus.task7.domain.model.Book;
import ru.mikhailov.otus.task7.domain.model.Genre;
import ru.mikhailov.otus.task7.repository.BookRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@DisplayName("Book service tests")
@SpringBootTest(
        classes = BookServiceImpl.class
)
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

    private static final Author EXISTING_AUTHOR = new Author(1L, "Пушкин");

    private static final Genre EXISTING_GENRE = new Genre(1L, "Роман");

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @BeforeEach
    public void setUp() {
        given(bookRepository.findById(EXISTING_BOOK_ID))
                .willReturn(Optional.of(EXISTING_BOOK));
    }

    @DisplayName("Should save new book")
    @Test
    public void shouldSaveNewBook() {

        given(authorService.findById(EXISTING_AUTHOR_ID))
                .willReturn(EXISTING_AUTHOR);

        given(genreService.findById(EXISTING_GENRE_ID))
                .willReturn(EXISTING_GENRE);

        var newBook = new Book(2L, "Дубровский", EXISTING_AUTHOR, EXISTING_GENRE);

        given(bookRepository.save(any(Book.class)))
                .willReturn(newBook);

        var bookDto = new BookDto(null, "Дубровский", EXISTING_AUTHOR_ID, EXISTING_GENRE_ID);

        var actualBook = bookService.save(bookDto);

        var expectedBook = new BookEntityDto(newBook);

        assertThat(actualBook)
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("Should update book name")
    @Test
    public void shouldUpdateBookName() {

        var bookDto = new BookDto(EXISTING_BOOK_ID, "Дубровский", null, null);

        bookService.update(bookDto);

        var expectedBook = new Book(EXISTING_BOOK_ID, "Дубровский", EXISTING_AUTHOR, EXISTING_GENRE);

        verify(bookRepository).save(eq(expectedBook));

    }

    @DisplayName("Should update book author")
    @Test
    public void shouldUpdateBookAuthor() {

        var newAuthor = new Author(2L, "Достоевский");

        given(authorService.findById(newAuthor.getId()))
                .willReturn(newAuthor);


        var bookDto = new BookDto(EXISTING_BOOK_ID, null, newAuthor.getId(), null);

        bookService.update(bookDto);

        var expectedBook = new Book(EXISTING_BOOK_ID, "Капитанская дочка", newAuthor, EXISTING_GENRE);

        verify(bookRepository).save(eq(expectedBook));
    }

    @DisplayName("Should update book genre")
    @Test
    public void shouldUpdateBookGenre() {

        var newGenre = new Genre(2L, "Стихотворение");

        given(genreService.findById(newGenre.getId()))
                .willReturn(newGenre);

        var bookDto = new BookDto(EXISTING_BOOK_ID, null, null, newGenre.getId());

        bookService.update(bookDto);

        var expectedBook = new Book(EXISTING_BOOK_ID, "Капитанская дочка", EXISTING_AUTHOR, newGenre);

        verify(bookRepository).save(eq(expectedBook));
    }

    @DisplayName("Should return existing book by id")
    @Test
    public void shouldReturnExistingBookById() {

        var actualBook = bookService.findById(EXISTING_BOOK_ID);

        var expectedBook = new BookEntityDto(EXISTING_BOOK);

        assertThat(actualBook)
                .isNotNull()
                .isEqualTo(expectedBook);
    }

    @DisplayName("Should return all books")
    @Test
    public void shouldReturnAllBooks() {
        given(bookRepository.findAll())
                .willReturn(List.of(EXISTING_BOOK));

        var books = bookService.findAll();

        assertThat(books)
                .isNotNull()
                .hasSize(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("Should delete existing book by id")
    @Test
    public void shouldDeleteExistingBookById() {

        bookService.deleteById(EXISTING_BOOK_ID);

        verify(bookRepository).deleteById(eq(EXISTING_BOOK_ID));
    }


}
