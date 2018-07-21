package nth.reflect.app.swdocgen.dom.javadoc.tag;

import java.util.Map;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import nth.reflect.app.swdocgen.dom.javafile.JavaFile;

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
		if (!javaFiles.containsKey(fileName)) {
			throw new RuntimeException("Could not find a java file with name:"+fileName);
		}
		JavaFile javaFile=javaFiles.get(fileName);
		String javaDoc=javaFile.getJavaDocOfClassDescriptor();
		return new Element(Tag.valueOf("p"),"").html(javaDoc);
	}




}
