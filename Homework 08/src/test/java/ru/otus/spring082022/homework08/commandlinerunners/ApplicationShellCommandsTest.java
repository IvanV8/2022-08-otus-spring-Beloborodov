package ru.otus.spring082022.homework08.commandlinerunners;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Тест команд shell ")
@SpringBootTest
public class ApplicationShellCommandsTest {


    private static final String COMMAND_REPORT_MESSAGE = "Total number of books:%d";

    private static final String COMMAND_REPORT_PATTERN = "r";
    private static final long TOTAL_BOOKS = 3;


    @Autowired
    private Shell shell;

    @DisplayName(" должен возвращать отчет о книгах")
    @Test
    void shouldReturnExpectedMessageAfterDeleteCommandEvaluated() {
        String command = COMMAND_REPORT_PATTERN;
        String res = (String) shell.evaluate(() -> command);
        assertThat(res).isEqualTo(String.format(COMMAND_REPORT_MESSAGE, TOTAL_BOOKS));
    }


}

