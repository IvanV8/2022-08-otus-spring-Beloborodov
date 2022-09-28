package ru.otus.spring082022.Beloborodov04.appconfig;

import java.util.Locale;

public interface LocaleDataProvider {
    Locale getLocale();

    void setLocale(Locale locale);
}
