package nth.meyn.cx.sysmac.converter.cx.ladder.model;

import java.util.ArrayList;
import java.util.List;

import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG;

public class CxLadderModelFactory {

	public static List<CxLadderModel> create(CxLadderDiagram cxLadderDiagram) {
		List<CxLadderModel> cxLadderModels=new ArrayList<>();
		List<RUNG> rungs = cxLadderDiagram.getRungList().getRUNG();
		for (RUNG rung : rungs) {
			CxLadderModel cxLadderModel = new CxLadderModel(rung);
			cxLadderModels.add(cxLadderModel);
		}
		return cxLadderModels;
	}

}
