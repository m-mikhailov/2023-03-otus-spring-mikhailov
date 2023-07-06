package ru.mikhailov.otus.task7.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.mikhailov.otus.task7.domain.model.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Author Repository Tests")
@DataJpaTest
public class AuthorRepositoryTests {

    private static final int EXPECTED_AUTHORS_COUNT = 1;

    public static final Long EXISTING_AUTHOR_ID = 1L;
    public static final String EXISTING_AUTHOR_NAME = "Пушкин";

    @Autowired
    private AuthorRepository repository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("Should return expected authors count")
    @Test
    public void shouldReturnExpectedAuthorsCount() {
        var authors = repository.findAll();

        assertThat(authors).hasSize(EXPECTED_AUTHORS_COUNT);
    }

    @DisplayName("Should return expected author by id")
    @Test
    public void shouldReturnExpectedAuthorById() {
        var actualAuthor = repository.findById(EXISTING_AUTHOR_ID);

        var expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);

        assertThat(actualAuthor)
                .isPresent()
                .contains(expectedAuthor);
    }

    @DisplayName("Should return empty optional")
    @Test
    public void shouldReturnEmptyOptional() {
        var actual = repository.findById(10L);

        assertThat(actual)
                .isNotPresent();
    }

    @DisplayName("Should save new author")
    @Test
    public void shouldSaveNewAuthor() {
        var newAuthor = new Author(null, "Достоевский");

        var savedAuthor = repository.save(newAuthor);

        em.clear();

        var actualAuthor = em.find(Author.class, savedAuthor.getId());

        assertThat(actualAuthor)
                .isNotNull()
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(newAuthor);
    }

}
