package ru.mikhailov.otus.task2.service.dao;

import java.util.List;

public interface CsvDaoService<T> {
    List<T> getAll();

}
