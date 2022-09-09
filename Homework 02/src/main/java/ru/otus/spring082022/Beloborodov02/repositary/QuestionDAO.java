package ru.otus.spring082022.Beloborodov02.repositary;

import ru.otus.spring082022.Beloborodov02.domain.Question;

import java.util.List;

public interface QuestionDAO {

        List<Question> getAllQuestions();

        String getAnswer();

}
