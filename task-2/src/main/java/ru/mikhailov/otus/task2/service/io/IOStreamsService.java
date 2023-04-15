package ru.mikhailov.otus.task2.service.io;


import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IOStreamsService implements IOService {

    private final PrintStream os;
    private final Scanner scanner;

    public IOStreamsService(PrintStream os, InputStream io) {
        this.os = os;
        scanner = new Scanner(io);
    }

    @Override
    public String readLineWithPrompt(String prompt) {
        printLine(prompt);
        return scanner.nextLine();
    }

    @Override
    public void printLine(String line) {
        os.println(line);
    }

}
