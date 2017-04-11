package hydraulic;

/**
 * Represents a tap that can interrupt the flow.
 * 
 * The status of the tap is defined by the method {@link #setOpen(boolean)
 * setOpen()}.
 */

public class Tap extends Element {

	private Boolean open = false;
	private Double flowin = 0.0;
	private Double flowout = 0.0;

	public Tap(String name) {
		super(name);
		this.open = false;
	}

	public void setOpen(boolean open) {
		this.open = open;
		flowout = flowin * (open ? 1.0 : 0.0);
	}

	public Double getFlowin() {
		return flowin;
	}

	public void setFlowin(Double flowin) {
		if (flowin >= 0.0) {
			this.flowin = flowin;
			flowout = flowin * (open ? 1.0 : 0.0);
		} else
			this.flowin = 0.0;
	}

	public Double getFlowout() {
		return flowout;
	}

}
