package nth.meyn.cx.sysmac.converter.cx.ladder.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBElement;

import org.apache.commons.lang3.StringUtils;

import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.COIL;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.CONTACT;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.FB;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.FBPARAMETER;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.HORIZONTAL;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.INSTRUCTION;
import nth.meyn.cx.sysmac.converter.cx.ladder.xml.CxLadderDiagram.RungList.RUNG.ElementList.VERTICAL;

public class CxLadderModel {
 
	static final String NEW_LINE = "\r";
	private static final int NOT_FOUND = -1;
	private final Map<Location, Object> grid;
	private final Map<Location, Boolean> isVertical;
	private  String comment;

	public CxLadderModel(RUNG rung) {
		grid=new HashMap<>();
		isVertical=new HashMap<>();
		populate(rung);
}
	
	private void populate(RUNG rung) {
		comment=getComment(rung);
		List<Serializable> elements = rung.getElementList().getContent();
		for (Serializable element : elements) {
			@SuppressWarnings("rawtypes")
			JAXBElement jaxbElement=(JAXBElement) element;
			Object value = jaxbElement.getValue();
			if (value instanceof VERTICAL) {
				Location location=getLocation((VERTICAL)value);
				isVertical.put(location, true);
			}  else if (value instanceof FB) {
				Location location=getLocation((FB)value);
				grid.put(location, value);
			} else if (value instanceof COIL) {
				Location location= getLocation((COIL)value);
				grid.put(location, value);
			} else if (value instanceof INSTRUCTION) {
				Location location= getLocation((INSTRUCTION)value);
				grid.put(location, value);
			}else if (value instanceof FBPARAMETER) {
				Location location= getLocation((FBPARAMETER)value);
				grid.put(location, value);
			}else if (value instanceof CONTACT) {
				Location location= getLocation((CONTACT)value);
				grid.put(location, value);
			}else if (value instanceof HORIZONTAL) {
				Location location= getLocation((HORIZONTAL)value);
				grid.put(location, value);
			} else throw new RuntimeException("Could not add cell:"+ value.toString() +" to:" +getClass().getSimpleName());
			
			
		}
		
	}

	private String getComment(RUNG rung) {
		String comment= rung.getRUNGCOMMENT();
		comment=comment.trim();
		StringUtils.removeStart(comment, "\\n");
		comment=comment.trim();
		return comment;
	}

	private Location getLocation(VERTICAL value) {
		Location location=new Location(value.getXPos(), value.getYPos());
		return location;
	}

	private Location getLocation(HORIZONTAL value) {
		Location location=new Location(value.getXPos(), value.getYPos());
		return location;
	}

	private Location getLocation(CONTACT value) {
		Location location=new Location(value.getXPos(), value.getYPos());
		return location;
	}

	private Location getLocation(FBPARAMETER value) {
		Location location=new Location(value.getXPos(), value.getYPos());
		return location;
	}

	private Location getLocation(INSTRUCTION value) {
		Location location=new Location(value.getXPos(), value.getYPos());
		return location;
	}

	private Location getLocation(COIL value) {
		Location location=new Location(value.getXPos(), value.getYPos());
		return location;
	}

	private Location getLocation(FB value) {
		Location location=new Location(value.getXPos(), value.getYPos());
		return location;
	}

	public void put(int x, int y, String text) {
		Location location=new Location(x,y);
		grid.put(location, text);
	}
	
	public Object get(int x, int y) {
		Location location=new Location(x,y);
		return grid.get(location);
	}

	





	public int getMaxX() {
		int maxX = NOT_FOUND;
		Set<Location> locations = grid.keySet();
		for (Location location : locations) {
			int x = location.getX();
			if (x>maxX) {
				maxX=x;
			}
		}
		return maxX;
	}

	public int getMaxY() {
		int maxY = NOT_FOUND;
		Set<Location> locations = grid.keySet();
		for (Location location : locations) {
			int y = location.getY();
			if (y>maxY) {
				maxY=y;
			}
		}
		return maxY;
	}
	
	@Override
	public String toString() {
		CxLadderModelPrinter printer = new CxLadderModelPrinter(this, 15);
		return printer.print();
	}



	public boolean hasVertical(int x, int y) {
		Location location=new Location(x,y);
		Set<Location> keys = isVertical.keySet();
		for (Location key : keys) {
			if (key.equals(location)) {
				Boolean value = isVertical.get(key);
				return value!=null && value==true; 
			}
		}
		return false;
	}

	public String getComment() {
		return comment;
	}

	
	
	
}
