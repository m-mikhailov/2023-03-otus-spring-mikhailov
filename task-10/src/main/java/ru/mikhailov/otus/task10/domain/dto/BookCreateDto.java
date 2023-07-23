package ru.mikhailov.otus.task10.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record BookCreateDto(
        @NotEmpty String name,
        @NotNull Long authorId,
        @NotNull Long genreId
) {
}
