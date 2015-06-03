package nth.software.doc.generator.framer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import nth.software.doc.generator.model.Bold;
import nth.software.doc.generator.model.Chapter;
import nth.software.doc.generator.model.DocumentationModel;
import nth.software.doc.generator.model.Hyperlink;
import nth.software.doc.generator.model.Image;
import nth.software.doc.generator.model.LineBreak;
import nth.software.doc.generator.model.List;
import nth.software.doc.generator.model.ListItem;
import nth.software.doc.generator.model.Node;
import nth.software.doc.generator.model.Paragraph;
import nth.software.doc.generator.model.SubParagraph;
import nth.software.doc.generator.model.TextNode;
import nth.software.doc.generator.model.Underline;
import nth.software.doc.generator.model.inlinetag.InlineTag;
import nth.software.doc.generator.service.DocumentationInfo;
import nth.software.doc.generator.tokenizer.ElementName;

public class WikiFramer extends HtmlSingleFileFramer {

	private final File destinationFolder;
	private int chapterNumber = 0;

	public WikiFramer(DocumentationModel documentationModel,
			DocumentationInfo htmlInfo, File destinationFolder)
			throws FileNotFoundException, UnsupportedEncodingException {
		super(documentationModel, htmlInfo, destinationFolder);
		this.destinationFolder = destinationFolder;
	}

	@Override
	public void onStartFraming() {
		try {
			createNewFile("Home.md");
			writeImage();
			writeTableOfContents();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}


	@Override
	public String createChapterLink(Chapter chapterOrParagraphOrSubParagraph) {
		StringBuilder link = new StringBuilder();
		Chapter chapter=documentationModel.findChapter(chapterOrParagraphOrSubParagraph);
		java.util.List<Chapter> chapters=documentationModel.findChapters();
		int chapterNr = chapters.indexOf(chapter)+1;
		if (chapterNr<10) {
			link.append("0");
		}
		link.append(chapterNr);
		link.append("-");
		link.append(chapter.getTitle().replace(" ", "-"));
		if (chapterOrParagraphOrSubParagraph instanceof Paragraph) {
			link.append("#");
			link.append(chapterOrParagraphOrSubParagraph.getTitle().toLowerCase().replace(" ", "-"));
		}
		return link.toString();
	}

	

	@Override
	public void frameChapter(Chapter chapter) {
		chapterNumber++;
		String fileName = createChapterFileName(chapter);
		try {
			createNewFile(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		super.frameChapter(chapter);
	}

	private String createChapterFileName(Chapter chapter) {
		StringBuilder fileName = new StringBuilder();
		String chapterTitle = chapter.getTitle();
		if (chapterNumber<10) {
			fileName.append("0");
		}
		fileName.append(chapterNumber);
		fileName.append("-");
		fileName.append(chapterTitle.replace(" ", "-"));
		fileName.append(".md");
		return fileName.toString();
	}


	
}
