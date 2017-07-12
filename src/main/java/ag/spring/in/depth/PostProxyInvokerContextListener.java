package ag.spring.in.depth;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.type.StandardMethodMetadata;

import ag.spring.in.depth.annotation.PostProxy;

public class PostProxyInvokerContextListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ConfigurableListableBeanFactory beanFactory;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext applicationContext = event.getApplicationContext();
		String[] beanNames = applicationContext.getBeanDefinitionNames();

		for (String beanName : beanNames) {
			BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
			String originalClassName = getBeanClassName(beanDefinition);
			try {
				if (originalClassName != null) {
					Class<?> originalClass = Class.forName(originalClassName);
					invokeMethodsWithPostProxyAnnotation(originalClass.getMethods(),
							applicationContext.getBean(beanName));
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

	}

	private String getBeanClassName(BeanDefinition beanDefinition) {
		return beanDefinition.getBeanClassName() != null ? beanDefinition.getBeanClassName()
				: getBeanClassNameFromSource(beanDefinition);

	}

	private String getBeanClassNameFromSource(BeanDefinition beanDefinition) {
		String className = null;

		if (beanDefinition.getSource() instanceof StandardMethodMetadata) {
			className = ((StandardMethodMetadata) beanDefinition.getSource()).getReturnTypeName();
		}

		return className;
	}

	private void invokeMethodsWithPostProxyAnnotation(Method[] methods, Object bean) {
		for (Method method : methods) {
			if (method.isAnnotationPresent(PostProxy.class)) {
				try {
					Method currentMethod = bean.getClass().getMethod(method.getName(), method.getParameterTypes());
					currentMethod.invoke(bean);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
