package ru.mikhailov.otus.task6.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.mikhailov.otus.task6.domain.model.Comment;

import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryJpa implements CommentRepository {

    private final EntityManager em;

    @Override
    public Comment save(Comment comment) {
        if (Objects.isNull(comment.getId())) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public void update(Comment comment) {
        Query query = em.createQuery(
                "update Comment c set c.text = :text where c.id = :id"
        );
        query.setParameter("id", comment.getId());
        query.setParameter("text", comment.getText());
        query.executeUpdate();
    }

    @Override
    public void deleteById(Long id) {
        Query query = em.createQuery(
                "delete from Comment c where c.id = :id"
        );
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Comment> findAllByBookId(Long bookId) {
        return em.createQuery(
                        "select c from Comment c " +
                                "where c.book.id = :book_id",
                        Comment.class
                )
                .setParameter("book_id", bookId)
                .getResultList();
    }
}
