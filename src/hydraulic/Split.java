package hydraulic;

/**
 * Represents a split element, a.k.a. T element
 * 
 * During the simulation each downstream element will receive a stream that is
 * half the input stream of the split.
 */

public class Split extends Element {

	private Double flowin;
	private Double[] flowout;
	private Element[] connectedTo;

	/**
	 * Constructor
	 * 
	 * @param name
	 */
	public Split(String name) {
		super(name);
		flowout = new Double[2];
		connectedTo = new Element[2];
	}

	/**
	 * returns the downstream elements
	 * 
	 * @return array containing the two downstream element
	 */
	public Element[] getOutputs() {
		return this.connectedTo;
	}

	public void connect(Element elem, int noutput) {
		if (noutput < 0 || noutput >= 2) {
			throw new IndexOutOfBoundsException();
		}
		this.connectedTo[noutput] = elem;
	}

	public Double getFlowin() {
		return flowin;
	}

	public void setFlowin(Double flowin) {
		this.flowin = flowin;
		this.flowout[0] = flowin / 2;
		this.flowout[1] = flowin / 2;
	}

	public Double[] getFlowout() {
		return flowout;
	}

}
