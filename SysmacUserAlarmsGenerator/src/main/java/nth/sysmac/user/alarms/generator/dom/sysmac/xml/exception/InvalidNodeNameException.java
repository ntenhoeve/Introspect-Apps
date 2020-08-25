package nth.sysmac.user.alarms.generator.dom.sysmac.xml.exception;

public class InvalidNodeNameException extends RuntimeException {

	private static final long serialVersionUID = -6776013882761243324L;

	public InvalidNodeNameException(String expectedNodeName) {
		super("The node name did not match expected node name: "+expectedNodeName);
	}
}
