package ag.spring.in.depth.beanpostprocessor;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import ag.spring.in.depth.annotation.DeprecatedClass;

public class DeprecationHandlerBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		Map<String,Object> beans = beanFactory.getBeansWithAnnotation(DeprecatedClass.class);
		
		beans.forEach((key,value)->{ 
			BeanDefinition beanDefinition = beanFactory.getBeanDefinition(key);
			
			DeprecatedClass annotation = value.getClass().getAnnotation(DeprecatedClass.class);
			
			if(annotation != null) {
				beanDefinition.setBeanClassName(annotation.newImplementation().getName());
				System.out.println("It doesn't work but idea is clear: should swap classes.");
			}
		});
	}
}
