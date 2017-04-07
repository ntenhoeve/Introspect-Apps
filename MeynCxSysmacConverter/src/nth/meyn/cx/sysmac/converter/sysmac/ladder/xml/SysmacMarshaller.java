package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml;

import java.io.StringWriter;

import javax.swing.text.MaskFormatter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class SysmacMarshaller {
	public static String createXml(Rungs sysmacRungs) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Rungs.class);
		Marshaller marshaller = context.createMarshaller();
		StringWriter stringWriter = new StringWriter();

		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
//		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		marshaller.marshal(sysmacRungs, stringWriter);
		return stringWriter.toString();
		
	}
}
