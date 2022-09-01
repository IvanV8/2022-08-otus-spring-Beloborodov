package ru.otus.spring082022.Beloborodov;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring082022.Beloborodov.service.QuestionServiceImpl;

public class BeloborodovApplication {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("spring-context.xml");
		QuestionServiceImpl service = context.getBean(QuestionServiceImpl.class);
		service.ListAllQuestions();
	}

}
