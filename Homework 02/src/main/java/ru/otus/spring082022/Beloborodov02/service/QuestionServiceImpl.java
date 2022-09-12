package ru.otus.spring082022.Beloborodov02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring082022.Beloborodov02.appconfig.AppConfig;
import ru.otus.spring082022.Beloborodov02.domain.Question;
import ru.otus.spring082022.Beloborodov02.domain.Student;
import ru.otus.spring082022.Beloborodov02.repositary.QuestionDAO;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDAO questionDAO;
    private final InOutService inoutService;
    private final AppConfig appConfig;

    @Autowired
    public QuestionServiceImpl(QuestionDAO questionDAO, InOutService inoutService, AppConfig appConfig) {
        this.questionDAO = questionDAO;
        this.inoutService = inoutService;
        this.appConfig = appConfig;
    }

    public void doTest() {
        List<Question> questions = questionDAO.getAllQuestions(appConfig.getNumberOfQuestions());

        String firstName = inoutService.inStringWithPrompt("Enter your first name:");
        String lastName = inoutService.inStringWithPrompt("Enter your last name:");
        Student student = new Student(firstName, lastName);

        questions.forEach((q) -> {
            inoutService.outStringn("Question: " + q.getQuestionText());
            q.setAnswerText(inoutService.inStringWithPrompt("Your answer:"));
        });
        int i = 0;
        inoutService.outStringn("Student:" + student.getFullName());
        for (Question q : questions) {
            inoutService.outStringn("Question: " + q.getQuestionText());
            inoutService.outStringn("Right answer: " + q.getRightAnswerText());
            inoutService.outStringn("Your answer: " + q.getAnswerText());
            if (q.isRightAnswer()) i++;
        }
        inoutService.outStringn(String.format("Your score: %d/%d", i, questions.size()));

    }
}
