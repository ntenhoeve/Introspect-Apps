package nth.software.doc.generator.framer;

/**
 * Replace "<" with "&lt;" Replace "&" with "&amp;" All other characters < 128
 * should be left as they are. All other characters >= 128 should be written as
 * "&#"+((int)myChar)+";", to make them readable in every encoding.
 * 
 * @author nilsth
 * 
 */
public class HtmlConverter {
	private static final String LESS_THAN_HTML = "&lt;";
	private static final String LESS_THAN_TEXT = "<";
	private static final String GREATHER_THAN_HTML = "&gt;";
	private static final String GREATHER_THAN_TEXT = ">";

	public static String convertToHtml(String text) {
		String html ;
		html= text.replace(LESS_THAN_TEXT, LESS_THAN_HTML);
		html= html.replace(GREATHER_THAN_TEXT, GREATHER_THAN_HTML);
		for (char ch : text.toCharArray()) {
			if (ch>=128) {
				html=html.replace(Character.toString(ch), "&#"+((int)ch)+";");
			}
		}
		return html;
	}
}
