package ru.mikhailov.otus.task5.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.mikhailov.otus.task5.domain.model.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Genre DAO Tests")
@JdbcTest
@Import(GenreDaoJdbc.class)
public class GenreDaoJdbcTests {

    private static final int EXPECTED_GENRES_COUNT = 1;

    public static final Long EXISTING_GENRE_ID = 1L;
    public static final String EXISTING_GENRE_NAME = "Роман";

    @Autowired
    private GenreDao genreDao;

    @DisplayName("Should return expected genres count")
    @Test
    public void shouldReturnExpectedGenresCount() {
        var genres = genreDao.findAll();

        assertThat(genres).hasSize(EXPECTED_GENRES_COUNT);
    }

    @DisplayName("Should return expected genre by id")
    @Test
    public void shouldReturnExpectedGenreById() {
        var actualGenre = genreDao.findById(EXISTING_GENRE_ID);

        var expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);

        assertThat(actualGenre)
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("Should save new genre")
    @Test
    public void shouldSaveNewGenre() {
        var newGenre = new Genre(null, "Ода");

        var savedGenre = genreDao.save(newGenre);

        var actualGenre = genreDao.findById(savedGenre.getId());

        assertThat(actualGenre)
                .isNotNull()
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(newGenre);
    }

}
