package ru.otus.spring082022.Beloborodov01;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring082022.Beloborodov01.service.QuestionService;

import java.io.IOException;

public class BeloborodovApplication {

	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("spring-context.xml");
		QuestionService service = context.getBean(QuestionService.class);
		service.listAllQuestions();

	}

}
