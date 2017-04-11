package hydraulic;

/**
 * Represents a source of water, i.e. the initial element for the simulation.
 *
 * Lo status of the source is defined through the method
 * {@link #setFlow(double) setFlow()}.
 */
public class Source extends Element {

	private Double flowout;
	private Element connectedTo = null;

	public Source(String name) {
		super(name);
		flowout = 0.0;
	}

	public void setFlow(double flow) {
		if (flow >= 0.0)
			this.flowout = flow;
		else
			this.flowout = 0.0;
	}

	public Double getFlowout() {
		return flowout;
	}

	/**
	 * Function description
	 * @return the Element to which the output is connected to 
	 */
	public Element getConnectedTo() {
		return connectedTo;
	}

	public void setConnectedTo(Element connectedTo) {
		this.connectedTo = connectedTo;
	}
}
