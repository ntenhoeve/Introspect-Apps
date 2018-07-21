package nth.reflect.app.swdocgen.dom.javadoc.tag;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

public class HtmlLinkToReference extends Element {

	public static final String HREF = "href";
	public static final String REFERENCE = "Reference_";
	public static final String ID = "id";

	public HtmlLinkToReference(String reference, String text) {
		super(Tag.valueOf("a"),"" );
		html(text);
		if (reference!=null) {
			attr(HREF, REFERENCE+reference);
		}
	}

}
