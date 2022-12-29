package ru.otus.spring082022.homework08;


import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties
@EnableMongock
public class Homework08Application {

    public static void main(String[] args) {
        SpringApplication.run(Homework08Application.class, args);
    }


}
