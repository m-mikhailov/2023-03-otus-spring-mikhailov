package ru.mikhailov.otus.task8.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mikhailov.otus.task8.domain.dto.GenreCreateDto;
import ru.mikhailov.otus.task8.domain.dto.GenreDto;
import ru.mikhailov.otus.task8.domain.error.EntityNotFoundException;
import ru.mikhailov.otus.task8.domain.model.Genre;
import ru.mikhailov.otus.task8.repository.GenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;

    @Override
    public GenreDto save(GenreCreateDto genre) {
        var newGenre = new Genre(genre.name());
        var savedGenre = repository.save(newGenre);
        return new GenreDto(savedGenre);
    }

    @Override
    public GenreDto findById(String id) {
        return repository.findById(id)
                .map(GenreDto::new)
                .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.GENRE_MESSAGE_FORMAT, id));
    }

    @Override
    public List<GenreDto> findAll() {
        return repository.findAll()
                .stream()
                .map(GenreDto::new)
                .toList();
    }
}
