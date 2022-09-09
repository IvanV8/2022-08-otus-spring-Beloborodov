package ru.otus.spring082022.Beloborodov01.service;

import ru.otus.spring082022.Beloborodov01.domain.Question;
import ru.otus.spring082022.Beloborodov01.repositary.QuestionDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionDAO questionDAO;
    private final OutputService outputService;

    public QuestionServiceImpl(QuestionDAO questionDAO, OutputService outputService) {
        this.questionDAO = questionDAO;
        this.outputService = outputService;
    }


    public void listAllQuestions() throws IOException {
        List<Question> questions = (ArrayList) questionDAO.getAll();

        questions.forEach((q) -> {
            outputService.out("Question: " + q.getQuestionText() + ", answer: " + q.getAnswerText());
        });
    }
}
