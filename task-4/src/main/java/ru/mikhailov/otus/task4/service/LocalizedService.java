package ru.mikhailov.otus.task4.service;

public interface LocalizedService {

    String getMessage(String code);

    String getMessage(String code, Object... args);

}
