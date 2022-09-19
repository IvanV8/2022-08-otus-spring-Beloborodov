package ru.otus.spring082022.Beloborodov03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.spring082022.Beloborodov03.appconfig.AppProps;


@SpringBootApplication
@EnableConfigurationProperties(AppProps.class)
public class Beloborodov03Application {

    public static void main(String[] args) {
        SpringApplication.run(Beloborodov03Application.class, args);
    }

}
