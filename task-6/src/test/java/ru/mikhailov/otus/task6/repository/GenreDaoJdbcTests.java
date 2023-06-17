package ru.mikhailov.otus.task6.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.mikhailov.otus.task6.domain.model.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Genre Repository Tests")
@DataJpaTest
@Import(GenreRepositoryJpa.class)
public class GenreDaoJdbcTests {

    private static final int EXPECTED_GENRES_COUNT = 1;

    public static final Long EXISTING_GENRE_ID = 1L;
    public static final String EXISTING_GENRE_NAME = "Роман";

    @Autowired
    private GenreRepository repository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("Should return expected genres count")
    @Test
    public void shouldReturnExpectedGenresCount() {
        var genres = repository.findAll();

        assertThat(genres).hasSize(EXPECTED_GENRES_COUNT);
    }

    @DisplayName("Should return expected genre by id")
    @Test
    public void shouldReturnExpectedGenreById() {
        var actualGenre = repository.findById(EXISTING_GENRE_ID);

        var expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);

        assertThat(actualGenre)
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("Should save new genre")
    @Test
    public void shouldSaveNewGenre() {
        var newGenre = new Genre(null, "Ода");

        var savedGenre = repository.save(newGenre);

        em.clear();

        var actualGenre = em.find(Genre.class, savedGenre.getId());

        assertThat(actualGenre)
                .isNotNull()
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(newGenre);
    }

}
