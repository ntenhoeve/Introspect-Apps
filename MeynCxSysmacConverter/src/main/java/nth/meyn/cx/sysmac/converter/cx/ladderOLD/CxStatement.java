package nth.meyn.cx.sysmac.converter.cx.ladderOLD;

import java.util.List;

public class CxStatement {
private final CxDiff diff;
private final CxCommand command;
private final List<String> parameters;

public CxStatement(CxDiff diff, CxCommand command, List<String> parameters) {
	this.diff = diff;
	this.command = command;
	this.parameters = parameters;
	}

@Override
	public String toString() {
	StringBuilder result = new StringBuilder();
	result.append(diff.getPresentation());
	result.append(command.getName());
	result.append(" ");
	result.append(parameters);
	return result.toString();
	}
}
