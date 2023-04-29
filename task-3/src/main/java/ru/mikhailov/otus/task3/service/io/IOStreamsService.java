package ru.mikhailov.otus.task3.service.io;


import org.springframework.context.MessageSource;
import ru.mikhailov.otus.task3.config.properties.LocaleProvider;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IOStreamsService implements IOService {

    private final PrintStream os;
    private final Scanner scanner;

    private final MessageSource messageSource;

    private final LocaleProvider localeProvider;

    public IOStreamsService(
            PrintStream os,
            InputStream io,
            MessageSource messageSource,
            LocaleProvider localeProvider
    ) {
        this.os = os;
        scanner = new Scanner(io);
        this.messageSource = messageSource;
        this.localeProvider = localeProvider;
    }

    @Override
    public String readLineWithPrompt(String prompt) {
        printLine(prompt);
        return scanner.nextLine();
    }

    @Override
    public String readLineWithLocalePrompt(String code) {
        return readLineWithPrompt(messageSource.getMessage(code, null, localeProvider.getLocale()));
    }

    @Override
    public void printLine(String line) {
        os.println(line);
    }

    @Override
    public void printLocaleLine(String code) {
        printLocaleLine(code, "");
    }

    @Override
    public void printLocaleLine(String code, Object... args) {
        printLine(messageSource.getMessage(code, args, localeProvider.getLocale()));
    }
}
