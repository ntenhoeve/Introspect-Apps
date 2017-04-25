package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import java.util.List;

import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement;

public interface LadderElementFactory<T> {

	List<LadderElement> createForInput1(T cxLadderObject, IdFactory idFactory, String programName);

	
}
