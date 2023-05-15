package ru.mikhailov.otus.task3.service;

public interface LocalizedService {

    String getMessage(String code);

    String getMessage(String code, Object... args);

}
