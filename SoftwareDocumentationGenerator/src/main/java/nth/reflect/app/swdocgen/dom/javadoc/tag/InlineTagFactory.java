package nth.reflect.app.swdocgen.dom.javadoc.tag;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.app.swdocgen.dom.javafile.DocumentationFiles;

public class InlineTagFactory {

	public static List<InlineTag> getAllInlineTags(DocumentationFiles documentationFiles) {
		List<InlineTag> inlineTags = new ArrayList<>();
		inlineTags.add(new InsertTag(documentationFiles));
		inlineTags.add(new LinkTag());
		inlineTags.add(new LinkPlainTag());
		inlineTags.add(new CodeTag());
		return inlineTags;
	}

}
