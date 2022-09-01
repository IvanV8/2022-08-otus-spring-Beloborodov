package ru.otus.spring082022.Beloborodov.repositary;

import ru.otus.spring082022.Beloborodov.domain.Question;

import java.util.ArrayList;

public interface QuestionDAO {
        ArrayList<Question> getAll();
}
