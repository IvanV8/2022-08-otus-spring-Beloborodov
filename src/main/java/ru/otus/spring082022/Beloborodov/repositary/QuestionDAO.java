package ru.otus.spring082022.Beloborodov.repositary;

import ru.otus.spring082022.Beloborodov.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionDAO {


        List<Question> getAll() throws IOException;

}