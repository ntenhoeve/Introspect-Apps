package nth.meyn.cx.sysmac.converter;

import java.nio.ByteBuffer;

public class MeynCxSysmacClipboardPrinter {// extends Application {
	public static void main(String[] args) {
//		launch(args);
	}

//	@Override
//	public void start(Stage primaryStage) throws Exception {
//
//		Clipboard clipboard = Clipboard.getSystemClipboard();
//		Set<DataFormat> contentTypes = clipboard.getContentTypes();
//		for (DataFormat dataFormat : contentTypes) {
//			System.out.println("---");
//			System.out.println(dataFormat);
//
//			String dataFormatId = getDataFormatId(dataFormat);
//			switch (dataFormatId) {
//			case CxClipboard.OMRON_CX_PROGRAMMER_2_0_EXTRACTED_SYMBOLS_CLIPBOARD_FORMAT:
//			case CxClipboard.OMRON_CX_PROGRAMMER_2_0_EXTRACTED_GLOBAL_SYMBOLS_CLIPBOARD_FORMAT:
//			case CxClipboard.OMRON_CX_PROGRAMMER_2_0_LADDER_ELEMENT_CLIPBOARD_FORMAT:
//			case CxClipboard.OMRON_POU_LADDER_CLIP_BOARD_FORMAT:
//			case CxClipboard.OMRON_CX_PROGRAMMER_2_0_RESOURCE_HANDLE_CLIPBOARD_FORMAT:
//			case SysmacClipboard.LADDER_SNIPPET_XML :
//			case SysmacClipboard.LADDER_SNIPPET_ASSIGNED_VARIABLES :
//			case SysmacClipboard.VARIABLE_NAMES:	
//				Object clipboardContent = clipboard.getContent(dataFormat);
//				print(clipboardContent);
//				System.out.println(StringToArrayCodeUtil.print((ByteBuffer) clipboardContent));
//				break;
//			default:
//				break;
//			}
//		}
//		Platform.exit();
//	}

//	private String getDataFormatId(DataFormat dataFormat) {
//		String dataFormatId = dataFormat.toString();
//		dataFormatId = StringUtils.removeStart(dataFormatId, "[");
//		dataFormatId = StringUtils.removeEnd(dataFormatId, "]");
//		return dataFormatId;
//	}

	private void print(Object clipboardContent) {
		ByteBuffer byteBuffer = (ByteBuffer) clipboardContent;
		String text = new String(byteBuffer.array());
		System.out.println(text);
	}

//	private void putSysmacLadderOnClipBoard(String ladderSnippetVariablesContent,
//			String ladderSnippetXmlContent) {
//		Clipboard clipboard = Clipboard.getSystemClipboard();
//		clipboard.clear();
//		ClipboardContent clipboardContent = new ClipboardContent();
//		DataFormat ladderSnippetXmlDataFormat = DataFormat.lookupMimeType(LADDER_SNIPPET_XML);
//		clipboardContent.put(ladderSnippetXmlDataFormat,
//				ByteBuffer.wrap(ladderSnippetXmlContent.getBytes()));
//		DataFormat ladderSnippetVariablesDataFormat = DataFormat
//				.lookupMimeType(LADDER_SNIPPET_ASSIGNED_VARIABLES);
//		clipboardContent.put(ladderSnippetVariablesDataFormat,
//				ByteBuffer.wrap(ladderSnippetVariablesContent.getBytes()));
//		clipboard.setContent(clipboardContent);
//	}

}
