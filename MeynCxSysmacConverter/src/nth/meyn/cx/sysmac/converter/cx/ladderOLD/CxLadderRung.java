package nth.meyn.cx.sysmac.converter.cx.ladderOLD;

import java.util.ArrayList;
import java.util.List;

public class CxLadderRung {

	private String description;
	private final List<CxStatement> statements;

	public CxLadderRung() {
		statements=new ArrayList<>();
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public List<CxStatement> getStatements() {
		return statements;
	}
	
	@Override
	public String toString() {
		StringBuilder result=new StringBuilder();
		result.append(description);
		result.append("\r");
		for (CxStatement statement : statements) {
			result.append(statement);
			result.append("\r");
		}
		return result.toString();
	}
	

}
