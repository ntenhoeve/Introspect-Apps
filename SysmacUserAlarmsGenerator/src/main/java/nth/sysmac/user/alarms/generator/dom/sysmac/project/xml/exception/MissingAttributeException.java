package nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.exception;

public class MissingAttributeException extends RuntimeException {

	private static final long serialVersionUID = -1662283056085327919L;

	public MissingAttributeException(String attributeName, Exception e) {
		super ("Could not find attribute: "+attributeName,e);
	}

}
