package nth.meyn.cx.sysmac.converter.cx.ladder.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

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
	private final Map<CxLocation, Object> grid;
	private String comment;
	private final CxLeftPowerRail leftPowerRail;
	private final CxRightPowerRail rightPowerRail;
	private final CxVerticals verticals;
	private final CxConnectionHubs connectionHubs;
	private final List<CxConnection> connections;
	private final int maxX;
	private final int maxY;

	public CxLadderModel(RUNG rung) {
		verticals = new CxVerticals();
		comment = getComment(rung);
		grid=createGridWithObjects(rung);
		maxX = findMaxX();
		maxY = findMaxY();
		leftPowerRail = new CxLeftPowerRail(this);
		rightPowerRail = new CxRightPowerRail(this);
		connectionHubs = new CxConnectionHubs(this);
		connections = createConnections();
	}

	private int findMaxY() {
		int maxY = NOT_FOUND;
		Set<CxLocation> locations = grid.keySet();
		for (CxLocation location : locations) {
			int y = location.getY();
			if (y > maxY) {
				maxY = y;
			}
		}
		return maxY;
	}

	private int findMaxX() {
		int maxX = NOT_FOUND;
		Set<CxLocation> locations = grid.keySet();
		for (CxLocation location : locations) {
			int x = location.getX();
			if (x > maxX) {
				maxX = x;
			}
		}
		return maxX;
	}


	private Map<CxLocation, Object> createInstructionInputs(TreeMap<CxLocation, Object> grid) {
		Map<CxLocation, Object> instructionInputs=new HashMap<>();
		Set<CxLocation> locations = grid.keySet();
		for (CxLocation location : locations) {
			Object value = grid.get(location);
			if (value instanceof INSTRUCTION) {
				CxLocation instructionLocation1 = location.oneDown();
				if (grid.get(instructionLocation1.oneLeft()) != null) {
					CxInstructionInput instructionInput1 = new CxInstructionInput(
							(INSTRUCTION) value, 1);
					instructionInputs.put(instructionLocation1, instructionInput1);
				}
				CxLocation instructionLocation2 = instructionLocation1.oneDown();
				if (grid.get(instructionLocation2.oneLeft()) != null) {
					CxInstructionInput instructionInput2 = new CxInstructionInput(
							(INSTRUCTION) value, 2);
					instructionInputs.put(instructionLocation2, instructionInput2);
				}
				CxLocation instructionLocation3 = instructionLocation2.oneDown();
				if (grid.get(instructionLocation3.oneLeft()) != null) {
					CxInstructionInput instructionInput3 = new CxInstructionInput(
							(INSTRUCTION) value, 3);
					instructionInputs.put(instructionLocation3, instructionInput3);
				}

			}
		}
		return instructionInputs;
	}

	private Map<CxLocation, Object> createGridWithObjects(RUNG rung) {
		TreeMap<CxLocation, Object> grid = new TreeMap<>(new CxLocationComparator(true));
		List<Serializable> elements = rung.getElementList().getContent();
		for (Serializable element : elements) {
			@SuppressWarnings("rawtypes")
			JAXBElement jaxbElement = (JAXBElement) element;
			Object value = jaxbElement.getValue();
			if (value instanceof VERTICAL) {
				CxLocation location = getLocation((VERTICAL) value);
				verticals.add(location);
			} else if (value instanceof FB) {
				CxLocation location = getLocation((FB) value);
				grid.put(location, value);
			} else if (value instanceof COIL) {
				CxLocation location = getLocation((COIL) value);
				grid.put(location, value);
			} else if (value instanceof INSTRUCTION) {
				CxLocation location = getLocation((INSTRUCTION) value);
				grid.put(location, value);
			} else if (value instanceof FBPARAMETER) {
				CxLocation location = getLocation((FBPARAMETER) value);
				grid.put(location, value);
			} else if (value instanceof CONTACT) {
				CxLocation location = getLocation((CONTACT) value);
				grid.put(location, value);
			} else if (value instanceof HORIZONTAL) {
				CxLocation location = getLocation((HORIZONTAL) value);
				grid.put(location, value);
			} else
				throw new RuntimeException("Could not add cell:" + value.toString() + " to:"
						+ getClass().getSimpleName());

		}
		
		Map<CxLocation, Object> instructionInputs = createInstructionInputs(grid);
		grid.putAll(instructionInputs);
		
		return grid;
	}

	private String getComment(RUNG rung) {
		String comment = rung.getRUNGCOMMENT();
		comment = comment.trim();
		StringUtils.removeStart(comment, "\\n");
		comment = comment.trim();
		return comment;
	}

	private CxLocation getLocation(VERTICAL value) {
		CxLocation location = new CxLocation(value.getXPos(), value.getYPos());
		return location;
	}

	private CxLocation getLocation(HORIZONTAL value) {
		CxLocation location = new CxLocation(value.getXPos(), value.getYPos());
		return location;
	}

	private CxLocation getLocation(CONTACT value) {
		CxLocation location = new CxLocation(value.getXPos(), value.getYPos());
		return location;
	}

	private CxLocation getLocation(FBPARAMETER value) {
		CxLocation location = new CxLocation(value.getXPos(), value.getYPos());
		return location;
	}

	private CxLocation getLocation(INSTRUCTION value) {
		CxLocation location = new CxLocation(value.getXPos(), value.getYPos());
		return location;
	}

	private CxLocation getLocation(COIL value) {
		CxLocation location = new CxLocation(value.getXPos(), value.getYPos());
		return location;
	}

	private CxLocation getLocation(FB value) {
		CxLocation location = new CxLocation(value.getXPos(), value.getYPos());
		return location;
	}

	public Object get(int x, int y) {
		CxLocation location = new CxLocation(x, y);
		return grid.get(location);
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	@Override
	public String toString() {
		CxLadderModelPrinter printer = new CxLadderModelPrinter(this, 15);
		return printer.print();
	}

	public String getComment() {
		return comment;
	}

	@SuppressWarnings("unchecked")
	public <T extends Object> List<T> get(Class<T> classToFind) {
		List<T> found = new ArrayList<>();
		for (Object value : grid.values()) {
			if (classToFind.isAssignableFrom(value.getClass())) {
				found.add((T) value);
			}
		}
		return found;
	}

	@SuppressWarnings("unchecked")
	public <T extends Object> List<T> get(TreeMap<CxLocation, Object> grid, Class<T> classToFind) {
		List<T> found = new ArrayList<>();
		for (Object value : grid.values()) {
			if (classToFind.isAssignableFrom(value.getClass())) {
				found.add((T) value);
			}
		}
		return found;
	}
	public CxLeftPowerRail getLeftPowerRail() {
		return leftPowerRail;
	}

	public CxRightPowerRail getRightPowerRail() {
		return rightPowerRail;
	}

	/**
	 * follows the lines and gets all XML objects left of given coordinates
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public List<Object> findLeftOf(int x, int y, boolean includeVertical) {
		List<Object> found = new ArrayList<>();
		if (includeVertical && (verticals.goingUpFrom(x, y) || verticals.goingDownFrom(x, y))) {
			int top = verticals.getTop(x, y);
			int bottom = verticals.getBottom(x, y);
			for (int y2 = top; y2 <= bottom; y2++) {
				found.addAll(findLeftOf(x, y2, false));
			}
		} else {
			if (x >= 0) {
				Object value = get(x - 1, y);
				if (value != null) {
					if (value instanceof HORIZONTAL) {
						found.addAll(findLeftOf(x - 1, y, true));
					} else {
						found.add(value);
					}
				}
			}
		}
		return found;
	}

	/**
	 * follows the lines and gets all XML objects right of given coordinates
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public List<Object> findRightOf(int x, int y, boolean includeVertical) {
		List<Object> found = new ArrayList<>();
		if (includeVertical
				&& (verticals.goingUpFrom(x + 1, y) || verticals.goingDownFrom(x + 1, y))) {
			int top = verticals.getTop(x + 1, y);
			int bottom = verticals.getBottom(x + 1, y);
			for (int y2 = top; y2 <= bottom; y2++) {
				found.addAll(findRightOf(x, y2, false));
			}
		} else {
			if (x < getMaxX()) {
				Object value = get(x + 1, y);
				if (value != null) {
					if (value instanceof HORIZONTAL && x < getMaxX()) {
						found.addAll(findRightOf(x + 1, y, true));
					} else {
						found.add(value);
					}
				}
			}
		}
		return found;
	}

	public List<Object> getInputs(Object cxLadderObject) {
		if (cxLadderObject instanceof CxLeftPowerRail) {
			return new ArrayList<>();
		} else if (cxLadderObject instanceof CxRightPowerRail) {
			return ((CxRightPowerRail) cxLadderObject).getInputs();
		} else if (cxLadderObject instanceof CxConnectionHub) {
			return ((CxConnectionHub) cxLadderObject).getInputs();
		} else {
			if (leftPowerRail.getOutputs().contains(cxLadderObject)) {
				return Arrays.asList(leftPowerRail);
			}

			CxConnectionHub connectionHub = connectionHubs.findOutput(cxLadderObject);
			if (connectionHub != null) {
				return Arrays.asList(connectionHub);
			}

			CxLocation location = getLocation(cxLadderObject);
			List<Object> inputs = findLeftOf(location.getX(), location.getY(), true);
			if (inputs.size() == 1) {
				return inputs;
			} else {
				throw new RuntimeException("An CX-One XML object sould only have one output!");
			}
		}

	}

	public List<Object> getOutputs(Object cxLadderObject) {
		if (cxLadderObject instanceof CxLeftPowerRail) {
			return ((CxLeftPowerRail) cxLadderObject).getOutputs();
		} else if (cxLadderObject instanceof CxRightPowerRail) {
			return new ArrayList<>();
		} else if (cxLadderObject instanceof CxConnectionHub) {
			return ((CxConnectionHub) cxLadderObject).getOutputs();
		} else {

			if (rightPowerRail.getInputs().contains(cxLadderObject)) {
				return Arrays.asList(rightPowerRail);
			}

			CxConnectionHub connectionHub = connectionHubs.findInput(cxLadderObject);
			if (connectionHub != null) {
				return Arrays.asList(connectionHub);
			}

			CxLocation location = getLocation(cxLadderObject);
			List<Object> outputs = findRightOf(location.getX(), location.getY(), true);
			if (outputs.size() == 1) {
				return outputs;
			} else {
				StringBuilder message = new StringBuilder(
						"The following ladder XML object must have one output:\r");
				CxLadderModelPrinter printer = new CxLadderModelPrinter(this, 15);
				message.append(printer.print(location));
				if (outputs.size() == 0) {
					message.append("\rThere are no outputs.");
				} else {
					message.append("\rOutputs:\r");
					for (Object output : outputs) {
						message.append(printer.print(getLocation(output)));
					}
				}
				throw new RuntimeException(message.toString());
			}
		}

	}

	public CxLocation getLocation(Object cxLadderObject) {
		Set<CxLocation> locations = grid.keySet();
		for (CxLocation location : locations) {
			if (grid.get(location).equals(cxLadderObject)) {
				return location;
			}
		}
		throw new RuntimeException(
				"Could not find a location for CX-One XML object: " + cxLadderObject);
	}

	public CxVerticals getVerticals() {
		return verticals;
	}

	public Set<CxLocation> getLocations() {
		return grid.keySet();
	}

	public Set<CxConnectionHub> getConnectionHubs() {
		return connectionHubs.getAll();
	}

	private List<CxConnection> createConnections() {
		List<CxConnection> connections = new ArrayList<>();
		for (Object input : getConnectingObjects()) {
			List<Object> outputs = getOutputs(input);
			for (Object output : outputs) {
				CxConnection connection = new CxConnection(input, output);
				connections.add(connection);
			}
		}
		return connections;
	}

	public List<CxConnection> getConnections() {
		return connections;
	}

	/**
	 * Note: order must be same as Sysmac XML!!!<br>
	 * TODO add FB
	 * 
	 * @return
	 */

	public List<Object> getContactsCoilsAndInstructions() {
		List<Object> objects = new ArrayList<>();
		List<CONTACT> contacts = new ArrayList<>( get(CONTACT.class));
//		Collections.reverse(contacts);
		objects.addAll(contacts);
		List<COIL> coils = get( COIL.class);
		objects.addAll(coils);
		List<INSTRUCTION> instructions = get( INSTRUCTION.class);
		objects.addAll(instructions);
		return objects;
	}

	/**
	 * Note: order must be same as Sysmac XML!!!
	 * 
	 * @return
	 */
	public List<Object> getConnectingObjects() {
		List<Object> objects = new ArrayList<>();
		objects.addAll(getContactsCoilsAndInstructions());
		objects.addAll(get( CxInstructionInput.class));
		objects.add(leftPowerRail);
		objects.add(rightPowerRail);
		objects.addAll(connectionHubs.getAll());
		return objects;
	}

//	public List<Object> getConnectingObjectsLastLineFirst() {
//		TreeMap<CxLocation,Object> gridUpsideDown=new TreeMap<>(new CxLocationComparator(false));
//		gridUpsideDown.putAll(grid);
//		List<Object> objects = new ArrayList<>();
//		objects.addAll(get(gridUpsideDown, CONTACT.class));
//		objects.addAll(get(gridUpsideDown,COIL.class));
//		objects.addAll(get(gridUpsideDown ,INSTRUCTION.class));
//		objects.addAll(get(gridUpsideDown, CxInstructionInput.class));
//		objects.add(leftPowerRail);
//		objects.add(rightPowerRail);
//		objects.addAll(connectionHubs.getAll());
//		return objects;
//	}

//	public List<Object> getConnectingObjectsinXmlOrder() {
//	TreeMap<CxLocation,Object> gridUpsideDown=new TreeMap<>(new CxLocationComparator(false));
//	gridUpsideDown.putAll(grid);
//	List<Object> objects = new ArrayList<>();
//	objects.add(leftPowerRail);
//	objects.add(rightPowerRail);
//	objects.addAll(connectionHubs.getAll());
//	List<Object> gridObjects = new ArrayList(grid.values());
//	gridObjects.removeIf(o -> (o instanceof HORIZONTAL));
//	objects.addAll(gridObjects);
//	TODO order werkt nog niet goed!!!
//	return objects;
//}
	
}
