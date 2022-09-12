package ru.otus.spring082022.Beloborodov02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring082022.Beloborodov02.domain.Question;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Класс Question")
public class QuestionTest {
    @DisplayName("корректно создается конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        Question question = new Question("Вопрос", "Правильный ответ");
        assertEquals("Вопрос", question.getQuestionText());
    }

}
