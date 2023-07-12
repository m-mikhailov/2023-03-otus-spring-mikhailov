package ru.mikhailov.otus.task8.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mikhailov.otus.task8.domain.dto.BookCreateDto;
import ru.mikhailov.otus.task8.domain.dto.BookDto;
import ru.mikhailov.otus.task8.domain.dto.BookUpdateDto;
import ru.mikhailov.otus.task8.domain.error.EntityNotFoundException;
import ru.mikhailov.otus.task8.domain.model.Book;
import ru.mikhailov.otus.task8.repository.AuthorRepository;
import ru.mikhailov.otus.task8.repository.BookRepository;
import ru.mikhailov.otus.task8.repository.GenreRepository;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    @Override
    public BookDto save(BookCreateDto book) {
        var author = authorRepository.findById(book.authorId())
                .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.AUTHOR_MESSAGE_FORMAT, book.authorId()));
        var genre = genreRepository.findById(book.genreId())
                .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.GENRE_MESSAGE_FORMAT, book.genreId()));

        var newBook = new Book(
                book.name(),
                author,
                genre
        );

        var savedBook = bookRepository.save(newBook);

        return new BookDto(savedBook);
    }

    @Override
    public void update(BookUpdateDto bookDto) {
        var book = bookRepository.findById(bookDto.id())
                .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.BOOK_MESSAGE_FORMAT, bookDto.id()));

        if (Objects.nonNull(bookDto.name())) {
            book.setName(bookDto.name());
        }

        if (Objects.nonNull(bookDto.authorId())) {
            var author = authorRepository.findById(bookDto.authorId())
                    .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.AUTHOR_MESSAGE_FORMAT, bookDto.authorId()));

            book.setAuthor(author);
        }

        if (Objects.nonNull(bookDto.genreId())) {
            var genre = genreRepository.findById(bookDto.genreId())
                    .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.GENRE_MESSAGE_FORMAT, bookDto.genreId()));
            book.setGenre(genre);
        }

        bookRepository.save(book);
    }

    @Override
    public BookDto findById(String id) {
        return bookRepository.findById(id)
                .map(BookDto::new)
                .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.BOOK_MESSAGE_FORMAT, id));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(BookDto::new)
                .toList();
    }

    @Override
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }

}
