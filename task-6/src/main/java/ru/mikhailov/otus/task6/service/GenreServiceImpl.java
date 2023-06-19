package ru.mikhailov.otus.task6.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailov.otus.task6.domain.dto.GenreDto;
import ru.mikhailov.otus.task6.domain.error.EntityNotFoundException;
import ru.mikhailov.otus.task6.domain.model.Genre;
import ru.mikhailov.otus.task6.repository.GenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;

    @Override
    @Transactional
    public Genre save(GenreDto genre) {
        var newGenre = new Genre(null, genre.name());
        return repository.save(newGenre);
    }

    @Override
    @Transactional(readOnly = true)
    public Genre findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Genre with id %s not found".formatted(id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> findAll() {
        return repository.findAll();
    }
}
