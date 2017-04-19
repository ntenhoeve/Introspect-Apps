package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import java.util.Arrays;
import java.util.List;

import nth.meyn.cx.sysmac.converter.cx.ladder.model.CxLeftPowerRail;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement;

public class LeftPowerRailFactory implements LadderElementFactory<CxLeftPowerRail> {

	@Override
	public List<LadderElement> create(CxLeftPowerRail cxLadderObject, IdFactory idFactory,
			String programName) {
		LadderElement ladderElement = ConnectionFactory.create(idFactory, true, false);
		return Arrays.asList(ladderElement);
	}

}
