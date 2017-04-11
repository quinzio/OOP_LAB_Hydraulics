package hydraulic;

/**
 * Main class that act as a container of the elements for the simulation of an
 * hydraulics system
 * 
 */
public class HSystem {
	private Element[] es;
	private int index = 0;
	final private int MAX_ELEMENTS = 1000;

	public HSystem() {
		es = new Element[MAX_ELEMENTS];
	}

	/**
	 * Adds a new element to the system
	 * 
	 * @param elem
	 */
	public void addElement(Element elem) {
		if (index < MAX_ELEMENTS)
			es[index++] = elem;
	}

	/**
	 * returns the element added so far to the system
	 * 
	 * @return an array of elements whose length is equal to the number of added
	 *         elements
	 */
	public Element[] getElements() {
		return es;
	}

	/**
	 * Prints the layout of the system starting at each Source
	 */
	public String layout() {
		StringBuilder str = new StringBuilder();
		for (Element e : es) {
			if (e instanceof Source)
				layoutat(0, str, e);
		}
		return str.toString();
	}

	private StringBuilder layoutat(int prespace, StringBuilder str, Element elem) {
		StringBuilder newstr = new StringBuilder();
		Integer offset;
		newstr.append("[");
		newstr.append(elem.getName());
		newstr.append("]");
		newstr.append(elem.getClass().getSimpleName());
		newstr.append(" -> ");
		str.append(newstr);
		offset = str.length();
		if (elem instanceof Sink) {
			str.reverse().delete(0, 3).reverse();
			str.append("|\n");
			return str;
		}
		if (!(elem instanceof Split)) {
			layoutat(0, str, elem.getOutput());
			return str;
		}
		if (elem instanceof Split) {
			StringBuilder spaces = new StringBuilder();
			offset -= 3;
			for (int i = 0; i < offset; i++)
				spaces.append(" ");
			str.reverse().delete(0, 3).reverse();
			str.append("+-> ");
			layoutat(0, str, ((Split) elem).getOutputs()[0]);
			str.append(spaces);
			str.append("|\n");
			str.append(spaces);
			str.append("+-> ");
			layoutat(0, str, ((Split) elem).getOutputs()[1]);
			return str;
		}
		return str;
	}

	/**
	 * starts the simulation of the system
	 */
	public void simulate() {
		for (Element e : es) {
			if (e == null) return;
			if (e instanceof Source) {
				simulateAt(e);
			}
		}
	}

	private void simulateAt(Element eIn) {
		Element a;
		if (eIn instanceof Sink)
			return;
		else if (eIn instanceof Split) {
			Split s1 = (Split) eIn;
			a = (s1.getOutputs()[0]);
			a.setFlowin(s1.getFlowout()[0]);
			System.out.println(a.getName() + " " + (s1.getFlowout()[0]));
			simulateAt(a);
			a = (s1.getOutputs()[1]);
			a.setFlowin(s1.getFlowout()[1]);
			System.out.println(a.getName() + " " + (s1.getFlowout()[1]));
			simulateAt(a);
		} else {
			a = eIn.getOutput();
			a.setFlowin(((Double) eIn.getFlowout()));
			System.out.println(a.getName() + " " + ((Double) eIn.getFlowout()));
			simulateAt(a);
		}
	}
}
