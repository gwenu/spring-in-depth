package ag.spring.in.depth;

// MBean server starts with java process and allow to change config through jmx console
public class ProfilingController implements ProfilingControllerMBean {
	
	private boolean enabled = true;

	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
