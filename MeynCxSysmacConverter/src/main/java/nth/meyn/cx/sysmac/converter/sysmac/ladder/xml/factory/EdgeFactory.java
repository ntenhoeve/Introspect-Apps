package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement;

public class EdgeFactory {
	public static final String EDGE = "Edge";
	
	public static LadderElement createEdge(String newInstanceId, String connectionPointSourceId, String connectionPointTargetId, boolean focusable) {
		LadderElement ladderElement = new LadderElement();
		ladderElement.setInstanceID(newInstanceId);
		ladderElement.setLadderElementType(EDGE);
		ladderElement.setFocusable(Boolean.toString(focusable));
		ladderElement.setSourceID(connectionPointSourceId);
		ladderElement.setTargetID(connectionPointTargetId);
		return ladderElement;
	}



}
