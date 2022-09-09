package ru.otus.spring082022.Beloborodov02.appconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.PrintStream;

@Component
@Configuration
public class AppConfig {

    @Value("${questions.path}")
    private String questionsPath;

    @Value("${questions.numerofquestions}")
    private int numberOfQuestions;

    @Value("#{T(java.lang.System).out}")
    private PrintStream outStream;

    @Value("#{T(java.lang.System).in}")
    private InputStream inStream;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        Resource resource;
        String activeProfile;

        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();

        // get active profile
        activeProfile = System.getProperty("spring.profiles.active");

        // choose different property files for different active profile
        if ("test".equals(activeProfile)) {
            resource = new ClassPathResource("test.properties");
        } else {
            resource = new ClassPathResource("application.properties");
        }
        // load the property file
        propertySourcesPlaceholderConfigurer.setLocation(resource);
        return propertySourcesPlaceholderConfigurer;
    }

    public PrintStream getOutStream() {
        return outStream;
    }

    public InputStream getInStream() {
        return inStream;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public String getQuestionsPath() {
        return questionsPath;
    }
}
