package ru.mikhailov.otus.task8.service;


import ru.mikhailov.otus.task8.domain.dto.CommentCreateDto;
import ru.mikhailov.otus.task8.domain.dto.CommentDto;
import ru.mikhailov.otus.task8.domain.dto.CommentUpdateDto;

import java.util.List;

public interface CommentService {

    void update(CommentUpdateDto commentDto);

    void deleteById(String id);

    CommentDto add(CommentCreateDto comment);

    List<CommentDto> findAllByBookId(String bookId);

}
