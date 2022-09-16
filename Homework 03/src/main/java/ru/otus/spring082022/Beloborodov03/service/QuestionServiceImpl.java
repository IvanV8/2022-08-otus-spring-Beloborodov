package ru.otus.spring082022.Beloborodov03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring082022.Beloborodov03.appconfig.AppProps;
import ru.otus.spring082022.Beloborodov03.domain.Question;
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

        String firstName = inoutService.inStringWithPrompt(true, "hello.firstname");
        String lastName = inoutService.inStringWithPrompt(true, "hello.lastname");
        Student student = new Student(firstName, lastName);

        questions.forEach((q) -> {
            inoutService.outString(true, "result.question");
            inoutService.outStringn(false, q.getQuestionText());
            student.addAnswer(inoutService.inStringWithPrompt(true, "result.youranswer"));
        });
        int rightAnswers = 0;
        inoutService.outString(true, "result.student");
        inoutService.outStringn(false, student.getFullName());
        for (int i = 0; i < questions.size(); i++) {
            inoutService.outString(true, "result.question");
            inoutService.outStringn(false, questions.get(i).getQuestionText());
            inoutService.outString(true, "result.rightanswer");
            inoutService.outString(false, questions.get(i).getRightAnswerText());
            String answer = student.getAnswer(i);
            if (answer != null) {
                inoutService.outString(true, "result.youranswer");
                inoutService.outStringn(false, answer);
                if (questions.get(i).isRightAnswer(answer)) {
                    rightAnswers++;
                }
            }

        }
        inoutService.outString(true, "result.yourscore");
        inoutService.outStringn(false, String.format(" %d/%d", rightAnswers, questions.size()));


    }
}
