package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.expression.node.matcher.result;

public class NoResultsFoundException extends RuntimeException {

	public static final String MESSAGE = "No results where found!";
	private static final long serialVersionUID = 6211589427425452803L;

	public NoResultsFoundException() {
		super(MESSAGE);	
	}
}
