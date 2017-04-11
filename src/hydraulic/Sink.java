package hydraulic;

/**
 * Represents the sink, i.e. the terminal element of a system
 *
 */
public class Sink extends Element {

	private Float flowin;

	/**
	 * Constructor
	 * 
	 * @param name
	 */
	public Sink(String name) {
		super(name);
		this.flowin = 0F;
	}

	public Float getFlowin() {
		return flowin;
	}

	public void setFlowin(Float flowout) {
		if (flowout >= 0F)
			this.flowin = flowout;
		else
			this.flowin = 0F;
	}

	@Override
	public Object getFlowout() {
		return null;
	}
}
