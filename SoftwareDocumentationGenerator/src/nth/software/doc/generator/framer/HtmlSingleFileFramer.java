package nth.software.doc.generator.framer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilePermission;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.List;

import com.sun.tools.doclets.internal.toolkit.resources.doclets;

import nth.software.doc.generator.javafile.JavaFile;
import nth.software.doc.generator.javafile.JavaFileFactory;
import nth.software.doc.generator.javafile.MultipleFileException;
import nth.software.doc.generator.model.Bold;
import nth.software.doc.generator.model.Chapter;
import nth.software.doc.generator.model.DocumentationModel;
import nth.software.doc.generator.model.Hyperlink;
import nth.software.doc.generator.model.Image;
import nth.software.doc.generator.model.LineBreak;
import nth.software.doc.generator.model.ListItem;
import nth.software.doc.generator.model.Node;
import nth.software.doc.generator.model.NodeContainer;
import nth.software.doc.generator.model.Paragraph;
import nth.software.doc.generator.model.SubParagraph;
import nth.software.doc.generator.model.TextNode;
import nth.software.doc.generator.model.Underline;
import nth.software.doc.generator.model.inlinetag.InlineTag;
import nth.software.doc.generator.tokenizer.ElementName;
import nth.software.doc.generator.tokenizer.InlineTagName;
import nth.software.doc.generator.tokenizer.TokenFactory;

public class HtmlSingleFileFramer extends DocumentationFramer {

	private static final int NOT_FOUND = -1;
	private final PrintWriter writer;
	private final File destination;

	public HtmlSingleFileFramer(DocumentationModel documentationModel,
			File destination) throws FileNotFoundException,
			UnsupportedEncodingException {
		super(documentationModel);
		this.destination = destination;
		writer = new PrintWriter(destination, "UTF-8");
	}

	private void outChildren(NodeContainer<Node> nodeContainer) {
		List<Node> childern = nodeContainer.getChildren();
		frameChildren(childern);
	}

	private void outEndElement(ElementName elementName) {
		writer.print("</");
		writer.print(elementName.toLowerCase());
		writer.print(">");
	}

	private void outText(String text) {
		String html = HtmlConverter.convertToHtml(text);
		writer.print(html);
	}

	private void outStartElement(ElementName elementName) {
		writer.print("<");
		writer.print(elementName.toLowerCase());
		writer.print(">");
	}

	private void outStartElement(ElementName elementName, String attributeName,
			String attributeValue) {
		writer.print("<");
		writer.print(elementName.toLowerCase());
		writer.print(" ");
		writer.print(attributeName);
		writer.print("=\"");
		writer.print(attributeValue);
		writer.print("\"");
		writer.print(">");
	}

	@Override
	public void onCloseFraming() {
		writer.flush();
		writer.close();
	}

	@Override
	public void onStartFraming() {
		writeImage();
		writeTableOfContents();
	}

	private void writeImage() {
		InlineTag beginOffFile = documentationModel.findBeginOffFile(documentationModel);
		String javaFileName = beginOffFile.getValue();
		File projectsFolder=documentationModel.getJavaProjectsFolder();
		String imageFileName=javaFileName+".png";
		frameImage(projectsFolder, javaFileName, imageFileName);
	}

	private void writeTableOfContents() {
		outStartElement(ElementName.H1);
		outText("Contents");
		outEndElement(ElementName.H1);
		for (Node node : documentationModel.getChildren()) {
			if (node instanceof Chapter) {
				Chapter chapter = (Chapter) node;
				outStartElement(ElementName.H3);
				outStartElement(ElementName.A, "href", "#" + chapter.getTitle());
				outText(chapter.getTitle());
				outEndElement(ElementName.A);
				outEndElement(ElementName.H3);
				outStartElement(ElementName.UL);
				List<Node> children = chapter.getChildren();
				for (Node child : children) {
					if (child instanceof Paragraph) {
						Paragraph paragraph = (Paragraph) child;
						outStartElement(ElementName.LI);
						outStartElement(ElementName.A, "href",
								"#" + paragraph.getTitle());
						outText(paragraph.getTitle());
						outEndElement(ElementName.A);
						outEndElement(ElementName.LI);
					}
				}
				outEndElement(ElementName.UL);
			}
		}
	}

	@Override
	public void frameText(TextNode textNode) {
		outText(textNode.getText());
	}

	@Override
	public void frameHyperlink(Hyperlink hyperlink) {
		outStartElement(ElementName.A, "href", hyperlink.getHref());
		outText(hyperlink.getText());
		outEndElement(ElementName.A);
	}

	@Override
	public void frameChapter(Chapter chapter) {
		outStartElement(ElementName.A, "name", chapter.getTitle());
		outStartElement(ElementName.H1);
		outText(chapter.getTitle());
		outEndElement(ElementName.H1);
		outEndElement(ElementName.A);
		outChildren(chapter);
	}

	@Override
	public void frameParagarph(Paragraph paragraph) {
		outStartElement(ElementName.A, "name", paragraph.getTitle());
		outStartElement(ElementName.H2);
		outText(paragraph.getTitle());
		outEndElement(ElementName.H2);
		outEndElement(ElementName.A);
		outChildren(paragraph);
	}

	@Override
	public void frameSubParagraph(SubParagraph subParagraph) {
		outStartElement(ElementName.H3);
		outText(subParagraph.getTitle());
		outEndElement(ElementName.H3);
		outChildren(subParagraph);
	}

	@Override
	public void frameLineBreak(LineBreak node) {
		outStartElement(ElementName.BR);
	}

	@Override
	public void frameUnderline(Underline underline) {
		outStartElement(ElementName.U);
		outChildren(underline);
		outEndElement(ElementName.U);
	}

	@Override
	public void frameBold(Bold bold) {
		outStartElement(ElementName.B);
		outChildren(bold);
		outEndElement(ElementName.B);
	}

	@Override
	public void frameListItem(ListItem listItem) {
		outStartElement(ElementName.LI);
		outChildren(listItem);
		outEndElement(ElementName.LI);
	}

	@Override
	public void frameList(nth.software.doc.generator.model.List list) {
		outStartElement(ElementName.UL);
		outChildren(list);
		outEndElement(ElementName.UL);

	}

	/**
	 * See {@link TokenFactory#createInlineTagTokens()}
	 */
	@Override
	public void frameInlineTag(InlineTag inlineTag) {
		switch (inlineTag.getName()) {
		case LINK:
		case LINKPLAIN:
			String text = inlineTag.getValue().replace("#", ".").trim();
			int endPos = text.indexOf(".");
			if (endPos == NOT_FOUND) {
				endPos = text.length();
			}
			String tagName = text.substring(0, endPos);

			Node chapterOrParagraph = documentationModel
					.findChapterOrParagraphWithBeginOfFileTag(tagName);
			String hRef = null;
			if (chapterOrParagraph == null) {
				outStartElement(ElementName.A);
			} else {
				Chapter chapter = (Chapter) chapterOrParagraph;
				hRef = "#" + chapter.getTitle();
				outStartElement(ElementName.A, "href", hRef);
			}
			outText(text);
			outEndElement(ElementName.A);
			break;
		default:
			break;
		}
	}

	@Override
	public void frameImage(Image image) {
		File projectsFolder = documentationModel.getJavaProjectsFolder();
		InlineTag beginOffFile = documentationModel.findBeginOffFileInParent(image);
		String JavaFileName = beginOffFile.getValue();
		String imageFileName = image.getSrc();
		frameImage(projectsFolder, JavaFileName, imageFileName);
	}

	private void frameImage(File projectsFolder, String JavaFileName,
			String imageFileName) {
		try {
			JavaFile javaFile = JavaFileFactory.create(projectsFolder, JavaFileName);
			File imageFile = javaFile.getResourcePath(imageFileName);
			copyImage(imageFile);
			outStartElement(ElementName.IMAGE, "src", imageFileName);
		} catch (FileNotFoundException | MultipleFileException e) {
			e.printStackTrace();
		}
	}

	private void copyImage(File imageFile) {
		try {
			String destinationFile = destination.getParent() + "/"
					+ imageFile.getName();
			FileOutputStream out = new FileOutputStream(destinationFile);
			Files.copy(imageFile.toPath(), out);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
