package ru.mikhailov.otus.task5.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.mikhailov.otus.task5.domain.model.Author;
import ru.mikhailov.otus.task5.domain.model.Book;
import ru.mikhailov.otus.task5.domain.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Book save(Book book) {
        String sql = """
                INSERT INTO books (name, author_id, genre_id)
                VALUES (:name, :author_id, :genre_id)
                """;

        var parameters = Map.of(
                "name", book.getName(),
                "author_id", book.getAuthor().getId(),
                "genre_id", book.getGenre().getId()
        );

        var keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, new MapSqlParameterSource(parameters), keyHolder);

        book.setId(keyHolder.getKey().longValue());

        return book;
    }

    @Override
    public Book findById(Long id) {
        String sql = """
                SELECT
                    b.id AS book_id,
                    b.name AS book_name,
                    a.id AS author_id,
                    a.name AS author_name,
                    g.id AS genre_id,
                    g.name AS genre_name
                FROM books AS b
                INNER JOIN authors AS a ON b.author_id = a.id
                INNER JOIN genres AS g ON b.genre_id = g.id
                WHERE b.id = :id
                """;

        var parameters = Map.of("id", id);

        return jdbcTemplate.queryForObject(sql, parameters, new BookMapper());
    }

    @Override
    public List<Book> findAll() {
        String sql = """
                SELECT
                    b.id AS book_id,
                    b.name AS book_name,
                    a.id AS author_id,
                    a.name AS author_name,
                    g.id AS genre_id,
                    g.name AS genre_name
                FROM books AS b
                INNER JOIN authors AS a ON b.author_id = a.id
                INNER JOIN genres AS g ON b.genre_id = g.id
                """;

        return jdbcTemplate.query(sql, new BookMapper());
    }

    @Override
    public void update(Book book) {
        String sql = """
                UPDATE books SET
                name = :name,
                author_id = :author_id,
                genre_id = :genre_id
                WHERE id = :id
                """;

        var parameters = Map.of(
                "id", book.getId(),
                "name", book.getName(),
                "author_id", book.getAuthor().getId(),
                "genre_id", book.getGenre().getId()
        );

        jdbcTemplate.update(sql, parameters);
    }

    @Override
    public void deleteById(Long id) {
        String sql = """
                DELETE FROM books WHERE id = :id
                """;

        var parameters = Map.of("id", id);

        jdbcTemplate.update(sql, parameters);
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long bookId = rs.getLong("book_id");
            String bookName = rs.getString("book_name");
            Long authorId = rs.getLong("author_id");
            String authorName = rs.getString("author_name");
            Long genreId = rs.getLong("genre_id");
            String genreName = rs.getString("genre_name");
            Genre genre = new Genre(genreId, genreName);
            Author author = new Author(authorId, authorName);
            return new Book(bookId, bookName, author, genre);
        }
    }

}
