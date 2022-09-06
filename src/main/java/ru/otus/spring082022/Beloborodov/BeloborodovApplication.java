package ru.otus.spring082022.Beloborodov;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring082022.Beloborodov.service.QuestionService;
import ru.otus.spring082022.Beloborodov.service.QuestionServiceImpl;

import java.io.IOException;

public class BeloborodovApplication {

	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("spring-context.xml");
		QuestionService service = context.getBean(QuestionService.class);
		service.listAllQuestions();

	}

}
