package ru.otus.spring082022.Beloborodov02;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring082022.Beloborodov02.service.QuestionService;

import java.io.IOException;

@ComponentScan
public class BeloborodovApplication {


	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeloborodovApplication.class)) {
			QuestionService service = context.getBean(QuestionService.class);

			service.doTest();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

}
