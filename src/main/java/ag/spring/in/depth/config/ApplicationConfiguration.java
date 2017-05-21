package ag.spring.in.depth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ag.spring.in.depth.SheldonQuote;
import ag.spring.in.depth.beanpostprocessor.InjectRandomIntAnnotationBeanPostProcessor;
import ag.spring.in.depth.beanpostprocessor.ProfilingHandlerBeanPostProcessor;

@Configuration
@ComponentScan(basePackages="ag.spring.in.depth")
public class ApplicationConfiguration {

	private static final String SHELDON_QUOTE = "For the record, it could kill us to meet new people. They could be murderers or the carriers of unusual pathogens. And I'm not insane, my mother had me tested.";

	@Bean(name = "quote")
	public SheldonQuote sheldonQuote() {
		SheldonQuote sheldoneQuote = new SheldonQuote();
		sheldoneQuote.setMessage(SHELDON_QUOTE);
		return sheldoneQuote;
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
}
