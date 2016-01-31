package nth.introspect.apps.docgenforjavaproj.dom.javadoc;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import nth.introspect.apps.docgenforjavaproj.dom.documentation.DocumentationInfo;
import nth.introspect.apps.docgenforjavaproj.dom.javadoc.tag.InlineTag;
import nth.introspect.apps.docgenforjavaproj.dom.javadoc.tag.InlineTagFactory;
import nth.introspect.apps.docgenforjavaproj.dom.javafile.JavaFile;
import nth.introspect.generic.util.StringUtil;
import nth.introspect.generic.util.TitleBuilder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JavaDocFactory {
	private static final String NONE_WORD_CHARACTER = "\\W";


	public static Document getAllJavaDoc(DocumentationInfo documentationInfo, Map<String, JavaFile> javaFiles )
			throws IOException {

		JavaFile rootFile = javaFiles.get(documentationInfo.getClassName());

		if (rootFile == null) {
			throw new RuntimeException("Could not found: "
					+ documentationInfo.getClassName() + " in folder:"
					+ documentationInfo.getProjectsFolder());
		}

		String javaDoc = rootFile.getJavaDocOfClassDescriptor();

		javaDoc = replaceAllInlineTags(javaDoc, javaFiles);

		Document html = Jsoup.parse(javaDoc,"UTF-8");

		setBookmarkIds(html);

		
		// TODO convert internal references to bookmarks (id's of H1, H2 and H3
		// elements)

		// TODO images

		return html;
	}

	/**
	 * Gives all H1, H2 and H3 elements and unique ID so that they can be used as a book mark (internal or external reference)
	 * @param html
	 */
	private static void setBookmarkIds(Document html) {
		Elements elements = html.select("h1, h2, h3");
		String currentH1 = null;
		String currentH2=null;
		String currentH3=null;
		for (Element element : elements) {
			switch (element.tag().toString()) {
			case "h1":currentH1=createId(element);
				currentH2=null;
				currentH3=null;
				element.attr("id", currentH1);
				break;
			case "h2":
				currentH2=createId(element);
				currentH3=null;
				element.attr("id", new TitleBuilder("_").append(currentH1).append(currentH2).toString());
				break;
			case "h3":
				currentH3=createId(element);
				element.attr("id", new TitleBuilder("_").append(currentH1).append(currentH2).append(currentH3) .toString());
				break;
			} 
		}
	}

	private static String createId(Element element) {
		String title=element.text();
		String id = StringUtil.convertToCamelCase(title, true).replaceAll(NONE_WORD_CHARACTER, "");
		return id;
	}

	private static String replaceAllInlineTags(String javaDoc,
			Map<String, JavaFile> javaFiles) throws IOException {

		List<InlineTag> inlineTags = InlineTagFactory
				.getAllInlineTags(javaFiles);

		for (InlineTag inlineTag : inlineTags) {
			boolean foundMatch = true;
			while (foundMatch) {
				foundMatch = false;
				Matcher matcher = inlineTag.getRegex().toMatcher(javaDoc);
				if (matcher.find()) {
					foundMatch = true;
					int start = matcher.start();
					int end = matcher.end();
					String tag = javaDoc.substring(start, end);
					String replacement = inlineTag.getReplacementText(tag);
					javaDoc = javaDoc.substring(0, start) + replacement
							+ javaDoc.substring(end);
				}
			}
		}

		return javaDoc;
	}
}
