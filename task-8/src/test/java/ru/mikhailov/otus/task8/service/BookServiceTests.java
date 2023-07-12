package ru.mikhailov.otus.task8.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.mikhailov.otus.task8.domain.dto.BookCreateDto;
import ru.mikhailov.otus.task8.domain.dto.BookDto;
import ru.mikhailov.otus.task8.domain.dto.BookUpdateDto;
import ru.mikhailov.otus.task8.domain.model.Author;
import ru.mikhailov.otus.task8.domain.model.Book;
import ru.mikhailov.otus.task8.domain.model.Genre;
import ru.mikhailov.otus.task8.repository.AuthorRepository;
import ru.mikhailov.otus.task8.repository.BookRepository;
import ru.mikhailov.otus.task8.repository.GenreRepository;

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

    private static final String EXISTING_AUTHOR_ID = "1";

    private static final String EXISTING_GENRE_ID = "1";

    private static final String EXISTING_BOOK_ID = "1";

    private static final Author EXISTING_AUTHOR = new Author(EXISTING_AUTHOR_ID, "Пушкин");

    private static final Genre EXISTING_GENRE = new Genre(EXISTING_GENRE_ID, "Роман");

    private static final Book EXISTING_BOOK = new Book(
            EXISTING_BOOK_ID,
            "Капитанская дочка",
            EXISTING_AUTHOR,
            EXISTING_GENRE
    );

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private GenreRepository genreRepository;

    @DisplayName("Should save new book")
    @Test
    public void shouldSaveNewBook() {

        given(authorRepository.findById(EXISTING_AUTHOR_ID))
                .willReturn(Optional.of(EXISTING_AUTHOR));

        given(genreRepository.findById(EXISTING_GENRE_ID))
                .willReturn(Optional.of(EXISTING_GENRE));

        var newBook = new Book("2", "Дубровский", EXISTING_AUTHOR, EXISTING_GENRE);

        given(bookRepository.save(any(Book.class)))
                .willReturn(newBook);

        var bookDto = new BookCreateDto("Дубровский", EXISTING_AUTHOR_ID, EXISTING_GENRE_ID);

        var actualBook = bookService.save(bookDto);

        var expectedBook = new BookDto(newBook);

        assertThat(actualBook)
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("Should update book name")
    @Test
    public void shouldUpdateBookName() {

        given(bookRepository.findById(EXISTING_BOOK_ID))
                .willReturn(Optional.of(new Book(EXISTING_BOOK_ID, "Капитанская дочка", EXISTING_AUTHOR, EXISTING_GENRE)));

        var bookDto = new BookUpdateDto(EXISTING_BOOK_ID, "Дубровский", null, null);

        bookService.update(bookDto);

        var expectedBook = new Book(EXISTING_BOOK_ID, "Дубровский", EXISTING_AUTHOR, EXISTING_GENRE);

        verify(bookRepository).save(eq(expectedBook));

    }

    @DisplayName("Should update book author")
    @Test
    public void shouldUpdateBookAuthor() {

        given(bookRepository.findById(EXISTING_BOOK_ID))
                .willReturn(Optional.of(new Book(EXISTING_BOOK_ID, "Капитанская дочка", EXISTING_AUTHOR, EXISTING_GENRE)));

        var newAuthor = new Author("2", "Достоевский");

        given(authorRepository.findById(newAuthor.getId()))
                .willReturn(Optional.of(newAuthor));


        var bookDto = new BookUpdateDto(EXISTING_BOOK_ID, null, newAuthor.getId(), null);

        bookService.update(bookDto);

        var expectedBook = new Book(EXISTING_BOOK_ID, "Капитанская дочка", newAuthor, EXISTING_GENRE);

        verify(bookRepository).save(eq(expectedBook));
    }

    @DisplayName("Should update book genre")
    @Test
    public void shouldUpdateBookGenre() {

        given(bookRepository.findById(EXISTING_BOOK_ID))
                .willReturn(Optional.of(new Book(EXISTING_BOOK_ID, "Капитанская дочка", EXISTING_AUTHOR, EXISTING_GENRE)));

        var newGenre = new Genre("2", "Стихотворение");

        given(genreRepository.findById(newGenre.getId()))
                .willReturn(Optional.of(newGenre));

        var bookDto = new BookUpdateDto(EXISTING_BOOK_ID, null, null, newGenre.getId());

        bookService.update(bookDto);

        var expectedBook = new Book(EXISTING_BOOK_ID, "Капитанская дочка", EXISTING_AUTHOR, newGenre);

        verify(bookRepository).save(eq(expectedBook));
    }

    @DisplayName("Should return existing book by id")
    @Test
    public void shouldReturnExistingBookById() {

        given(bookRepository.findById(EXISTING_BOOK_ID))
                .willReturn(Optional.of(EXISTING_BOOK));

        var actualBook = bookService.findById(EXISTING_BOOK_ID);

        var expectedBook = new BookDto(EXISTING_BOOK);

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
