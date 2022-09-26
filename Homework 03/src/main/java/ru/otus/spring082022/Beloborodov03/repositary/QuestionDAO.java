package ru.otus.spring082022.Beloborodov03.repositary;

import ru.otus.spring082022.Beloborodov03.domain.Question;

import java.util.List;

public interface QuestionDAO {

    List<Question> getAllQuestions(int maxNumberOfQuestions);

}
