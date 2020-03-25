package nth.reflect.apps.share.point.url.converter;

import java.awt.HeadlessException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class SharePointUrlConverter {

	public static void main(String[] commandLineArguments)
			throws HeadlessException, UnsupportedFlavorException, IOException {
		convert();
	}

	private static final String SHARED_DOCUMENTS = "Shared%20Documents";

	/**
	 * Converts a Share point Url that is on the clipboard, converts is to a
	 * reference that can be used in a document and puts it on the clipboard
	 * 
	 * @throws IOException
	 * @throws UnsupportedFlavorException
	 * @throws HeadlessException
	 * 
	 */
	public static void convert() throws HeadlessException, UnsupportedFlavorException, IOException {
		String url = Clipboard.get();

		String result = convert(url);

		Clipboard.put(result);
	}

	private static String convert(String url) throws UnsupportedEncodingException {
		String siteUrl = StringUtil.getTextUpUntil(url, SHARED_DOCUMENTS);

		String textAfter = StringUtil.getTextAfter(url, SHARED_DOCUMENTS);
		String encodedFolderPath = StringUtil.getTextAfter(textAfter, SHARED_DOCUMENTS);
		String decodedFolderPath = URLDecoder.decode(encodedFolderPath, "UTF-8");

		String result = siteUrl + decodedFolderPath;
		return result;
	}
}
