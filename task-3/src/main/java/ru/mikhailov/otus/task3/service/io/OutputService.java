package ru.mikhailov.otus.task3.service.io;

public interface OutputService {

    void printLine(String line);

    void printLocaleLine(String code);

    void printLocaleLine(String code, Object... args);

}
