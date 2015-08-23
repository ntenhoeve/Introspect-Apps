package nth.software.doc.generator.framer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.List;

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
import nth.software.doc.generator.model.SubChapter;
import nth.software.doc.generator.model.SubSubChapter;
import nth.software.doc.generator.model.Text;
import nth.software.doc.generator.model.TextBlock;
import nth.software.doc.generator.model.TextWithFixedWidthFont;
import nth.software.doc.generator.model.Underline;
import nth.software.doc.generator.model.inlinetag.InlineTag;
import nth.software.doc.generator.service.DocumentationInfo;
import nth.software.doc.generator.tokenizer.ElementName;
import nth.software.doc.generator.tokenizer.TokenFactory;
import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;
/**
 * TODO For HTML: make it more like Wiki framer: split up in multiple files, extend sitemap, implement a title bar See {@link HtmlTitleBar} for inspiration 
 * @author nilsth
 *
 */
public class HtmlSingleFileFramer extends DocumentationFramer {

	
	
	private static final int NOT_FOUND = -1;
	public static final String FILE_NAME = "index.html";
	private PrintWriter writer;
	private File destinationFolder;

	public HtmlSingleFileFramer(DocumentationModel documentationModel,
			DocumentationInfo htmlInfo, File destinationFolder)
			throws FileNotFoundException, UnsupportedEncodingException {
		super(documentationModel);
		this.destinationFolder = destinationFolder;
	}

	private void outChildren(NodeContainer<Node> nodeContainer) {
		List<Node> childern = nodeContainer.getChildren();
		frameChildren(childern);
	}

	private void outEndElement(ElementName elementName) {
		getWriter().print("</");
		getWriter().print(elementName.toLowerCase());
		getWriter().print(">");
	}

	protected void outText(String text) {
		String html = escapeHtml4(text);
		getWriter().print(html);
	}

	private void outStartElement(ElementName elementName) {
		getWriter().print("<");
		getWriter().print(elementName.toLowerCase());
		getWriter().print(">");
	}

	private void outStartElement(ElementName elementName, String attributeName,
			String attributeValue) {
		getWriter().print("<");
		getWriter().print(elementName.toLowerCase());
		getWriter().print(" ");
		getWriter().print(attributeName);
		getWriter().print("=\"");
		getWriter().print(attributeValue);
		getWriter().print("\"");
		getWriter().print(">");
	}

	@Override
	public void onCloseFraming() {
		closeCurrentFile();
	}

	@Override
	public void onStartFraming() {
		try {
			createNewFile(FILE_NAME);
			writeImage();
			writeTableOfContents();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	protected void createNewFile(String fileName) throws FileNotFoundException,
			UnsupportedEncodingException {
		if (getWriter() != null) {
			closeCurrentFile();
		}
		StringBuilder filePath = new StringBuilder();
		filePath.append(destinationFolder.getAbsolutePath());
		filePath.append("/");
		filePath.append(fileName);
		File file = new File(filePath.toString());
		setWriter(new PrintWriter(file, "UTF-8"));
	}

	private void closeCurrentFile() {
		getWriter().flush();
		getWriter().close();
	}

	protected void writeImage() {
		InlineTag beginOffFile = documentationModel
				.findBeginOffFile(documentationModel);
		String javaFileName = beginOffFile.getValue();
		File projectsFolder = documentationModel.getJavaProjectsFolder();
		String imageFileName = javaFileName + ".png";
		frameImage(projectsFolder, javaFileName, imageFileName);
	}

	protected void writeTableOfContents() {
		outStartElement(ElementName.H1);
		outText("Contents");
		outEndElement(ElementName.H1);
		for (Node node : documentationModel.getChildren()) {
			if (node instanceof Chapter) {
				Chapter chapter = (Chapter) node;
				outStartElement(ElementName.H3);
				outStartElement(ElementName.A, "href",
						createChapterLink(chapter));
				outText(chapter.getTitle());
				outEndElement(ElementName.A);
				outEndElement(ElementName.H3);
				outStartElement(ElementName.UL);
				List<Node> children = chapter.getChildren();
				for (Node child : children) {
					if (child instanceof SubChapter) {
						SubChapter subChapter = (SubChapter) child;
						outStartElement(ElementName.LI);
						outStartElement(ElementName.A, "href",
								createChapterLink(subChapter));
						outText(subChapter.getTitle());
						outEndElement(ElementName.A);
						outEndElement(ElementName.LI);
					}
				}
				outEndElement(ElementName.UL);
			}
		}
	}

	@Override
	public void frameText(Text textNode) {
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
	public void frameSubChapter(SubChapter subChapter) {
		outStartElement(ElementName.A, "name", subChapter.getTitle());
		outStartElement(ElementName.H2);
		outText(subChapter.getTitle());
		outEndElement(ElementName.H2);
		outEndElement(ElementName.A);
		outChildren(subChapter);
	}

	@Override
	public void frameSubSubChapter(SubSubChapter subSubChapter) {
		outStartElement(ElementName.H3);
		outText(subSubChapter.getTitle());
		outEndElement(ElementName.H3);
		outChildren(subSubChapter);
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

			Node chapterOrSubChapter = documentationModel
					.findChapterOrSubChapterWithBeginOfFileTag(tagName);
			String hRef = null;
			if (chapterOrSubChapter == null) {
				System.out.println(text);
				outStartElement(ElementName.A);
			} else {
				Chapter chapter = (Chapter) chapterOrSubChapter;
				hRef = createChapterLink(chapter);
				outStartElement(ElementName.A, "href", hRef);
			}
			outText(text);
			outEndElement(ElementName.A);
			break;
		default:
			break;
		}
	}

	public String createChapterLink(Chapter chapter) {
		return "#" + chapter.getTitle();
	}

	@Override
	public void frameImage(Image image) {
		File projectsFolder = documentationModel.getJavaProjectsFolder();
		InlineTag beginOffFile = documentationModel
				.findBeginOffFileInParent(image);
		String JavaFileName = beginOffFile.getValue();
		String imageFileName = image.getSrc();
		frameImage(projectsFolder, JavaFileName, imageFileName);
	}

	private void frameImage(File projectsFolder, String JavaFileName,
			String imageFileName) {
		try {
			JavaFile javaFile = JavaFileFactory.create(projectsFolder,
					JavaFileName);
			File imageFile = javaFile.getResourcePath(imageFileName);
			copyImage(imageFile);
			outStartElement(ElementName.IMG, "src", imageFileName);
		} catch (FileNotFoundException | MultipleFileException e) {
			e.printStackTrace();
		}
	}

	private void copyImage(File imageFile) {
		try {
			StringBuilder destinationFile = new StringBuilder();
			destinationFile.append(destinationFolder);
			destinationFile.append("/");
			destinationFile.append(imageFile.getName());
			FileOutputStream out = new FileOutputStream(
					destinationFile.toString());
			Files.copy(imageFile.toPath(), out);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public PrintWriter getWriter() {
		return writer;
	}

	public void setWriter(PrintWriter writer) {
		this.writer = writer;
	}

	@Override
	public void frameTextWithFixedWidthFont(TextWithFixedWidthFont node) {
		outStartElement(ElementName.PRE);
		outText(node.getText());
		outEndElement(ElementName.PRE);
	}

	@Override
	public void frameTextBlock(TextBlock textBlock) {
		outStartElement(ElementName.P);
		outChildren(textBlock);
		outEndElement(ElementName.P);

	}

}
