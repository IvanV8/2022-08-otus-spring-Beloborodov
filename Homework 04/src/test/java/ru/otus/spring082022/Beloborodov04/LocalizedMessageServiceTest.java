package ru.otus.spring082022.Beloborodov04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring082022.Beloborodov04.appconfig.LocaleDataProvider;
import ru.otus.spring082022.Beloborodov04.service.LocalizedMessagesService;

import java.util.Locale;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Тест для проверки работы шаблонов сообщений")
@SpringBootTest
public class LocalizedMessageServiceTest {

    @Autowired
    private LocaleDataProvider localeDataProvider;

    @Autowired
    private LocalizedMessagesService localizedMessageService;

    private static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of("ru", "Ваш результат: "),
                Arguments.of("RU", "Ваш результат: "),
                Arguments.of("en", "Your score: "),
                Arguments.of("EN", "Your score: ")

        );

    }

    @ParameterizedTest
    @MethodSource("generateData")
    @DisplayName("дожен возвращать не пустое, корректное сообщение для кода указанной страны в любом регистре")
    void shouldReturnNotEmptyMessageByCaseInsensitiveCountryCode(String countryCode, String expectedMessage) {
        localeDataProvider.setLocale(new Locale(countryCode));

        String localMessage = localizedMessageService.getLocalizedMessage("result.yourscore");
        assertNotNull(localMessage);
        assertEquals(localMessage, expectedMessage);
    }
}