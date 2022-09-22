package ru.otus.spring082022.Beloborodov03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring082022.Beloborodov03.appconfig.AppProps;
import ru.otus.spring082022.Beloborodov03.domain.Question;
import ru.otus.spring082022.Beloborodov03.domain.ResultTest;
import ru.otus.spring082022.Beloborodov03.domain.Student;
import ru.otus.spring082022.Beloborodov03.repositary.QuestionDAO;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDAO questionDAO;
    private final InOutService inoutService;
    private final AppProps appProps;

    @Autowired
    public QuestionServiceImpl(QuestionDAO questionDAO, InOutService inoutService, AppProps appProps) {
        this.questionDAO = questionDAO;
        this.inoutService = inoutService;
        this.appProps = appProps;
    }

    public void doTest() {
        List<Question> questions = questionDAO.getAllQuestions(appProps.getMaxnumberofquestions());

        String firstName = inoutService.enterStringWithPatternPrompt("hello.firstname");
        String lastName = inoutService.enterStringWithPatternPrompt("hello.lastname");
        Student student = new Student(firstName, lastName);
        ResultTest resultTest = new ResultTest(student);

        questions.forEach((q) -> {
            inoutService.patternMessage("result.question");
            inoutService.textMessageWithCR(q.getQuestionText());
            resultTest.addAnswer(inoutService.enterStringWithPatternPrompt("result.youranswer"));
        });
        int rightAnswers = 0;
        inoutService.patternMessage("result.student");
        inoutService.textMessage(student.getFullName());
        for (int i = 0; i < questions.size(); i++) {
            inoutService.patternMessage("result.question");
            inoutService.textMessageWithCR(questions.get(i).getQuestionText());
            inoutService.patternMessage("result.rightanswer");
            inoutService.textMessageWithCR(questions.get(i).getRightAnswerText());
            String answer = resultTest.getAnswer(i);
            if (answer != null) {
                inoutService.patternMessage("result.youranswer");
                inoutService.textMessageWithCR(answer);
                if (questions.get(i).isRightAnswer(answer)) {
                    rightAnswers++;
                }
            }

        }
        inoutService.patternMessage("result.yourscore");
        inoutService.textMessageWithCR(String.format(" %d/%d", rightAnswers, questions.size()));


    }
}
