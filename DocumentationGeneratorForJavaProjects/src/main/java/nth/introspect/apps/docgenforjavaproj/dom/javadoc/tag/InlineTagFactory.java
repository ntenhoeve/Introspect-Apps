package nth.introspect.apps.docgenforjavaproj.dom.javadoc.tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nth.introspect.apps.docgenforjavaproj.dom.javafile.JavaFile;

/**
 * {@docRoot}
 * {@literal}
 * {@value}
 * @author nilsth
 *
 */
public class InlineTagFactory {

	public static List<InlineTag> getAllInlineTags(
			Map<String, JavaFile> javaFiles) {
		List<InlineTag> inlineTags = new ArrayList<>();
		inlineTags.add(new InsertTag(javaFiles));
		inlineTags.add(new LinkTag());
		inlineTags.add(new LinkPlainTag());
		inlineTags.add(new CodeTag());
		return inlineTags;
	}

}
