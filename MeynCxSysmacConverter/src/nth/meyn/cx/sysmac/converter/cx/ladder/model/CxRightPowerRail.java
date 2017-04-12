package nth.meyn.cx.sysmac.converter.cx.ladder.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CxRightPowerRail {


	private final List<Object> inputs;

	public List<Object> getInputs() {
		return inputs;
	}

	public CxRightPowerRail(CxLadderModel cxLadderModel) {
		this.inputs=createInputs(cxLadderModel);
	}

	private List<Object> createInputs(CxLadderModel cxLadderModel) {
		List<Object> inputs = new ArrayList<>();
		int maxY = cxLadderModel.getMaxY();
		int x = cxLadderModel.getMaxX();
		for (int y = 0; y <= maxY; y++) {
			Object value = cxLadderModel.get(x, y);
			if (value != null) {
				inputs.add(value);
			}
		}
		return Collections.unmodifiableList(inputs);
	}
	
	

}
