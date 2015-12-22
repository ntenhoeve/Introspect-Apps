package nth.introspect.apps.docgenforjavaproj.dom.javadoc.tag;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

public class HtmlLinkToReference extends Element {

	public static final String REFERENCE = "Reference_";

	public HtmlLinkToReference(String reference, String text) {
		super(Tag.valueOf("a"),"" );
		html(text);
		if (reference!=null) {
			attr("href", REFERENCE+reference);
		}
	}

}
