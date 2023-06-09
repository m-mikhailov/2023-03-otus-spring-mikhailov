package ru.mikhailov.otus.task7.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.mikhailov.otus.task7.domain.model.Book;
import ru.mikhailov.otus.task7.domain.model.Comment;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Comment Repository Tests")
@DataJpaTest
public class CommentRepositoryTests {

    public static final Long EXISTING_BOOK_ID = 1L;

    @Autowired
    private CommentRepository repository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("Should create new book comment")
    @Test
    public void shouldCreateNewBookComment() {

        var newComment = new Comment(
                null,
                "Отличный роман!",
                new Book(1L, null, null, null)
        );

        var savedComment = repository.save(newComment);

        em.clear();

        var actualComment = em.getEntityManager().createQuery(
                "select c from Comment c where c.id = :id", Comment.class
        ).setParameter("id", savedComment.getId()).getSingleResult();

        assertThat(actualComment)
                .isNotNull()
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(newComment);
    }

    @DisplayName("Should update comment")
    @Test
    public void shouldUpdateComment() {
        var newComment = new Comment(
                null,
                "Без изменений",
                new Book(EXISTING_BOOK_ID, null, null, null)
        );

        var commentId = em.persist(newComment).getId();

        var commentUpdate = new Comment(commentId, "Текст изменен", null);

        repository.save(commentUpdate);

        var actualComment = em.find(Comment.class, commentId);

        assertThat(actualComment)
                .isNotNull()
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(commentUpdate);
    }

    @DisplayName("Should delete comment by id")
    @Test
    public void shouldDeleteCommentById() {
        var newComment = new Comment(
                null,
                "Без изменений",
                new Book(EXISTING_BOOK_ID, null, null, null)
        );

        var commentId = em.persist(newComment).getId();

        em.clear();

        repository.deleteById(commentId);

        var actualComment = Optional.ofNullable(em.find(Comment.class, commentId));

        assertThat(actualComment)
                .isNotPresent();
    }

    @DisplayName("Should return all book comments")
    @Test
    public void shouldReturnAllCommetsByBookId() {
        em.persist(new Comment(null, "Отличный роман!", new Book(1L, null, null, null)));
        em.persist(new Comment(null, "Рекомендую всем!", new Book(1L, null, null, null)));

        Long commentsCount = (Long) em.getEntityManager().createQuery(
                        "select count(c) from Comment c where c.book.id = :book_id"
                )
                .setParameter("book_id", EXISTING_BOOK_ID)
                .getSingleResult();

        var actualComments = repository.findAllByBookId(EXISTING_BOOK_ID);

        assertThat(actualComments)
                .isNotEmpty()
                .hasSize(commentsCount.intValue());
    }

}
