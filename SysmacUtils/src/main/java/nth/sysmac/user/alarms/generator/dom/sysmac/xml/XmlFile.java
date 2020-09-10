package nth.sysmac.user.alarms.generator.dom.sysmac.xml;

import java.io.File;
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
import org.w3c.dom.Node;

import nth.sysmac.user.alarms.generator.dom.sysmac.SysmacProject;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.exception.XPathNotFoundException;

/**
 * Represents an XML file within a {@link SysmacProject} file. The {@link SysmacProject} file is essentially a zip file.
 * @author nilsth
 *
 */
public class XmlFile {

	public static final String XML_EXTENSION = ".xml";
	protected final Document document;
	protected final ZipEntry zipEntry;
	protected final ZipFile zipFile;

	public XmlFile(ZipFile zipFile, ZipEntry zipEntry) {
		this.zipFile = zipFile;
		this.zipEntry = zipEntry;
		InputStream inputStream = getInputStream(zipFile, zipEntry);
		this.document = parse(zipEntry, inputStream);
	}

	public XmlFile(XmlFile xmlFile) {
		this.zipFile=xmlFile.getZipFile();
		this.zipEntry=xmlFile.getZipEntry();
		this.document=xmlFile.getDocument();
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

	public boolean containsNode(String xPathExpression) {
		try {
			findNode(xPathExpression);
			return true;
		} catch (Throwable t) {
			return false;
		}
	}

	public Node findNode(String xPathExpression) {
		XPath xPath = XPathFactory.newInstance().newXPath();
		try {
			Node node = (Node) xPath.compile(xPathExpression).evaluate(document, XPathConstants.NODE);
			if (node == null) {
				throw new XPathNotFoundException(this, xPathExpression);
			}
			return node;
		} catch (XPathExpressionException e) {
			throw new XPathNotFoundException(this, xPathExpression, e);
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

	public Document getDocument() {
		return document;
	}

	public ZipFile getZipFile() {
		return zipFile;
	}

	public ZipEntry getZipEntry() {
		return zipEntry;
	}

	public String getZipEntryFileName() {
		String path=zipEntry.getName();
		int lastSeperator = path.lastIndexOf("/")+1;
		String zipEntryFileName=path.substring(lastSeperator);
		return zipEntryFileName;
	}
	
	public String getZipPath() {
		return zipFile.getName() + File.separator + zipEntry.getName();
	}
	
}
