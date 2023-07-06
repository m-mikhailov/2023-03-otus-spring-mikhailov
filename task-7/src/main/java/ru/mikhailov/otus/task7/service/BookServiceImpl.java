package ru.mikhailov.otus.task7.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailov.otus.task7.domain.dto.BookCreateDto;
import ru.mikhailov.otus.task7.domain.dto.BookDto;
import ru.mikhailov.otus.task7.domain.dto.BookUpdateDto;
import ru.mikhailov.otus.task7.domain.error.EntityNotFoundException;
import ru.mikhailov.otus.task7.domain.model.Book;
import ru.mikhailov.otus.task7.repository.AuthorRepository;
import ru.mikhailov.otus.task7.repository.BookRepository;
import ru.mikhailov.otus.task7.repository.GenreRepository;

import java.util.List;
import java.util.Objects;

import static ru.mikhailov.otus.task7.domain.error.EntityNotFoundException.*;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    @Override
    @Transactional
    public BookDto save(BookCreateDto book) {
        var author = authorRepository.findById(book.authorId())
                .orElseThrow(() -> new EntityNotFoundException(AUTHOR_MESSAGE_FORMAT, book.authorId()));
        var genre = genreRepository.findById(book.genreId())
                .orElseThrow(() -> new EntityNotFoundException(GENRE_MESSAGE_FORMAT, book.genreId()));

        var newBook = new Book(
                book.name(),
                author,
                genre
        );

        var savedBook = bookRepository.save(newBook);

        return new BookDto(savedBook);
    }

    @Override
    @Transactional
    public void update(BookUpdateDto bookDto) {
        var book = bookRepository.findById(bookDto.id())
                .orElseThrow(() -> new EntityNotFoundException(BOOK_MESSAGE_FORMAT, bookDto.id()));

        if (Objects.nonNull(bookDto.name())) {
            book.setName(bookDto.name());
        }

        if (Objects.nonNull(bookDto.authorId())) {
            var author = authorRepository.findById(bookDto.authorId())
                    .orElseThrow(() -> new EntityNotFoundException(AUTHOR_MESSAGE_FORMAT, bookDto.authorId()));

            book.setAuthor(author);
        }

        if (Objects.nonNull(bookDto.genreId())) {
            var genre = genreRepository.findById(bookDto.genreId())
                    .orElseThrow(() -> new EntityNotFoundException(GENRE_MESSAGE_FORMAT, bookDto.genreId()));
            book.setGenre(genre);
        }

        bookRepository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public BookDto findById(Long id) {
        return bookRepository.findById(id)
                .map(BookDto::new)
                .orElseThrow(() -> new EntityNotFoundException(BOOK_MESSAGE_FORMAT, id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(BookDto::new)
                .toList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

}
