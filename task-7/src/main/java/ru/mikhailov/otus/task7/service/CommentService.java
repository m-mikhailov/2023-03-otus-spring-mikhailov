package ru.mikhailov.otus.task7.service;

import ru.mikhailov.otus.task7.domain.dto.CommentCreateDto;
import ru.mikhailov.otus.task7.domain.dto.CommentDto;
import ru.mikhailov.otus.task7.domain.dto.CommentUpdateDto;
import ru.mikhailov.otus.task7.domain.model.Comment;

import java.util.List;

public interface CommentService {

    void update(CommentUpdateDto commentDto);

    void deleteById(Long id);

    CommentDto add(CommentCreateDto comment);

    List<CommentDto> findAllByBookId(Long bookId);

}
