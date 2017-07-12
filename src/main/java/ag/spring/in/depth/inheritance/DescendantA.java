package ag.spring.in.depth.inheritance;

import org.springframework.stereotype.Component;

@Component
public class DescendantA extends AbstractParent {

	@Override
	protected void create() {
		System.out.println("From ag.spring.in.depth.inheritance.DescendantA -> Should be called on Parent PostConstruct");
	}
}
