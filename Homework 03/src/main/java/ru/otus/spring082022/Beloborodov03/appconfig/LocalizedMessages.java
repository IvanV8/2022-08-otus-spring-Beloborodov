package ru.otus.spring082022.Beloborodov03.appconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;


// Класс сервис для получения локализованных сообщений i18n

@Component
public class LocalizedMessages {


    private final MessageSource messageSource;
    private final AppProps appprops;

    @Autowired
    public LocalizedMessages(MessageSource messageSource, AppProps appprops) {
        this.messageSource = messageSource;
        this.appprops = appprops;
    }

    public String getLocalizedMessage(String code) {
        return messageSource.getMessage(code, null, appprops.getLocale());

    }
}
