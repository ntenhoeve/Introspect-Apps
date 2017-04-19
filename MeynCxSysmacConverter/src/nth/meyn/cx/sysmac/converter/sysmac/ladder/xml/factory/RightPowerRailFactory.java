package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import java.util.Arrays;
import java.util.List;

import nth.meyn.cx.sysmac.converter.cx.ladder.model.CxRightPowerRail;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement;

public class RightPowerRailFactory implements LadderElementFactory<CxRightPowerRail>{

	@Override
	public List<LadderElement> create(CxRightPowerRail cxLadderObject, IdFactory idFactory,
			String programName ) {
		LadderElement ladderElement = ConnectionFactory.create(idFactory, false, true);
		return Arrays.asList(ladderElement);
	}

}
