package nth.meyn.cx.sysmac.converter.cx.ladder.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CxLeftPowerRail {

	private final List<Object> outputs;

	public List<Object> getOutputs() {
		return outputs;
	}

	public CxLeftPowerRail(CxLadderModel cxLadderModel) {
		outputs=createOutputs(cxLadderModel);
	}
	

	private List<Object> createOutputs(CxLadderModel cxLadderModel) {
		List<Object> outputs=new ArrayList<>();
		int maxY=cxLadderModel.getMaxY();
		int x=0;
		for (int y=0;y<=maxY;y++) {
			Object value = cxLadderModel.get(x,y);
			if (value != null) {
				outputs.add(value);
			}
		}
		return Collections.unmodifiableList(outputs);
	}

}
