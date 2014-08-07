package nth.github.page.generator.dom.text.service;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.net.URI;
import java.util.List;

import nth.github.page.generator.Config;
import nth.github.page.generator.model.text.TextChapterLevel1;
import nth.github.page.generator.model.text.TextChapterLevel2;
import nth.github.page.generator.model.text.TextChapterLevel3;
import nth.github.page.generator.model.text.TextDocument;
import nth.github.page.generator.model.text.TextHyperLink;
import nth.github.page.generator.model.text.TextList;
import nth.github.page.generator.model.text.TextListItem;
import nth.github.page.generator.model.text.TextNode;
import nth.github.page.generator.model.text.TextNodeContainer;
import nth.github.page.generator.model.text.TextText;
import nth.github.page.generator.model.text.ToDo;

import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHyperlink;
import org.apache.poi.xwpf.usermodel.XWPFHyperlinkRun;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class TextDocumentFactory {

	private static TextChapterLevel1 currentChapter;
	private static TextChapterLevel1 chapterLevel1;
	private static TextChapterLevel2 chapterLevel2;

	public TextDocument createTextDocument(Config config) {
		try {
			// TODO move file to config
			File file = config.getWordDocumentLocation();
			FileInputStream fis = new FileInputStream(file);

			XWPFDocument wordDocument = new XWPFDocument(fis);

			// create our own model from the POI word model
			TextDocument textDocument = createTextDocument(wordDocument);

			return textDocument;
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}

	/**
	 * @param wordDocument
	 *            a POI model which represents a word file
	 * @return our own model (created from the POI word model)
	 */
	public static TextDocument createTextDocument(XWPFDocument wordDocument) {
		List<XWPFParagraph> paragraphs = wordDocument.getParagraphs();

		TextDocument textDoc = new TextDocument();

		for (XWPFParagraph paragraph : paragraphs) {

			BigInteger listId = paragraph.getNumID();
			boolean isList = listId != null;
			if (isList) {
				TextList textList = findList(textDoc, listId);
				if (textList == null) {
					textList = new TextList(listId);
					currentChapter.getChilderen().add(textList);
				}
				TextListItem listItem = new TextListItem();
				textList.getChilderen().add(listItem);
				addWordParagraphToTextNode(wordDocument, paragraph, listItem);
			} else if (paragraph.getStyle() == null) {
				addWordParagraphToTextNode(wordDocument, paragraph,
						currentChapter);
			} else {
				switch (paragraph.getStyle()) {
				case "Heading1":
					chapterLevel1 = new TextChapterLevel1(paragraph);
					if (!chapterLevel1.getTitle().toLowerCase().equals("index")) {
						textDoc.getChilderen().add(chapterLevel1);
						currentChapter = chapterLevel1;
					}
					break;
				case "Heading2":
					chapterLevel2 = new TextChapterLevel2(paragraph);
					chapterLevel1.getChilderen().add(chapterLevel2);
					currentChapter = chapterLevel2;
					break;
				case "Heading3":
					TextChapterLevel3 chapterLevel3 = new TextChapterLevel3(
							paragraph);
					chapterLevel2.getChilderen().add(chapterLevel3);
					currentChapter = chapterLevel3;
					break;
				default:
					break;
				}
			}
		}

		return textDoc;

	}

	private static TextList findList(TextDocument textDoc, BigInteger listId) {
		List<TextNode> lists = textDoc.findChilderenOfType(TextList.class);
		for (TextNode node : lists) {
			TextList textList = (TextList) node;
			if (textList.getId().equals(listId)) {
				return textList;
			}
		}
		return null;
	}

	public static void addWordParagraphToTextNode(XWPFDocument wordDocument,
			XWPFParagraph paragraph, TextNodeContainer nodeContainer) {
		if (nodeContainer == null) {
			return;
		}

		List<XWPFRun> runs = paragraph.getRuns();
		for (XWPFRun run : runs) {
			if (run instanceof XWPFHyperlinkRun) {
				XWPFHyperlinkRun hyperlinkRun = (XWPFHyperlinkRun) run;
				String text = hyperlinkRun.getText(0);
				if ("annotated".equals(text)) {
TODO					System.out.println("!!!!");
				}
				String id = hyperlinkRun.getHyperlinkId();
				String uriString = null;
				if (id != null) {
					PackageRelationship relationship = wordDocument
							.getPackagePart().getRelationship(id);
					URI uri = relationship.getTargetURI();
					if (uri != null) {
						uriString = uri.toString();
					}
				}
				TextHyperLink hyperLink = new TextHyperLink(text, uriString);
				nodeContainer.getChilderen().add(hyperLink);
			} else {
				String text = run.getText(0);
				if (text != null && text.contains("TODO")) {
					ToDo toDo = new ToDo(text);
					nodeContainer.getChilderen().add(toDo);
				} else {
					TextText textNode = new TextText(text);
					nodeContainer.getChilderen().add(textNode);
				}
			}
		}
		if (!(nodeContainer instanceof TextListItem)) {
			TextText text = new TextText(null);// close paragraph with new line
			nodeContainer.getChilderen().add(text);
		}
	}

}
