package nth.meyn.cx.sysmac.converter.util;

import java.nio.ByteBuffer;
import java.util.Set;

import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;

public class ClipboardUtil {

	public static String getText(String mimeType) {
		mimeType="["+mimeType+"]";
		Clipboard clipboard = Clipboard.getSystemClipboard();
		Set<DataFormat> contentTypes = clipboard.getContentTypes();
		for (DataFormat dataFormat : contentTypes) {
			if (dataFormat.toString().equals(mimeType)) {
				Object clipboardContent = clipboard.getContent(dataFormat);
				ByteBuffer byteBuffer = (ByteBuffer) clipboardContent;
				String text = new String(byteBuffer.array());
				return text;
			}
		}
		return null;
	}

}
