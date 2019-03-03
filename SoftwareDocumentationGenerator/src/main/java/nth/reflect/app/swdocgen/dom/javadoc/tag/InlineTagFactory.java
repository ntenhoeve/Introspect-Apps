package nth.reflect.app.swdocgen.dom.javadoc.tag;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.app.swdocgen.dom.javafile.DocumentationFiles;
import nth.reflect.app.swdocgen.dom.javafile.ReferenceName;

public class InlineTagFactory {

	public static List<InlineTag> getAllInlineTags(DocumentationFiles documentationFiles, ReferenceName referenceName) {
		List<InlineTag> inlineTags = new ArrayList<>();
		inlineTags.add(new InsertTag(documentationFiles, referenceName));
		inlineTags.add(new LinkTag());
		inlineTags.add(new LinkPlainTag());
		inlineTags.add(new CodeTag());
		return inlineTags;
	}

}
