package ru.mikhailov.otus.task5.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.mikhailov.otus.task5.domain.model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Author save(Author author) {
        String sql = """
                INSERT INTO authors (name)
                VALUES (:name)
                """;

        var parameters = Map.of("name", author.getName());

        var keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, new MapSqlParameterSource(parameters), keyHolder);

        author.setId(keyHolder.getKey().longValue());

        return author;
    }

    @Override
    public Author findById(Long id) {

        String sql = """
                SELECT id, name
                FROM authors
                WHERE id = :id
                """;

        var parameters = Map.of("id", id);

        return jdbcTemplate.queryForObject(sql, parameters, new AuthorMapper());
    }

    @Override
    public List<Author> findAll() {
        String sql = """
                SELECT id, name
                FROM authors
                """;

        return jdbcTemplate.query(sql, new AuthorMapper());
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong("id");
            String name = rs.getString("name");

            return new Author(id, name);
        }
    }
}
