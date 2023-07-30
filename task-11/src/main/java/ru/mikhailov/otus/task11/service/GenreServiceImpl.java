package ru.mikhailov.otus.task11.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.mikhailov.otus.task11.domain.dto.GenreCreateDto;
import ru.mikhailov.otus.task11.domain.dto.GenreDto;
import ru.mikhailov.otus.task11.domain.error.EntityNotFoundException;
import ru.mikhailov.otus.task11.domain.model.Genre;
import ru.mikhailov.otus.task11.repository.GenreRepository;

import static ru.mikhailov.otus.task11.domain.error.EntityNotFoundException.GENRE_MESSAGE_FORMAT;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;

    @Override
    public Mono<GenreDto> save(GenreCreateDto genre) {
        var newGenre = new Genre(genre.name());
        return repository.save(newGenre)
                .map(GenreDto::new);
    }

    @Override
    public Mono<GenreDto> findById(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new EntityNotFoundException(GENRE_MESSAGE_FORMAT, id)))
                .map(GenreDto::new);
    }

    @Override
    public Flux<GenreDto> findAll() {
        return repository.findAll()
                .map(GenreDto::new);
    }
}
