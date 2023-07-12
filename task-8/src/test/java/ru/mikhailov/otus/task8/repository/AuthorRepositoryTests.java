package ru.mikhailov.otus.task8.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mikhailov.otus.task8.domain.model.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Author Repository Tests")
public class AuthorRepositoryTests extends AbstractRepositoryTest {
    public static final String EXISTING_AUTHOR_NAME = "Пушкин";

    @Autowired
    private AuthorRepository repository;

    @DisplayName("Should return expected authors count")
    @Test
    public void shouldReturnExpectedAuthorsCount() {
        var authors = repository.findAll();

        assertThat(authors).hasSizeGreaterThan(1);
    }

    @DisplayName("Should return expected author by id")
    @Test
    public void shouldReturnExpectedAuthorById() {
        var existingAuthor = repository.findAll().get(0);

        var actualAuthor = repository.findById(existingAuthor.getId());

        var expectedAuthor = new Author(existingAuthor.getId(), EXISTING_AUTHOR_NAME);

        assertThat(actualAuthor)
                .isPresent()
                .contains(expectedAuthor);
    }

    @DisplayName("Should return empty optional")
    @Test
    public void shouldReturnEmptyOptional() {
        var actual = repository.findById("1");

        assertThat(actual)
                .isNotPresent();
    }

    @DisplayName("Should save new author")
    @Test
    public void shouldSaveNewAuthor() {
        var newAuthor = new Author( "Достоевский");

        var savedAuthor = repository.save(newAuthor);

        assertThat(savedAuthor)
                .isNotNull()
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(newAuthor);
    }

}
