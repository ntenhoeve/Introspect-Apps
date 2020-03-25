package nth.reflect.apps.share.point.url.converter;

public class StringUtil {

	public static String getTextUpUntil(String source, String textToFind) {
		if (source == null) {
			return null;
		}
		int beginPos = source.indexOf(textToFind);
		if (beginPos < 0) {
			return source;
		}
		int endPos = beginPos + textToFind.length();
		return source.substring(0, endPos);
	}

	public static String getTextAfter(String source, String textToFind) {
		if (source == null) {
			return null;
		}
		int beginPos = source.indexOf(textToFind);
		if (beginPos < 0) {
			return source;
		}
		int endPos = beginPos + textToFind.length();
		return source.substring(endPos);
	}

}
