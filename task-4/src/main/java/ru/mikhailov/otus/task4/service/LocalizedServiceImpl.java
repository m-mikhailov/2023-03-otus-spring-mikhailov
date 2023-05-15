package ru.mikhailov.otus.task4.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.mikhailov.otus.task4.config.properties.LocaleProvider;

@Service
@RequiredArgsConstructor
public class LocalizedServiceImpl implements LocalizedService {

    private final MessageSource messageSource;

    private final LocaleProvider localeProvider;

    @Override
    public String getMessage(String code) {
        return messageSource.getMessage(code, null, localeProvider.getLocale());
    }

    @Override
    public String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, localeProvider.getLocale());
    }

}
