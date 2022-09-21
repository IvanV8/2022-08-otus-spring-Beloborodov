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

        String firstName = inoutService.EnterStringWithPatternPrompt("hello.firstname");
        String lastName = inoutService.EnterStringWithPatternPrompt("hello.lastname");
        Student student = new Student(firstName, lastName);
        ResultTest resultTest = new ResultTest(student);

        questions.forEach((q) -> {
            inoutService.PatternMessage("result.question");
            inoutService.TextMessageWithCR(q.getQuestionText());
            resultTest.addAnswer(inoutService.EnterStringWithPatternPrompt("result.youranswer"));
        });
        int rightAnswers = 0;
        inoutService.PatternMessage("result.student");
        inoutService.TextMessage(student.getFullName());
        for (int i = 0; i < questions.size(); i++) {
            inoutService.PatternMessage("result.question");
            inoutService.TextMessageWithCR(questions.get(i).getQuestionText());
            inoutService.PatternMessage("result.rightanswer");
            inoutService.TextMessageWithCR(questions.get(i).getRightAnswerText());
            String answer = resultTest.getAnswer(i);
            if (answer != null) {
                inoutService.PatternMessage("result.youranswer");
                inoutService.TextMessageWithCR(answer);
                if (questions.get(i).isRightAnswer(answer)) {
                    rightAnswers++;
                }
            }

        }
        inoutService.PatternMessage("result.yourscore");
        inoutService.TextMessageWithCR(String.format(" %d/%d", rightAnswers, questions.size()));


    }
}
