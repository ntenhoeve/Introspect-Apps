package nth.introspect.apps.docgenforjavaproj.dom.javadoc.tag;

import java.util.Map;

import nth.introspect.apps.docgenforjavaproj.dom.javafile.JavaFile;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

public class InsertTag extends InlineTag {

	private final Map<String, JavaFile> javaFiles;


	public InsertTag(Map<String, JavaFile> javaFiles) {
		this.javaFiles = javaFiles;
	}


	@Override
	protected String getName() {
		return "insert";
	}


	@Override
	protected Element getElement(String fileName)  {
		JavaFile javaFile=javaFiles.get(fileName);
		String javaDoc=javaFile.getJavaDocOfClassDescriptor();
		return new Element(Tag.valueOf("p"),"").html(javaDoc);
	}




}
