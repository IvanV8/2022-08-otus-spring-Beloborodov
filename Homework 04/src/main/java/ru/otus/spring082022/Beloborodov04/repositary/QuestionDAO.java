package ru.otus.spring082022.Beloborodov04.repositary;

import ru.otus.spring082022.Beloborodov04.domain.Question;

import java.util.List;

public interface QuestionDAO {

    List<Question> getAllQuestions(int maxNumberOfQuestions);

}
