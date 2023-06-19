package ru.mikhailov.otus.task6.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailov.otus.task6.domain.error.EntityNotFoundException;
import ru.mikhailov.otus.task6.domain.model.Comment;
import ru.mikhailov.otus.task6.repository.BookRepository;
import ru.mikhailov.otus.task6.domain.dto.BookDto;
import ru.mikhailov.otus.task6.domain.model.Book;

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
    public Book save(BookDto book) {
        var author = authorService.findById(book.authorId());
        var genre = genreService.findById(book.genreId());
        var newBook = new Book(
                null,
                book.name(),
                author,
                genre
        );

        return bookRepository.save(newBook);
    }

    @Override
    @Transactional
    public void updateById(Long id, BookDto book) {
        var newBook = findById(id);

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
    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book with id %s not found".formatted(id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getBookComments(Long id) {
        return bookRepository.getBookCommentsById(id);
    }

}
