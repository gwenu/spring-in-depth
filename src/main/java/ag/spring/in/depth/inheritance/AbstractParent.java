package ag.spring.in.depth.inheritance;

import javax.annotation.PostConstruct;

public abstract class AbstractParent {
	
	@PostConstruct
	public void init() {
		create();
	}
	
	protected abstract void create();

}
