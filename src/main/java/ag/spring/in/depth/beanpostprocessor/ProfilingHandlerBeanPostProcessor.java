package ag.spring.in.depth.beanpostprocessor;

import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import ag.spring.in.depth.ProfilingController;
import ag.spring.in.depth.annotation.Profiling;

public class ProfilingHandlerBeanPostProcessor implements BeanPostProcessor {
	
	private Map<String, Class<?>> map = new HashMap<>();
	private ProfilingController profilingController = new ProfilingController();
	
	public ProfilingHandlerBeanPostProcessor() throws Exception {
		MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
		platformMBeanServer.registerMBean(profilingController, new ObjectName("profiling", "name", "controller"));
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		Class<?> beanClass = bean.getClass();
		
		if(beanClass.isAnnotationPresent(Profiling.class)) {
			map.put(beanName, beanClass);
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		Class<?> beanClass = map.get(beanName);
		
		if(beanClass != null) {
			return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
				
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					if(profilingController.isEnabled()) {
						System.out.println("Profiling...");
						long before = System.nanoTime();
						Object value = method.invoke(bean, args);
						long after = System.nanoTime();
						System.out.println("Profiling finished. Result: " + (after - before));
						return value;
					} else {
						return method.invoke(bean, args);
					}
					
				}
			});
		}
		
		return bean;
	}

}
