package nth.reflect.app.swdocgen.dom.page;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import nth.reflect.fw.generic.util.StringUtil;

public abstract class  Page implements WritableFile {

	
	protected static final String HREF = "href";
	private File file;
	private Document contents;
	private final File destinationFolder;
	private final String javaDocClass;
	private final Document javaDoc;

	public Page( File destinationFolder, String javaDocClass , Document javaDoc) {
		this.destinationFolder = destinationFolder;
		this.javaDocClass = javaDocClass;
		this.javaDoc = javaDoc;
	}
	
	
	public  String getTitle() {
		Element titleElement = javaDoc.select("MaterialAppBarTitle").first();
		if (titleElement != null) {
			return titleElement.html();
		}
		String title = StringUtil.convertToNormalCase(javaDocClass);
		return title;
	}

	
	protected File createFile(String title) {
		StringBuilder filePath = new StringBuilder();
		filePath.append(getDestinationFolder());
		filePath.append("/");
		filePath.append(createFileName(title));
		File file = new File(filePath.toString());
		return file;
	}


	protected abstract String createFileName(String title);

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


	/* (non-Javadoc)
	 * @see nth.reflect.app.swdocgen.dom.page.WritableFile#write()
	 */
	@Override
	public void write() throws IOException {
		FileOutputStream fos = new FileOutputStream(getFile());
		OutputStreamWriter osw = new OutputStreamWriter(fos,
				StandardCharsets.UTF_8);
		osw.write(getContents().toString());
		osw.flush();
		osw.close();
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("---------------------------------------------------------------\n");
		string.append("file:");
		string.append(getFile().getAbsolutePath());
		string.append("\n");
		string.append(getContents());
		return string.toString();
	}

	

	public File getFile() {
		if (file==null) {
			String title=getTitle();
			file = createFile(title);
		}
		return file;
	}


	public File getDestinationFolder() {
		return destinationFolder;
	}


	public Document getContents() {
		if (contents ==null) {
			contents=createContents();
			updateInternalReferences(javaDoc.clone(), contents);
		}
		return contents;
	}


	public  abstract Document createContents() ;


	public String getJavaDocClass() {
		return javaDocClass;
	}


	public Document getJavaDoc() {
		return javaDoc.clone();
	}



	
}
