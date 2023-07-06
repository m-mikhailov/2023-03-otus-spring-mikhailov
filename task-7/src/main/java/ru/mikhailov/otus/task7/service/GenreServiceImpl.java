package ru.mikhailov.otus.task7.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailov.otus.task7.domain.dto.GenreCreateDto;
import ru.mikhailov.otus.task7.domain.dto.GenreDto;
import ru.mikhailov.otus.task7.domain.error.EntityNotFoundException;
import ru.mikhailov.otus.task7.domain.model.Genre;
import ru.mikhailov.otus.task7.repository.GenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;

    @Override
    @Transactional
    public GenreDto save(GenreCreateDto genre) {
        var newGenre = new Genre(genre.name());
        var savedGenre = repository.save(newGenre);
        return new GenreDto(savedGenre);
    }

    @Override
    @Transactional(readOnly = true)
    public GenreDto findById(Long id) {
        return repository.findById(id)
                .map(GenreDto::new)
                .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.GENRE_MESSAGE_FORMAT, id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<GenreDto> findAll() {
        return repository.findAll()
                .stream()
                .map(GenreDto::new)
                .toList();
    }
}
