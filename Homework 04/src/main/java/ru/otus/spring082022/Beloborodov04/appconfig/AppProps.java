package ru.otus.spring082022.Beloborodov04.appconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

// Класс для получения properties из yml

@ConfigurationProperties(prefix = "application")
public class AppProps implements LocaleDataProvider, QuestionsPathProvider {

    private Locale locale;
    private int maxnumberofquestions;
    private String questionspath;

    @Override
    public Locale getLocale() {
        return locale;
    }

    @Override
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
    @Override
    public String getQuestionsPath() {

        String s = locale.toString();
        return new StringBuffer(questionspath).insert(questionspath.length() - 4, "_" + s).toString();
    }

    @Override
    public void setQuestionsPath(String questionspath) {
        this.questionspath = questionspath;
    }


}
