package ru.otus.spring082022.Beloborodov.service;

import ru.otus.spring082022.Beloborodov.domain.Question;
import ru.otus.spring082022.Beloborodov.repositary.QuestionDAOImpl;

import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionDAOImpl questionDAO;

    public QuestionServiceImpl(QuestionDAOImpl questionDAO) {
        this.questionDAO = questionDAO;
    }

    public void listAllQuestions() {
        ArrayList<Question> questions = (ArrayList) questionDAO.getAll();
        SystemOutService console = new SystemOutServiceImpl(System.out);

        questions.forEach((q) -> {
            console.Out("Question: " + q.getQuestionText() + ", answer: " + q.getAnswerText());
        });
    }
}
