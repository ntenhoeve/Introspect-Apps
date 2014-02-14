package nth.accounts.domain.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import nth.introspect.Introspect;
import nth.introspect.util.xml.XmlUtil;

/**
 * A very simple data access class for the {@link Introspect} framework that can be extended to store and retrieve 
 * {@link DomainObjectModelRepository#domainObjectModel} in an object database (a XML file)
 * 
 * @author nilsth
 *
 * @param <T> type of the domain object model (A single object that holds references to all domain objects to be stored/retrieved)
 */
//TODO implement DataAccess interface
//TODO move this class to a new IntrospectDataAccessObjectRepositority
abstract public class DomainObjectModelRepository<T> {

	private Boolean xmlIndent;
	private File xmlDatabaseFile;
	/**
	 * A single object that holds references to all domain objects to be stored/retrieved
	 */
	private T domainObjectModel;

	/**
	 * See {@link DomainObjectModelRepository}
	 * @param domainObjectModel See {@link DomainObjectModelRepository#domainObjectModel} 
	 * @param xmlIndent True if the data in the XML file needs to be indented (indents make the XML easier to read by a human, but result in more data to store and process)
	 */
	// TODO add parameter String encryptionKey
	public DomainObjectModelRepository(T domainObjectModel, Boolean xmlIndent) {
		this.domainObjectModel = domainObjectModel;
		this.xmlIndent = xmlIndent;
		String databaseFileName = domainObjectModel.getClass().getSimpleName() + ".xml";
		URI databaseUri = Introspect.getPathProvider().getConfigPath(databaseFileName);
		this.xmlDatabaseFile = new File(databaseUri);
	}

	public T get() throws Exception {
		String xml = readXmlDatabaseFile();
		domainObjectModel=(T) XmlUtil.unmarshalFirst(xml.toString());
		return domainObjectModel;
	}
	
	private String readXmlDatabaseFile() throws FileNotFoundException  {
		if (xmlDatabaseFile.exists()) {
			return new Scanner(xmlDatabaseFile).useDelimiter("\\Z").next();
		} else  {
			return "";
		}
	}

	public void persist(T domainObject) throws Exception {
		String xml = XmlUtil.marshal(domainObjectModel, xmlIndent);
		PrintWriter out = new PrintWriter(xmlDatabaseFile);
		out.print(xml);
		out.close();
	}


}
