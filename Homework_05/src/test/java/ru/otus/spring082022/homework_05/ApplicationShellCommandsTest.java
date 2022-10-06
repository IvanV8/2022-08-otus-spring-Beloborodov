package ru.otus.spring082022.homework_05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Тест команд shell ")
@SpringBootTest
public class ApplicationShellCommandsTest {


    private static final String COMMAND_DELETE_MESSAGE = "Book deleted with id:%d";

    private static final String COMMAND_DELETE_PATTERN = "%s %d";
    private static final long CUSTOM_ID = 1;
    private static final String COMMAND_DELETE_SHORT = "d";

    @Autowired
    private Shell shell;

    @DisplayName(" должен возвращать сообщение об удалении")
    @Test
    void shouldReturnExpectedMessageAfterLoginCommandEvaluated() {
        String command = String.format(COMMAND_DELETE_PATTERN, COMMAND_DELETE_SHORT, CUSTOM_ID);
        String res = (String) shell.evaluate(() -> command);
        assertThat(res).isEqualTo(String.format(COMMAND_DELETE_MESSAGE, CUSTOM_ID));
    }


}

