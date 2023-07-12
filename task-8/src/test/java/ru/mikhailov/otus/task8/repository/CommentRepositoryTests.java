package ru.mikhailov.otus.task8.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mikhailov.otus.task8.domain.model.Comment;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Comment Repository Tests")
public class CommentRepositoryTests extends AbstractRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BookRepository bookRepository;

    @DisplayName("Should create new book comment")
    @Test
    public void shouldCreateNewBookComment() {

        var existingBook = bookRepository.findAll().get(0);

        var newComment = new Comment(
                "Отличный роман!",
                existingBook
        );

        var savedComment = commentRepository.save(newComment);

        assertThat(savedComment)
                .isNotNull()
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(newComment);
    }

    @DisplayName("Should update comment")
    @Test
    public void shouldUpdateComment() {
        var existingBook = bookRepository.findAll().get(0);

        var newComment = new Comment(
                "Без изменений",
                existingBook
        );

        var savedComment = commentRepository.save(newComment);

        var commentUpdate = new Comment(savedComment.getText(), "Текст изменен", existingBook);

        var actualComment = commentRepository.save(commentUpdate);

        assertThat(actualComment)
                .isNotNull()
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(commentUpdate);
    }

    @DisplayName("Should delete comment by id")
    @Test
    public void shouldDeleteCommentById() {
        var existingBook = bookRepository.findAll().get(0);

        var newComment = new Comment(
                "Без изменений",
                existingBook
        );

        var commentId = commentRepository.save(newComment).getId();

        commentRepository.deleteById(commentId);

        var actualComment = commentRepository.findById(commentId);

        assertThat(actualComment)
                .isNotPresent();
    }

    @DisplayName("Should return all book comments")
    @Test
    public void shouldReturnAllCommetsByBookId() {
        var existingBook = bookRepository.findAll().get(0);

        commentRepository.save(new Comment("Отличный роман!", existingBook));
        commentRepository.save(new Comment("Рекомендую всем!", existingBook));

        var actualComments = commentRepository.findAllByBookId(existingBook.getId());

        assertThat(actualComments)
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(2);
    }

    @DisplayName("Should delete all book comments")
    @Test
    public void shouldDeleteAllCommetsByBookId() {
        var existingBook = bookRepository.findAll().get(0);

        commentRepository.save(new Comment("Отличный роман!", existingBook));
        commentRepository.save(new Comment("Рекомендую всем!", existingBook));

        commentRepository.deleteAllByBookId(existingBook.getId());

        var actualComments = commentRepository.findAllByBookId(existingBook.getId());

        assertThat(actualComments)
                .isEmpty();
    }

}
