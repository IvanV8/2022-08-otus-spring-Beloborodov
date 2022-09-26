package ru.otus.spring082022.Beloborodov03.appconfig;


// интерфейс для получения пути к файлам с вопросами

public interface QuestionsPathProvider {
    String getQuestionsPath();

    void setQuestionsPath(String csvpath);
}
