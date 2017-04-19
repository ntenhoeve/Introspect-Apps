package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import java.util.Arrays;
import java.util.List;

import nth.meyn.cx.sysmac.converter.cx.ladder.model.CxConnectionHub;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement;

public class ConnectionHubFactory implements LadderElementFactory<CxConnectionHub> {


	@Override
	public List<LadderElement> create(CxConnectionHub cxConnectionHub, IdFactory idFactory,
			String programName) {
		LadderElement ladderElement = ConnectionFactory.create(idFactory, false, false);
		return Arrays.asList(ladderElement);
	}

}
