package ru.mikhailov.otus.task5.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.mikhailov.otus.task5.domain.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Genre save(Genre genre) {
        String sql = """
                INSERT INTO genres (name)
                VALUES (:name)
                """;

        var parameters = Map.of("name", genre.getName());

        var keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, new MapSqlParameterSource(parameters), keyHolder);

        genre.setId(keyHolder.getKey().longValue());

        return genre;
    }

    @Override
    public Genre findById(Long id) {
        String sql = """
                SELECT id, name
                FROM genres
                WHERE id = :id
                """;

        var parameters = Map.of("id", id);

        return jdbcTemplate.queryForObject(sql, parameters, new GenreMapper());
    }

    @Override
    public List<Genre> findAll() {
        String sql = """
                SELECT id, name
                FROM genres
                """;

        return jdbcTemplate.query(sql, new GenreMapper());
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong("id");
            String name = rs.getString("name");

            return new Genre(id, name);
        }
    }
}
