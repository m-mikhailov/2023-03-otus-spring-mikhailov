package ru.mikhailov.otus.task11.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.mikhailov.otus.task11.domain.dto.BookCreateDto;
import ru.mikhailov.otus.task11.domain.dto.BookDto;
import ru.mikhailov.otus.task11.domain.dto.BookUpdateDto;
import ru.mikhailov.otus.task11.domain.error.EntityNotFoundException;
import ru.mikhailov.otus.task11.domain.model.Book;
import ru.mikhailov.otus.task11.repository.AuthorRepository;
import ru.mikhailov.otus.task11.repository.BookRepository;
import ru.mikhailov.otus.task11.repository.GenreRepository;

import java.util.function.Function;

import static ru.mikhailov.otus.task11.domain.error.EntityNotFoundException.*;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    @Override
    public Mono<BookDto> save(BookCreateDto book) {
        return authorRepository.findById(book.authorId())
                .switchIfEmpty(Mono.error(new EntityNotFoundException(AUTHOR_MESSAGE_FORMAT, book.authorId())))
                .flatMap(author -> genreRepository.findById(book.genreId())
                        .map(genre -> new Book(book.name(), author, genre))
                        .switchIfEmpty(Mono.error(
                                new EntityNotFoundException(GENRE_MESSAGE_FORMAT, book.genreId()))))
                .flatMap(bookRepository::save)
                .map(BookDto::new);
    }

    @Override
    public Mono<Void> update(BookUpdateDto bookDto) {
        Function<Book, Mono<Book>> updateBookName = (Book book) ->
                Mono.justOrEmpty(bookDto.name())
                        .defaultIfEmpty(book.getName())
                        .map(name -> {
                            book.setName(name);
                            return book;
                        });

        Function<Book, Mono<Book>> updateBookAuthor = (Book book) ->
                Mono.justOrEmpty(bookDto.authorId())
                        .defaultIfEmpty(book.getAuthor().getId())
                        .flatMap(authorRepository::findById)
                        .switchIfEmpty(Mono.error(new EntityNotFoundException(AUTHOR_MESSAGE_FORMAT, book.getAuthor().getId())))
                        .map(author -> {
                            book.setAuthor(author);
                            return book;
                        });

        Function<Book, Mono<Book>> updateBookGenre = (Book book) ->
                Mono.justOrEmpty(bookDto.genreId())
                        .defaultIfEmpty(book.getGenre().getId())
                        .flatMap(genreRepository::findById)
                        .switchIfEmpty(Mono.error(new EntityNotFoundException(GENRE_MESSAGE_FORMAT, book.getGenre().getId())))
                        .map(genre -> {
                            book.setGenre(genre);
                            return book;
                        });

        return bookRepository.findById(bookDto.id())
                .switchIfEmpty(Mono.error(new EntityNotFoundException(BOOK_MESSAGE_FORMAT, bookDto.id())))
                .flatMap(updateBookName)
                .flatMap(updateBookAuthor)
                .flatMap(updateBookGenre)
                .flatMap(bookRepository::save)
                .then();
    }

    @Override
    public Mono<BookDto> findById(String id) {
        return bookRepository.findById(id)
                .switchIfEmpty(Mono.error(new EntityNotFoundException(BOOK_MESSAGE_FORMAT, id)))
                .map(BookDto::new);
    }

    @Override
    public Flux<BookDto> findAll() {
        return bookRepository.findAll()
                .map(BookDto::new);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return bookRepository.deleteById(id);
    }

}
