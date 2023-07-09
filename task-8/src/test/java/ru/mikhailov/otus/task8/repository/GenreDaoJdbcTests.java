package ru.mikhailov.otus.task8.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mikhailov.otus.task8.domain.model.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Genre Repository Tests")
public class GenreDaoJdbcTests extends AbstractRepositoryTest {

    public static final String EXISTING_GENRE_NAME = "Роман";

    @Autowired
    private GenreRepository repository;

    @DisplayName("Should return expected genres count")
    @Test
    public void shouldReturnExpectedGenresCount() {
        var genres = repository.findAll();

        assertThat(genres).hasSizeGreaterThan(1);
    }

    @DisplayName("Should return expected genre by id")
    @Test
    public void shouldReturnExpectedGenreById() {
        var existingGenre = repository.findAll().get(0);

        var actualGenre = repository.findById(existingGenre.getId());

        var expectedGenre = new Genre(existingGenre.getId(), EXISTING_GENRE_NAME);

        assertThat(actualGenre)
                .isPresent()
                .contains(expectedGenre);
    }

    @DisplayName("Should return empty optional")
    @Test
    public void shouldReturnEmptyOptional() {
        var actual = repository.findById("1");

        assertThat(actual)
                .isNotPresent();
    }

    @DisplayName("Should save new genre")
    @Test
    public void shouldSaveNewGenre() {
        var newGenre = new Genre( "Ода");

        var savedGenre = repository.save(newGenre);

        assertThat(savedGenre)
                .isNotNull()
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(newGenre);
    }

}
