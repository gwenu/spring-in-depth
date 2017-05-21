package ag.spring.in.depth;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ag.spring.in.depth.config.ApplicationConfiguration;

@SpringBootApplication
public class SpringInDepth {

	public static void main(String[] args) throws InterruptedException {
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
		Quote sheldonQuote = context.getBean(Quote.class);

		while (true) {
			Thread.sleep(1000);
			sheldonQuote.sayQuote();
		}
	}
}
