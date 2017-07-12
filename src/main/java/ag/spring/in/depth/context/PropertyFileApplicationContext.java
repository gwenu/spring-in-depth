package ag.spring.in.depth.context;

import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;

import ag.spring.in.depth.Quote;

public class PropertyFileApplicationContext extends GenericApplicationContext {
	
	public PropertyFileApplicationContext(String fileName) {
		PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(this);
		int loadBeanDefinitions = reader.loadBeanDefinitions(fileName);
		
		System.out.println("Found beans: " + loadBeanDefinitions);
		refresh();
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		PropertyFileApplicationContext context = new PropertyFileApplicationContext("/resources/context.properties");
		context.getBean(Quote.class).sayQuote();
	}
}
