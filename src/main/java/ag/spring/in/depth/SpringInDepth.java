package ag.spring.in.depth;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ag.spring.in.depth.config.ApplicationConfiguration;

@SpringBootApplication
public class SpringInDepth {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws InterruptedException {
		new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
	}
}
