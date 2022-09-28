package ru.otus.spring082022.Beloborodov04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.CommandNotCurrentlyAvailable;
import org.springframework.shell.Shell;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Тест команд shell ")
@SpringBootTest
public class ApplicationShellCommandsTest {


    private static final String GREETING_PATTERN = "%s, you have been authorized for test.";
    private static final String CUSTOM_AUTH = "Вася Васильев";
    private static final String COMMAND_AUTH_SHORT = "a";
    private static final String COMMAND_TEST = "test";
    private static final String COMMAND_AUTH_PATTERN = "%s %s";
    @Autowired
    private Shell shell;

    @DisplayName(" должен возвращать сообщение об аутентификации")
    @Test
    void shouldReturnExpectedGreetingAfterLoginCommandEvaluated() {

        String res = (String) shell.evaluate(() -> String.format(COMMAND_AUTH_PATTERN, COMMAND_AUTH_SHORT, CUSTOM_AUTH));
        assertThat(res).isEqualTo(String.format(GREETING_PATTERN, CUSTOM_AUTH));
    }

    @DisplayName(" должен возвращать CommandNotCurrentlyAvailable если при попытке выполнения команды test пользователь выполнил авторизацию")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void shouldReturnCommandNotCurrentlyAvailableObjectWhenUserDoesNotLoginAfterPublishCommandEvaluated() {
        Object res = shell.evaluate(() -> COMMAND_TEST);
        assertThat(res).isInstanceOf(CommandNotCurrentlyAvailable.class);
    }
}

