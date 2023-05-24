package ru.mikhailov.otus.task5.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mikhailov.otus.task5.dao.AuthorDao;
import ru.mikhailov.otus.task5.dao.BookDao;
import ru.mikhailov.otus.task5.dao.GenreDao;
import ru.mikhailov.otus.task5.domain.dto.BookDto;
import ru.mikhailov.otus.task5.domain.model.Book;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    private final AuthorDao authorDao;

    private final GenreDao genreDao;

    @Override
    public Book save(BookDto book) {
        var author = authorDao.findById(book.authorId());
        var genre = genreDao.findById(book.genreId());
        var newBook = new Book(
                null,
                book.name(),
                author,
                genre
        );

        return bookDao.save(newBook);
    }

    @Override
    public void updateById(Long id, BookDto book) {
        var newBook = bookDao.findById(id);

        if (Objects.nonNull(book.name())) {
            newBook.setName(book.name());
        }

        if (Objects.nonNull(book.authorId())) {
            var author = authorDao.findById(book.authorId());

            newBook.setAuthor(author);
        }

        if (Objects.nonNull(book.genreId())) {
            var genre = genreDao.findById(book.genreId());
            newBook.setGenre(genre);
        }

        bookDao.update(newBook);
    }

    @Override
    public Book findById(Long id) {
        return bookDao.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public void deleteById(Long id) {
        bookDao.deleteById(id);
    }
}
