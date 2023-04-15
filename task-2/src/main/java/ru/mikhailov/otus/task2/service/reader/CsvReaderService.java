package ru.mikhailov.otus.task2.service.reader;

import ru.mikhailov.otus.task2.domain.Question;

import java.util.List;

public interface CsvReaderService<T> {
    List<T> readFile();

}
