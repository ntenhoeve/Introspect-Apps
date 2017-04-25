package nth.meyn.cx.sysmac.converter.cx.ladder.xml;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class CxUnmarshaller {

	public static CxLadderDiagram createCxLadderDiagram(String xml) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(CxLadderDiagram.class);

		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		StringReader reader = new StringReader(xml);
		
		CxLadderDiagram cxLadderDiagram = (CxLadderDiagram) unmarshaller.unmarshal(reader);
		// TODO Auto-generated method stub
		return cxLadderDiagram;
	}

}
