package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml;

import java.util.HashMap;
import java.util.Map;

import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement;

public class Mapping {

	private Map<Object, LadderElement> mapping;
	
	public Mapping() {
		mapping=new HashMap<>();
	}
	
	public void put(Object cxObject, LadderElement sysmacObject) {
		mapping.put(cxObject, sysmacObject);
	}
	
	public  LadderElement getLadderElement(Object cxObject) {
		return mapping.get(cxObject);
	}
}
