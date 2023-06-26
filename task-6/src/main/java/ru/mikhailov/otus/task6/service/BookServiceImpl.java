package ru.mikhailov.otus.task6.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailov.otus.task6.domain.dto.BookDto;
import ru.mikhailov.otus.task6.domain.dto.BookEntityDto;
import ru.mikhailov.otus.task6.domain.error.EntityNotFoundException;
import ru.mikhailov.otus.task6.domain.model.Book;
import ru.mikhailov.otus.task6.repository.BookRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    private final GenreService genreService;

    @Override
    @Transactional
    public BookEntityDto save(BookDto book) {
        var author = authorService.findById(book.authorId());
        var genre = genreService.findById(book.genreId());
        var newBook = new Book(
                book.id(),
                book.name(),
                author,
                genre
        );

        var savedBook = bookRepository.save(newBook);

        return new BookEntityDto(savedBook);
    }

    @Override
    @Transactional
    public void update(BookDto book) {
        var existingBook = findById(book.id());
        var newBook = new Book(
                existingBook.id(),
                existingBook.name(),
                existingBook.author(),
                existingBook.genre()
        );

        if (Objects.nonNull(book.name())) {
            newBook.setName(book.name());
        }

        if (Objects.nonNull(book.authorId())) {
            var author = authorService.findById(book.authorId());

            newBook.setAuthor(author);
        }

        if (Objects.nonNull(book.genreId())) {
            var genre = genreService.findById(book.genreId());
            newBook.setGenre(genre);
        }

        bookRepository.update(newBook);
    }

    @Override
    @Transactional(readOnly = true)
    public BookEntityDto findById(Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book with id %s not found".formatted(id)));
        return new BookEntityDto(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookEntityDto> findAll() {
        return bookRepository.findAll().stream()
                .map(BookEntityDto::new)
                .toList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

}
