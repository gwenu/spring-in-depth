package ag.spring.in.depth.beanpostprocessor;

import java.lang.reflect.Field;
import java.util.Random;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import ag.spring.in.depth.annotation.InjectRandomInt;

@Component
public class InjectRandomIntAnnotationBeanPostProcessor implements BeanPostProcessor  {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		Field[] fields = bean.getClass().getDeclaredFields();
		
		for (Field field : fields) {
			InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);
			
			if(annotation != null) {
				int min = annotation.min();
				int max = annotation.max();
				int randomInt = getRandomInt(min, max);
				System.out.println("BPP: " + randomInt);
				
				field.setAccessible(true);
				ReflectionUtils.setField(field, bean, randomInt);
			}
		}
		
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
	
	private int getRandomInt(int min, int max) {
		Random random = new Random();
		return random.nextInt(max + 1 - min) + min;
	}
}
