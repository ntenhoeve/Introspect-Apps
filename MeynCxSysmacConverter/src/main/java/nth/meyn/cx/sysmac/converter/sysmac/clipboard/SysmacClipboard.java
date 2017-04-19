package nth.meyn.cx.sysmac.converter.sysmac.clipboard;

import java.nio.ByteBuffer;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import nth.meyn.cx.sysmac.converter.util.ClipboardUtil;

public class SysmacClipboard {

	public static final String LADDER_SNIPPET_ASSIGNED_VARIABLES = "ladderSnippetAssignedVariables";
	public static final String LADDER_SNIPPET_XML = "ladderSnippetXML";
	public static final String VARIABLE_NAMES = "VariableNames";

	public static String getLadderData() {
		String ladderData = ClipboardUtil.getText(LADDER_SNIPPET_XML);
		return ladderData;
	}


	public static String getSymbolData() {
		String symbolData = ClipboardUtil.getText(LADDER_SNIPPET_ASSIGNED_VARIABLES);
		return symbolData;

	}

	

	public static void putLadderRungs(String sysmacLadderData, String sysmacSymbolData) {
		Clipboard clipboard = Clipboard.getSystemClipboard();
		clipboard.clear();
		ClipboardContent clipboardContent = new ClipboardContent();
		DataFormat ladderSnippetXmlDataFormat = new DataFormat(LADDER_SNIPPET_XML);
		clipboardContent.put(ladderSnippetXmlDataFormat,
				ByteBuffer.wrap(sysmacLadderData.getBytes()));
		DataFormat ladderSnippetVariablesDataFormat = new DataFormat(
				LADDER_SNIPPET_ASSIGNED_VARIABLES);
		clipboardContent.put(ladderSnippetVariablesDataFormat,
				ByteBuffer.wrap(sysmacSymbolData.getBytes()));
		clipboard.setContent(clipboardContent);
	}


	/**
	 * trims everything for and after the root element
	 * @param sysmacLadderClipboardData
	 * @return 
	 */
	public static String getXml(String sysmacLadderClipboardData) {
		String xmlWithSuffix = removeBeforeFirst(sysmacLadderClipboardData, "<Rungs>");
		String xml= removeAfterLast(xmlWithSuffix,"</Rungs>");
		return xml;
	}


	private static String removeAfterLast(String text, String textToFind) {
		int pos=text.lastIndexOf(textToFind);
		if (pos>0) {
			return text.substring(0,pos+textToFind.length());
		} else {
			return text;
		}
	}


	private static String removeBeforeFirst(String text, String textToFind) {
		int pos=text.indexOf(textToFind);
		if (pos>0) {
			return text.substring(pos);
		} else {
			return text;
		}
	}
}
