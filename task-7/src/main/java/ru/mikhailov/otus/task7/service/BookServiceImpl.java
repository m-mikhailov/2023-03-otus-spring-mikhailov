package ru.mikhailov.otus.task7.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailov.otus.task7.domain.dto.BookCreateDto;
import ru.mikhailov.otus.task7.domain.dto.BookDto;
import ru.mikhailov.otus.task7.domain.dto.BookUpdateDto;
import ru.mikhailov.otus.task7.domain.error.AuthorNotFoundException;
import ru.mikhailov.otus.task7.domain.error.BookNotFoundException;
import ru.mikhailov.otus.task7.domain.error.GenreNotFoundException;
import ru.mikhailov.otus.task7.domain.model.Book;
import ru.mikhailov.otus.task7.repository.AuthorRepository;
import ru.mikhailov.otus.task7.repository.BookRepository;
import ru.mikhailov.otus.task7.repository.GenreRepository;

import java.util.List;
import java.util.Objects;

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
                .orElseThrow(() -> new AuthorNotFoundException(book.authorId()));
        var genre = genreRepository.findById(book.genreId())
                .orElseThrow(() -> new GenreNotFoundException(book.genreId()));

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
                .orElseThrow(() -> new BookNotFoundException(bookDto.id()));

        if (Objects.nonNull(bookDto.name())) {
            book.setName(bookDto.name());
        }

        if (Objects.nonNull(bookDto.authorId())) {
            var author = authorRepository.findById(bookDto.authorId())
                    .orElseThrow(() -> new AuthorNotFoundException(bookDto.authorId()));

            book.setAuthor(author);
        }

        if (Objects.nonNull(bookDto.genreId())) {
            var genre = genreRepository.findById(bookDto.genreId())
                    .orElseThrow(() -> new GenreNotFoundException(bookDto.genreId()));
            book.setGenre(genre);
        }

        bookRepository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public BookDto findById(Long id) {
        return bookRepository.findById(id)
                .map(BookDto::new)
                .orElseThrow(() -> new BookNotFoundException(id));
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
