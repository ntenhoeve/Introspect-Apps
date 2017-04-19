package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement;

public class ConnectionFactory {

	private static final String CONNECTION = "Connection";

	public static LadderElement create(IdFactory idFactory, boolean isLeftPowerRail,
			boolean isRightPowerRail) {
		LadderElement ladderElement = new LadderElement();
		ladderElement.setInstanceID(idFactory.createNext());
		ladderElement.setLadderElementType(CONNECTION);
		ladderElement.setIsLeftPowerRail(Boolean.toString(isLeftPowerRail));
		ladderElement.setIsRightPowerRail(Boolean.toString(isRightPowerRail));
		return ladderElement;
	}
}
