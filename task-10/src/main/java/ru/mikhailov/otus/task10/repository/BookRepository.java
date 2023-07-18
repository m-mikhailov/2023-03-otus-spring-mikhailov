package ru.mikhailov.otus.task10.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mikhailov.otus.task10.domain.model.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(attributePaths = {"author", "genre"})
    Optional<Book> findById(Long id);

    @EntityGraph(attributePaths = {"author", "genre"})
    List<Book> findAll();

}
