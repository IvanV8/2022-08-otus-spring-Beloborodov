package ru.otus.spring082022.Beloborodov02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring082022.Beloborodov02.appconfig.AppConfig;
import ru.otus.spring082022.Beloborodov02.domain.Question;
import ru.otus.spring082022.Beloborodov02.repositary.QuestionDAO;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDAO questionDAO;
    private final AppConfig appConfig;
    private final InOutService inoutService;

    @Autowired
    public QuestionServiceImpl(QuestionDAO questionDAO, AppConfig appConfig, InOutService inoutService) {
        this.questionDAO = questionDAO;
        this.appConfig = appConfig;
        this.inoutService = inoutService;
    }

    public void doTest() {
        List<Question> questions = (ArrayList) questionDAO.getAllQuestions();

        inoutService.outString("Enter your first name:");
        String firstName = inoutService.inString();
        inoutService.outString("Enter your last name:");
        String lastName = inoutService.inString();

        questions.forEach((q) -> {
            inoutService.outStringn("Question: " + q.getQuestionText());
            inoutService.outString("Your answer:");
            q.setAnswerText(inoutService.inString());
        });
    }
}
