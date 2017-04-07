package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.commons.lang3.StringUtils;

import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement.ConnectionPoint;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement.ConnectionPoint.Edge;

public class SysmacLadderElementFactory {

	private static final String HEX_PREFIX = "0x";
	private static final String CONNECTION = "Connection";
	private static final String EDGE = "Edge";
	private static final String OUTPUT = "output";
	private static final String INPUT = "input";
	private static final String CONTACT = "Contact";
	private static final String BOOL = "BOOL";
	private static final String COIL = "Coil";
	private int instanceId;

	public SysmacLadderElementFactory() {
		instanceId = 10067-1;
	}

//	public  String getNextInstanceId() {
//		instanceId++;
//		return getHexId(instanceId);
//	}

	public static  String getHex(int id) {
		StringBuilder builder = new StringBuilder();
		builder.append(HEX_PREFIX);
		String hexString = Integer.toHexString(id).toUpperCase();
		int leadingZeros = 8 - hexString.length();
		for (int i = 0; i < leadingZeros; i++) {
			builder.append("0");
		}
		builder.append(hexString);
		return builder.toString();
	}


	private int getInt(String hex) {
		hex=StringUtils.removeStart(hex, HEX_PREFIX);
		long l=Long.parseLong(hex, 16);
		return (int) l;
	}

	
	/**
	 * {@link ConnectionPoint} properties is added later see {@link #createConnection(RungXML, LadderElement, LadderElement)}
	 * @param newInstanceId 
	 * @return
	 */
	public LadderElement createLeftPowerRail(String newInstanceId) {
		LadderElement ladderElement = new LadderElement();
		ladderElement.setInstanceID(newInstanceId);
		ladderElement.setLadderElementType(CONNECTION);
		ladderElement.setIsLeftPowerRail(Boolean.TRUE.toString());
		ladderElement.setIsRightPowerRail(Boolean.FALSE.toString());
		return ladderElement;
	}

	/**
	 * {@link ConnectionPoint} properties is added later see {@link #createConnection(RungXML, LadderElement, LadderElement)}
	 * @param newInstanceId 
	 * @return
	 */
	public LadderElement createRightPowerRail(String newInstanceId) {
		LadderElement ladderElement = new LadderElement();
		ladderElement.setInstanceID(newInstanceId);
		ladderElement.setLadderElementType(CONNECTION);
		ladderElement.setIsLeftPowerRail(Boolean.FALSE.toString());
		ladderElement.setIsRightPowerRail(Boolean.TRUE.toString());
		// add ConnectionPointInput later
		return ladderElement;

	}

	/**
	 * {@link LadderElement#setSourceID()} and {@link LadderElement#setTargetID(String)} properties are set later see {@link #createConnection(RungXML, LadderElement, LadderElement)}
	 * @param newInstanceId 
	 * @return
	 */

	public LadderElement createEdge(String newInstanceId) {
		LadderElement ladderElement = new LadderElement();
		ladderElement.setInstanceID(newInstanceId);
		ladderElement.setLadderElementType(EDGE);
		ladderElement.setFocusable(Boolean.TRUE.toString());
		return ladderElement;
	}

	public JAXBElement<ConnectionPoint> createConnectionPointOutput(String newConnectionPointId,
			String edgeInstanceID) {
		ConnectionPoint connectionPoint = new ConnectionPoint();
		connectionPoint.setInstanceID(newConnectionPointId);
		connectionPoint.setConnectionPointType(OUTPUT);
		Edge edge = new Edge();
		edge.setInstanceID(edgeInstanceID);
		connectionPoint.setEdge(edge);
		JAXBElement<ConnectionPoint> jaxbElement = new JAXBElement<ConnectionPoint>(
				QName.valueOf(ConnectionPoint.class.getSimpleName()), ConnectionPoint.class,
				connectionPoint);
		return jaxbElement;
	}

	public JAXBElement<ConnectionPoint> createConnectionPointIntput(String newConnectionPointId,
			String edgeInstanceID) {
		ConnectionPoint connectionPoint = new ConnectionPoint();
		connectionPoint.setInstanceID(newConnectionPointId);
		connectionPoint.setConnectionPointType(INPUT);
		Edge edge = new Edge();
		edge.setInstanceID(edgeInstanceID);
		connectionPoint.setEdge(edge);
		JAXBElement<ConnectionPoint> jaxbElement = new JAXBElement<ConnectionPoint>(
				QName.valueOf(ConnectionPoint.class.getSimpleName()), ConnectionPoint.class,
				connectionPoint);
		return jaxbElement;
	}

	public void createConnection(RungXML sysmacRung, String edgeId,  LadderElement source, LadderElement target) {

		LadderElement edge = createEdge(edgeId);
		sysmacRung.getLadderElement().add(edge);
		


		String connectionPointOutputId = getHex(getInt(source.getInstanceID())+source.getContent().size()+1);
		JAXBElement<ConnectionPoint> connectionPointOutput = createConnectionPointOutput(
				connectionPointOutputId, edge.getInstanceID());
		source.getContent().add(connectionPointOutput);

		

		String connectionPointInputId = getHex(getInt(target.getInstanceID())+target.getContent().size()+1);
		JAXBElement<ConnectionPoint> connectionPointInput = createConnectionPointIntput(
				connectionPointInputId, edge.getInstanceID());
		target.getContent().add(connectionPointInput);

		
		edge.setSourceID(connectionPointOutputId);
		edge.setTargetID(connectionPointInputId);


	}

	public LadderElement createContact(String newInstanceId, String programName, String varName, boolean inverted, boolean diffUp,
			boolean diffDown) {
		LadderElement contact=new LadderElement();
		contact.setInstanceID(newInstanceId);
		contact.setLadderElementType(CONTACT);
		contact.setInverted(Boolean.toString(inverted));
		contact.setDiffUp(Boolean.toString(diffUp));
		contact.setDiffDown(Boolean.toString(diffDown));
		contact.setVariableName(varName);
		contact.setBaseVariableName(varName);
		contact.setProgramName(programName);
		contact.setBaseVariableDataType(BOOL);
		return contact;
	}
	
	
	public LadderElement createCoil(String coilID, String programName, String varName, boolean inverted, boolean diffUp,
			boolean diffDown, boolean set, boolean reset) {
		LadderElement coil=new LadderElement();
		coil.setInstanceID(coilID);
		coil.setLadderElementType(COIL);
		coil.setInverted(Boolean.toString(inverted));
		coil.setDiffUp(Boolean.toString(diffUp));
		coil.setDiffDown(Boolean.toString(diffDown));
		coil.setSet(Boolean.toString(set));
		coil.setReset(Boolean.toString(reset));
		coil.setVariableName(varName);
		coil.setBaseVariableName(varName);
		coil.setProgramName(programName);
		coil.setBaseVariableDataType(BOOL);
		return coil;
	}

}
