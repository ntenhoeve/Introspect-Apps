package nth.meyn.cx.sysmac.converter.cx.ladder.model;

public class CxConnection {

	private final Object input;
	private final Object output;

	public CxConnection(Object input, Object output) {
		this.input = input;
		this.output = output;
	}

	public Object getInput() {
		return input;
	}

	public Object getOutput() {
		return output;
	}
	
	@Override
	public String toString() {
		StringBuilder reply=new StringBuilder();
		reply.append(input);
		reply.append("->");
		reply.append(output);
		return reply.toString();
	}

}
