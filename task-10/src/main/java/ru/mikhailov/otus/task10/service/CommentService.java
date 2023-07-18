package ru.mikhailov.otus.task10.service;

import ru.mikhailov.otus.task10.domain.dto.CommentCreateDto;
import ru.mikhailov.otus.task10.domain.dto.CommentDto;

import java.util.List;

public interface CommentService {

    void deleteById(Long id);

    CommentDto add(CommentCreateDto comment);

    List<CommentDto> findAllByBookId(Long bookId);

}
