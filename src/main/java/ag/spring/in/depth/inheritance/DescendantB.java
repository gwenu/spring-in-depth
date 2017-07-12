package ag.spring.in.depth.inheritance;

import org.springframework.stereotype.Component;

@Component
public class DescendantB extends AbstractParent {

	@Override
	protected void create() {
		System.out.println("From ag.spring.in.depth.inheritance.DescendantB -> Should be called on Parent PostConstruct");
	}

}
