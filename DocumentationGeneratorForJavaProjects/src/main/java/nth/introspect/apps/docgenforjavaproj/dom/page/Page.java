package nth.introspect.apps.docgenforjavaproj.dom.page;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import nth.introspect.generic.util.StringUtil;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public abstract class  Page {

	
	protected static final String HREF = "href";
	private final File file;
	private final Document document;
	private final File destinationFolder;
	private final String javaDocClass;

	public Page( File destinationFolder, String javaDocClass , Document javaDoc) {
		this.destinationFolder = destinationFolder;
		this.javaDocClass = javaDocClass;
		String title = createTitle( javaDoc);
		this.document = createDocument( title, javaDoc);
		updateInternalReferences(javaDoc.clone(), document);
		verifyExternalResources(document);
		this.file = createFile(title);
	}
	
	
	private String createTitle( Document javaDoc) {
		Element titleElement = javaDoc.select("title").first();
		if (titleElement != null) {
			return titleElement.html();
		}
		String title = StringUtil.convertToNormalCase(javaDocClass);
		return title;
	}

	protected abstract Document createDocument( String title, Document javaDoc);
	
	protected File createFile(String title) {
		StringBuilder filePath = new StringBuilder();
		filePath.append(getDestinationFolder());
		filePath.append("/");
		filePath.append(createFileName(title));
		File file = new File(filePath.toString());
		return file;
	}


	protected abstract String createFileName(String title);


	private void verifyExternalResources(Element body) {
		// TODO Auto-generated method stub

	}

	private void updateInternalReferences(Document javaDoc, Element body) {
		Elements elementsToUpdate = body.select("a[href^=Reference_]");

		Elements sequence = javaDoc.select("a[id^=Reference_],h1,h2,h3");

		for (Element elementToUpdate : elementsToUpdate) {
			String idToFind = elementToUpdate.attr(HREF);
			Elements found = ElementUtil
					.findAElementsWithId(sequence, idToFind);

			if (found.size() > 1) {
				System.out.println("Found multiple A elements with id:"
						+ idToFind);
				elementToUpdate.removeAttr(HREF);
			} else if (found.size() == 0) {
				System.out.println("Could not found an A element with id:"
						+ idToFind);
				elementToUpdate.removeAttr(HREF);
			} else {
				Element reference = found.get(0);
				Element hElement = ElementUtil.findPreviousHElement(sequence,
						reference);
				if (hElement == null) {
					System.out
							.println("Could not found a H element before A element with id:"
									+ reference.id());
					elementToUpdate.removeAttr(HREF);
				} else {
					String href = createReference(hElement);
					elementToUpdate.attr(HREF, href);
				}

			}
		}

	}
	protected abstract  String createReference(Element hElement) ;


	public void write() throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(fos,
				StandardCharsets.UTF_8);
		osw.write(document.toString());
		osw.flush();
		osw.close();
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("---------------------------------------------------------------\n");
		string.append("file:");
		string.append(file.getAbsolutePath());
		string.append("\n");
		string.append(document);
		return string.toString();
	}


	public File getDestinationFolder() {
		return destinationFolder;
	}


	
}
