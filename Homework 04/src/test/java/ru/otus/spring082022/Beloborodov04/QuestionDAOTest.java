package ru.otus.spring082022.Beloborodov04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring082022.Beloborodov04.appconfig.AppProps;
import ru.otus.spring082022.Beloborodov04.domain.Question;
import ru.otus.spring082022.Beloborodov04.repositary.QuestionDAO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
public class QuestionDAOTest {

    @Autowired
    QuestionDAO questionDAO;

    @Autowired
    AppProps appProps;

    @DisplayName(" должен проверять загрузку файла с вопросами")
    @Test
    void shouldCheckQuestionsFileLoaded() {

        List<Question> questions = questionDAO.getAllQuestions(appProps.getMaxnumberofquestions());
        assertNotNull(questions);
        assertTrue("questions.size() should be greater than zero", questions.size() > 0);

    }


}
