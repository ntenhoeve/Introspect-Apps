package nth.reflect.app.swdocgen.dom.javadoc.tag;

import java.util.Optional;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import nth.reflect.app.swdocgen.dom.javafile.DocumentationFiles;
import nth.reflect.app.swdocgen.dom.javafile.JavaFile;
import nth.reflect.app.swdocgen.dom.javafile.ReferenceName;

public class InsertTag extends InlineTag {

	private final DocumentationFiles documentationFiles;

	public InsertTag(DocumentationFiles documentationFiles) {
		this.documentationFiles = documentationFiles;
	}

	@Override
	protected String getName() {
		return "insert";
	}

	@Override
	protected Element getElement(String nameToFind) {
		ReferenceName referenceName = new ReferenceName(nameToFind);
		Optional<JavaFile> result = documentationFiles.findJavaFile(referenceName);
		if (!result.isPresent()) {
			throw new RuntimeException("Could not find a java file with name:" + nameToFind);
		}
		JavaFile javaFile = result.get();
		String javaDoc = javaFile.getJavaDocHtmlOfClassDescriptor().toString();
		return new Element(Tag.valueOf("p"), "").html(javaDoc);
	}

}
