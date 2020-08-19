package nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.exception;

import javax.xml.xpath.XPathExpressionException;

import nth.sysmac.user.alarms.generator.dom.sysmac.project.xml.XmlFile;

public class XPathNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1715780398150324066L;

	public XPathNotFoundException(XmlFile xmlFile, String xPathExpression) {
		super(getMessage(xmlFile, xPathExpression));
	}
	
	public XPathNotFoundException(XmlFile xmlFile, String xPathExpression, XPathExpressionException e) {
		super(getMessage(xmlFile, xPathExpression), e);
	}

	private static String getMessage(XmlFile xmlFile, String xPathExpression) {
		return "XPath expression: "+xPathExpression+" could not be found in:"+xmlFile.getZipPath();
	}

	
}
