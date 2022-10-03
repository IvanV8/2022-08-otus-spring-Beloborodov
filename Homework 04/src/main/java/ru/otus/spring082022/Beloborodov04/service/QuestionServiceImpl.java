package ru.otus.spring082022.Beloborodov04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring082022.Beloborodov04.appconfig.AppProps;
import ru.otus.spring082022.Beloborodov04.domain.Question;
import ru.otus.spring082022.Beloborodov04.domain.ResultTest;
import ru.otus.spring082022.Beloborodov04.domain.Student;
import ru.otus.spring082022.Beloborodov04.repositary.QuestionDAO;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDAO questionDAO;
    private final LocalizedIOService inoutService;
    private final AppProps appProps;

    @Autowired
    public QuestionServiceImpl(QuestionDAO questionDAO, LocalizedIOService inoutService, AppProps appProps) {
        this.questionDAO = questionDAO;
        this.inoutService = inoutService;
        this.appProps = appProps;
    }

    public String doTest(Student student) {
        List<Question> questions = questionDAO.getAllQuestions(appProps.getMaxnumberofquestions());

        ResultTest resultTest = new ResultTest(student);

        questions.forEach((q) -> {
            inoutService.patternMessage("result.question");
            inoutService.textMessageWithCR(q.getQuestionText());
            resultTest.addAnswer(inoutService.enterStringWithPatternPrompt("result.youranswer"));
        });

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
            }

        }
        inoutService.patternMessage("result.yourscore");
        return resultTest.score(questions);
    }
}
