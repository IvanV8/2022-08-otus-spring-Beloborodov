package ru.otus.spring082022.Beloborodov03.appconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

// Класс для получения properties из yml

@ConfigurationProperties(prefix = "application")
public class AppProps {

    private Locale locale;
    private int maxnumberofquestions;
    private String csvpath;

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public int getMaxnumberofquestions() {
        return maxnumberofquestions;
    }

    public void setMaxnumberofquestions(int maxnumberofquestions) {
        this.maxnumberofquestions = maxnumberofquestions;
    }

    // при получении csv файла с вопросами используется locale. Название языка добавляется к имени файла
    public String getCsvpath() {

        String s = locale.toString();
        return new StringBuffer(csvpath).insert(csvpath.length() - 4, "_" + s).toString();
    }

    public void setCsvpath(String csvpath) {
        this.csvpath = csvpath;
    }


}
