package nth.software.doc.generator.framer;

import java.util.List;

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
import nth.software.doc.generator.model.Text;
import nth.software.doc.generator.model.TextWithFixedWidthFont;
import nth.software.doc.generator.model.Underline;
import nth.software.doc.generator.model.inlinetag.InlineTag;
import nth.software.doc.generator.tokenizer.ElementName;
import nth.software.doc.generator.tokenizer.InlineTagName;

public class ConsoleFramer extends DocumentationFramer {

	public ConsoleFramer(DocumentationModel documentationModel) {
		super(documentationModel);
	}

	@Override
	public void frameChapter(Chapter chapter) {
		out("chapter", chapter.getTitle(), chapter);
	}

	private void out(String what, String title,
			NodeContainer<Node> nodeContainer) {
		out(what + "Start", title);
		List<Node> children = nodeContainer.getChildren();
		frameChildren(children);
		out(what + "End", title);
	}

	private void out(String what, NodeContainer<Node> nodeContainer) {
		out(what + "Start");
		List<Node> children = nodeContainer.getChildren();
		frameChildren(children);
		out(what + "End");
	}

	private void out(String what, String value) {
		System.out.println(what + ":" + value);
	}

	@Override
	public void frameText(Text textNode) {
		out("text", textNode.getText());

	}

	private void out(String string) {
		System.out.println(string);
	}

	@Override
	public void frameHyperlink(Hyperlink node) {
		out("hyperlink", node.getText() + "=" + node.getHref());
	}

	@Override
	public void frameParagarph(Paragraph paragraph) {
		out("paragraph", paragraph.getTitle(), paragraph);
	}

	@Override
	public void frameLineBreak(LineBreak node) {
		out("lineBreak");
	}

	@Override
	public void frameSubParagraph(SubParagraph subParagraph) {
		out("subParagraph", subParagraph.getTitle(), subParagraph);
	}

	@Override
	public void frameUnderline(Underline underline) {
		out("underline", underline);
	}

	@Override
	public void frameBold(Bold bold) {
		out("bold", bold);
	}

	@Override
	public void frameListItem(ListItem listItem) {
		out("listitem", listItem);
	}

	@Override
	public void frameList(nth.software.doc.generator.model.List list) {
		out("list", list);
	}

	@Override
	public void frameInlineTag(InlineTag inlineTag) {
		String value = inlineTag.getValue();
		if (value == null) {
			out(inlineTag.getName().toLowerCase());
		} else {
			out(inlineTag.getName().toLowerCase().toLowerCase(), value);
		}
	}

	@Override
	public void frameImage(Image image) {
		out(ElementName.IMG.toString(),image.getSrc());
	}

	@Override
	public void frameTextWithFixedWidthFont(TextWithFixedWidthFont node) {
		out(ElementName.PRE.toString(), node.getText());
	}

}
