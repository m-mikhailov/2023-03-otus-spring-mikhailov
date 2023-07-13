package ru.mikhailov.otus.task9.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mikhailov.otus.task9.domain.model.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

}
