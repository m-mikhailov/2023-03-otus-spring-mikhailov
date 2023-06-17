package ru.mikhailov.otus.task6.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mikhailov.otus.task6.domain.model.Comment;
import ru.mikhailov.otus.task6.repository.AuthorRepository;
import ru.mikhailov.otus.task6.repository.BookRepository;
import ru.mikhailov.otus.task6.repository.GenreRepository;
import ru.mikhailov.otus.task6.domain.dto.BookDto;
import ru.mikhailov.otus.task6.domain.model.Book;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    @Override
    public Book save(BookDto book) {
        var author = authorRepository.findById(book.authorId());
        var genre = genreRepository.findById(book.genreId());
        var newBook = new Book(
                null,
                book.name(),
                author,
                genre
        );

        return bookRepository.save(newBook);
    }

    @Override
    public void updateById(Long id, BookDto book) {
        var newBook = bookRepository.findById(id);

        if (Objects.nonNull(book.name())) {
            newBook.setName(book.name());
        }

        if (Objects.nonNull(book.authorId())) {
            var author = authorRepository.findById(book.authorId());

            newBook.setAuthor(author);
        }

        if (Objects.nonNull(book.genreId())) {
            var genre = genreRepository.findById(book.genreId());
            newBook.setGenre(genre);
        }

        bookRepository.update(newBook);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Comment> getBookComments(Long id) {
        return bookRepository.getBookCommentsById(id);
    }

    @Override
    public void createBookComment(Comment comment) {
        bookRepository.createComment(comment);
    }
}
