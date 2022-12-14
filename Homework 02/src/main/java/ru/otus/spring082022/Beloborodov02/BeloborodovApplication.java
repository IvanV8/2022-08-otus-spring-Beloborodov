package ru.otus.spring082022.Beloborodov02;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring082022.Beloborodov02.service.QuestionService;


@ComponentScan
@PropertySource("classpath:application.properties")
public class BeloborodovApplication {


	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeloborodovApplication.class);
		QuestionService service = context.getBean(QuestionService.class);
		service.doTest();
	}

}
