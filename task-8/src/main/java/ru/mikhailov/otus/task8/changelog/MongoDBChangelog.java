package ru.mikhailov.otus.task8.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.mikhailov.otus.task8.domain.model.Author;
import ru.mikhailov.otus.task8.domain.model.Book;
import ru.mikhailov.otus.task8.domain.model.Genre;
import ru.mikhailov.otus.task8.repository.AuthorRepository;
import ru.mikhailov.otus.task8.repository.BookRepository;
import ru.mikhailov.otus.task8.repository.GenreRepository;

@ChangeLog(order = "001")
public class MongoDBChangelog {

    private Author pushkinAuthor;

    private Genre romanGenre;

    @ChangeSet(order = "000", id = "dropDB", author = "mmikhailov", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", author = "mmikhailov", runAlways = true)
    public void initAuthors(AuthorRepository repository) {
        pushkinAuthor = repository.save(new Author("Пушкин"));
    }

    @ChangeSet(order = "002", id = "initGenres", author = "mmikhailov", runAlways = true)
    public void initGenres(GenreRepository repository) {
        romanGenre = repository.save(new Genre("Роман"));
    }

    @ChangeSet(order = "003", id = "initBooks", author = "mmikhailov", runAlways = true)
    public void initBooks(BookRepository repository) {
        var book = new Book("Капитанская дочка", pushkinAuthor, romanGenre);
        repository.save(book);
    }



}
