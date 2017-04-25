package nth.meyn.cx.sysmac.converter.cx.clipboard;

import nth.meyn.cx.sysmac.converter.util.ClipboardUtil;

public class CxClipboard {
	public static final String OMRON_CX_PROGRAMMER_2_0_RESOURCE_HANDLE_CLIPBOARD_FORMAT = "Omron CX-Programmer 2.0 Resource Handle Clipboard Format";
	public static final String OMRON_POU_LADDER_CLIP_BOARD_FORMAT = "OMRON POU Ladder ClipBoard Format";
	public static final String OMRON_CX_PROGRAMMER_2_0_LADDER_ELEMENT_CLIPBOARD_FORMAT = "Omron CX-Programmer 2.0 Ladder Element Clipboard Format";
	public static final String OMRON_CX_PROGRAMMER_2_0_EXTRACTED_GLOBAL_SYMBOLS_CLIPBOARD_FORMAT = "Omron CX-Programmer 2.0 Extracted GlobalSymbols Clipboard Format";
	public static final String OMRON_CX_PROGRAMMER_2_0_EXTRACTED_SYMBOLS_CLIPBOARD_FORMAT = "Omron CX-Programmer 2.0 Extracted Symbols Clipboard Format";

	public static String getLadderXml() {
		String xml = ClipboardUtil.getText(OMRON_POU_LADDER_CLIP_BOARD_FORMAT);
		if (xml==null) {
			throw new RuntimeException("There was no CX-One Ladder program on the clipboard.");
		}
		xml = convertToWellFormedXml(xml);
		return xml;
		// Set<DataFormat> contentTypes = clipboard.getContentTypes();
		// for (DataFormat dataFormat : contentTypes) {
		// Object clipboardContent =
		// Clipboard.getSystemClipboard().getContent(dataFormat);
		// if dataFormat.toString()) {
		//// case "[Omron CX-Programmer 2.0 Extracted Symbols Clipboard
		// Format]":
		//// extractedLocalSymbols = new
		// CxSymbolParser(toString(clipboardContent)).createCxSymbols();
		//// break;
		//// case "[Omron CX-Programmer 2.0 Extracted GlobalSymbols Clipboard
		// Format]":
		//// extractedGlobalSymbols = new
		// CxSymbolParser(toString(clipboardContent)).createCxSymbols();
		//// break;
		//// case "[Omron CX-Programmer 2.0 Ladder Element Clipboard Format]":
		//// List<CxLadderRung> cxLadder = new
		// CxLadderParserOld(toString(clipboardContent)).createCxLadder();
		//// System.out.println();
		// case "[OMRON POU Ladder ClipBoard Format]":
		// System.out.println(toString(clipboardContent));
		// CxLadderDiagram cxLadder = new
		// CxUnmarshaller().createCxLadder(toString(clipboardContent));
		// print(cxLadder);
		//
		// }
	}

	private static String convertToWellFormedXml(String xml) {
		xml = xml.replace("&", "&amp;").replace("<Name><", "<Name>&lt;").replace("<Name>>",
				"<Name>&gt;").replace("<Name><>",
						"<Name>&lt;&gt;");// TODO will need more work
		xml = trimCharactersAfterLastXmlElement(xml, "</LadderDiagram>");
		return xml;
	}

	private static String trimCharactersAfterLastXmlElement(String xml, String rootEndElement) {
		return xml.substring(0, xml.indexOf(rootEndElement) + rootEndElement.length());
	}


}
