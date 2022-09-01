package ru.otus.spring082022.Beloborodov.service;

import ru.otus.spring082022.Beloborodov.domain.Question;
import ru.otus.spring082022.Beloborodov.repositary.QuestionDAOImpl;

import java.util.ArrayList;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionDAOImpl questionDAO;

    public QuestionServiceImpl(QuestionDAOImpl questionDAO) {
        this.questionDAO = questionDAO;
    }

    public void ListAllQuestions() {
        ArrayList<Question> questions = questionDAO.getAll();
        questions.forEach((q) -> {
            System.out.println("Question: " + q.getQuestionText() + ", answer: " + q.getAnswerText());
        });
    }
}
