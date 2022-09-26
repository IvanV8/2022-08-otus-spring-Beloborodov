package ru.otus.spring082022.Beloborodov03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.spring082022.Beloborodov03.appconfig.LocaleDataProvider;


// Класс сервис для получения локализованных сообщений i18n

@Component
public class LocalizedMessagesServiceImpl implements LocalizedMessagesService {


    private final MessageSource messageSource;
    private final LocaleDataProvider localeDataProvider;

    @Autowired
    public LocalizedMessagesServiceImpl(MessageSource messageSource, LocaleDataProvider localeDataProvider) {
        this.messageSource = messageSource;
        this.localeDataProvider = localeDataProvider;
    }

    public String getLocalizedMessage(String code) {
        return messageSource.getMessage(code, null, localeDataProvider.getLocale());

    }
}
