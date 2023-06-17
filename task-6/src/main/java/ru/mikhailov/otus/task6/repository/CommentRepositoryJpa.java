package ru.mikhailov.otus.task6.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailov.otus.task6.domain.model.Comment;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryJpa implements CommentRepository {

    private final EntityManager em;

    @Override
    @Transactional
    public void update(Comment comment) {
        Query query = em.createQuery(
                "update Comment c set c.text = :text where c.id = :id"
        );
        query.setParameter("id", comment.getId());
        query.setParameter("text", comment.getText());
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Query query = em.createQuery(
                "delete from Comment c where c.id = :id"
        );
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
