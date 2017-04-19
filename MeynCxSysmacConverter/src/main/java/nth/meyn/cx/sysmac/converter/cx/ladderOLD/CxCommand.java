package nth.meyn.cx.sysmac.converter.cx.ladderOLD;

import java.util.ArrayList;
import java.util.List;

public enum CxCommand {
  LD, LDNOT, OR, ORNOT, ORLD, AND, ANDNOT, ANDLD, OUT, OUTNOT, MOVE_021, KEEP_011, TIMX_550;
	
	private static final String UNDER_SCORE = "_";

	
	public String getName() {
		String name = this.toString();
		if (name.contains(UNDER_SCORE)) {
			name=name.replace("_", "(")+")";
		}
		return name;
	}
	
	public static  List<String> getNames() {
		List<String> names=new ArrayList<>();
for (CxCommand cxCommand : values()) {
	names.add(cxCommand.getName());
}
		return names;
	}
	


	
	public static CxCommand valueOfName(String name) {
		for (CxCommand cxCommand : values()) {
			if (cxCommand.getName().equals(name)) {
				return cxCommand;
			}
		}
		return null;
	}
	
	
}
