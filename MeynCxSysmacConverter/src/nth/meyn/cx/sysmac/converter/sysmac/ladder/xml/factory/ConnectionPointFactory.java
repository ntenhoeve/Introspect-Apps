package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement.ConnectionPoint;
import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement.ConnectionPoint.Edge;
import nth.meyn.cx.sysmac.converter.sysmac.types.SysmacConnectionPointType;

public class ConnectionPointFactory {

	public static JAXBElement<ConnectionPoint> create(String newConnectionPointId,
			String edgeInstanceID, SysmacConnectionPointType sysmacConnectionPointType, Boolean powerPin) {
		ConnectionPoint connectionPoint = new ConnectionPoint();
		connectionPoint.setInstanceID(newConnectionPointId);
		connectionPoint.setConnectionPointType(sysmacConnectionPointType.toString().toLowerCase());
		if (powerPin!=null) {
			connectionPoint.setPowerPin(powerPin.toString());
		}
		Edge edge = new Edge();
		edge.setInstanceID(edgeInstanceID);
		connectionPoint.setEdge(edge);
		JAXBElement<ConnectionPoint> jaxbElement = new JAXBElement<ConnectionPoint>(
				QName.valueOf(ConnectionPoint.class.getSimpleName()), ConnectionPoint.class,
				connectionPoint);
		return jaxbElement;
	}

}
