package ru.otus.spring082022.Beloborodov01.repositary;

import ru.otus.spring082022.Beloborodov01.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionDAO {


        List<Question> getAll() throws IOException;

}
