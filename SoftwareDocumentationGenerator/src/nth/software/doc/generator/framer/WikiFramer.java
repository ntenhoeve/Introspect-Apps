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
import nth.software.doc.generator.tokenizer.ElementName;

public class WikiFramer extends DocumentationFramer {


	private final File destinationFolder;
	private PrintWriter writer;

	public WikiFramer(DocumentationModel documentationModel, File destinationFolder) throws FileNotFoundException, UnsupportedEncodingException {
		super(documentationModel);
		this.destinationFolder = destinationFolder;
	}
	

	@Override
	public void onStartFraming()  {
		try {
			writeHomePage();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onCloseFraming() {
		writer.flush();
		writer.close();
	}

	
	private void writeHomePage() throws FileNotFoundException, UnsupportedEncodingException {
		createNewFile("Home");
		writeHeaderBar();
		writeImage();
		writeTableOfContents();
		writeBottomBar();
	}

	private void writeBottomBar() {
		// TODO Auto-generated method stub
		
	}


	private void writeHeaderBar() {
		// TODO Auto-generated method stub
		
	}


	private void writeImage() {
		InlineTag beginOffFile = documentationModel.findBeginOffFile(documentationModel);
		String javaFileName = beginOffFile.getValue();
		File projectsFolder=documentationModel.getJavaProjectsFolder();
		String imageFileName=javaFileName+".png";
		frameImage(projectsFolder, javaFileName, imageFileName);
	}

	private void frameImage(File projectsFolder, String javaFileName,
			String imageFileName) {
		// TODO Auto-generated method stub
		
	}


	private void writeTableOfContents() {
		writer.print("Contents");
		for (Node node : documentationModel.getChildren()) {
			if (node instanceof Chapter) {
				Chapter chapter = (Chapter) node;
				writer.print("### [");
				writer.print(chapter.getTitle());
				writer.print("](");
				writer.print(createReference(chapter));
				writer.print(")");
				java.util.List<Node> children = chapter.getChildren();
				for (Node child : children) {
					if (child instanceof Paragraph) {
						Paragraph paragraph = (Paragraph) child;
						writer.print("### [");
						writer.print(paragraph.getTitle());
						writer.print("](");
						writer.print(createReference(paragraph));
						writer.print(")");
					}
				}

			}
		}
	}
		

	private char[] createReference(Chapter chapter) {
		// TODO Auto-generated method stub
		return null;
	}


	private void createNewFile(String fileName) throws FileNotFoundException, UnsupportedEncodingException {
		StringBuilder filePath = new StringBuilder();
		filePath.append(destinationFolder.getAbsolutePath());
		filePath.append("/");
		filePath.append(fileName);
		filePath.append(".md");
		File file=new File(filePath.toString());
		writer= new PrintWriter(file, "UTF-8");
	}




	@Override
	public void frameImage(Image node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void frameUnderline(Underline node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void frameBold(Bold node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void frameListItem(ListItem node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void frameList(List node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void frameChapter(Chapter chapter) {
		// TODO Auto-generated method stub

	}

	@Override
	public void frameParagarph(Paragraph node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void frameSubParagraph(SubParagraph node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void frameInlineTag(InlineTag inlineTag) {
		// TODO Auto-generated method stub

	}

	@Override
	public void frameText(TextNode node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void frameLineBreak(LineBreak node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void frameHyperlink(Hyperlink node) {
		// TODO Auto-generated method stub

	}

}
