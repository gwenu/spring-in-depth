package ag.spring.in.depth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ag.spring.in.depth.PostProxyInvokerContextListener;
import ag.spring.in.depth.SheldonQuote;
import ag.spring.in.depth.SheldonQuoteV2;
import ag.spring.in.depth.beanpostprocessor.DeprecationHandlerBeanFactoryPostProcessor;
import ag.spring.in.depth.beanpostprocessor.InjectRandomIntAnnotationBeanPostProcessor;
import ag.spring.in.depth.beanpostprocessor.ProfilingHandlerBeanPostProcessor;

@Configuration
@ComponentScan(basePackages="ag.spring.in.depth")
public class ApplicationConfiguration {

	@Bean(name = "quote")
	public SheldonQuote sheldonQuote() {
		SheldonQuote sheldoneQuote = new SheldonQuote();
		sheldoneQuote.setDefaultMessage();
		return sheldoneQuote;
	}
	
	@Bean(name = "quoteV2")
	public SheldonQuoteV2 sheldonQuoteV2() {
		SheldonQuoteV2 sheldoneQuoteV2 = new SheldonQuoteV2();
		sheldoneQuoteV2.setDefaultMessage();
		return sheldoneQuoteV2;
	}
	
	@Bean
	public InjectRandomIntAnnotationBeanPostProcessor injectRandomIntAnnotation() {
		InjectRandomIntAnnotationBeanPostProcessor injectRandomIntBPP = new InjectRandomIntAnnotationBeanPostProcessor();
		return injectRandomIntBPP;
	}
	
	@Bean
	public ProfilingHandlerBeanPostProcessor profilingHandler() throws Exception {
		ProfilingHandlerBeanPostProcessor profilingHandlerBPP = new ProfilingHandlerBeanPostProcessor();
		return profilingHandlerBPP;
	}
	
	@Bean
	public PostProxyInvokerContextListener postProxyInvoker() {
		PostProxyInvokerContextListener postProxyInvokerListener = new PostProxyInvokerContextListener();
		return postProxyInvokerListener;
	}
	
	@Bean
	public DeprecationHandlerBeanFactoryPostProcessor deprecationHandler() {
		DeprecationHandlerBeanFactoryPostProcessor deprecationHandler = new DeprecationHandlerBeanFactoryPostProcessor();
		return deprecationHandler;
	}
}
