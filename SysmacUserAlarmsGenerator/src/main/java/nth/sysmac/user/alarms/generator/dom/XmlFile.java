package nth.sysmac.user.alarms.generator.dom;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlFile {

	private final Document document;

	public XmlFile(ZipFile zipFile, ZipEntry zipEntry) {
		InputStream inputStream = getInputStream(zipFile, zipEntry);
		document = parse(zipEntry, inputStream);
	}

	private Document parse(ZipEntry zipEntry, InputStream inputStream) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			return builder.parse(inputStream);
		} catch (Exception e) {
			throw new RuntimeException("Could not parse xml file: " + zipEntry.getName(), e);
		}
	}

	private InputStream getInputStream(ZipFile zipFile, ZipEntry zipEntry) {
		try {
			return zipFile.getInputStream(zipEntry);
		} catch (IOException e) {
			throw new RuntimeException("Error reading file: " + zipEntry.getName(), e);
		}
	}

	/**
	 * Using xPath the search the document
	 * See https://www.baeldung.com/java-xpath
	 */
	public boolean containsDataTypes() {
		System.out.println(this);
		String expression = "/data/DataType";
		XPath xPath = XPathFactory.newInstance().newXPath();
		try {
			Node node = (Node) xPath.compile(expression).evaluate(document, XPathConstants.NODE);
			return node!=null;
		} catch (XPathExpressionException e) {
			return false;
		}
	}

	@Override
	public String toString() {
		try {
			Transformer tf = TransformerFactory.newInstance().newTransformer();
			tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			Writer out = new StringWriter();
			tf.transform(new DOMSource(document), new StreamResult(out));
			return out.toString();
		} catch (Exception e) {
			throw new RuntimeException("Error converting docoment to string", e);
		}
	}
}
