package ru.mikhailov.otus.task5.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.mikhailov.otus.task5.domain.model.Author;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Author DAO Tests")
@JdbcTest
@Import(AuthorDaoJdbc.class)
public class AuthorDaoJdbcTests {

    private static final int EXPECTED_AUTHORS_COUNT = 1;

    public static final Long EXISTING_AUTHOR_ID = 1L;
    public static final String EXISTING_AUTHOR_NAME = "Пушкин";

    @Autowired
    private AuthorDao authorDao;

    @DisplayName("Should return expected authors count")
    @Test
    public void shouldReturnExpectedAuthorsCount() {
        var authors = authorDao.findAll();

        assertThat(authors).hasSize(EXPECTED_AUTHORS_COUNT);
    }

    @DisplayName("Should return expected author by id")
    @Test
    public void shouldReturnExpectedAuthorById() {
        var actualAuthor = authorDao.findById(EXISTING_AUTHOR_ID);

        var expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);

        assertThat(actualAuthor)
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("Should save new author")
    @Test
    public void shouldSaveNewAuthor() {
        var newAuthor = new Author(null, "Достоевский");

        var savedAuthor = authorDao.save(newAuthor);

        var actualAuthor = authorDao.findById(savedAuthor.getId());

        assertThat(actualAuthor)
                .isNotNull()
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(newAuthor);
    }

}
