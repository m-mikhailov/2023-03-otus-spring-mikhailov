package ru.mikhailov.otus.task11.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record BookCreateDto(
        @NotEmpty String name,
        @NotNull String authorId,
        @NotNull String genreId
) {
}
