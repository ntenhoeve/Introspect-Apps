package nth.github.page.generator.dom.text.service;

import java.util.ArrayList;
import java.util.List;

import nth.github.page.generator.model.text.TextChapterLevel1;
import nth.github.page.generator.model.text.TextHyperLink;
import nth.github.page.generator.model.text.TextNode;
import nth.github.page.generator.model.text.TextNodeContainer;
import nth.github.page.generator.model.text.TextText;
import nth.github.page.generator.model.text.ToDo;

public class StringDocumentFactory {

	private static final String INDENT = " ";
	private static final String NEW_LINE = "\n";

	public static String create(TextNode node) {
		List<String> lines = print(node);
		StringBuilder builder = new StringBuilder();
		for (String line : lines) {
			builder.append(line);
			builder.append(NEW_LINE);
		}
		return builder.toString();
	}

	private static List<String> print(TextNode node) {
		List<String> lines = new ArrayList<>();

		StringBuilder line = new StringBuilder();
		line.append(node.getClass().getSimpleName());
		line.append("(");

		if (node instanceof TextChapterLevel1) {
			TextChapterLevel1 chapter = (TextChapterLevel1) node;
			line.append("title=");
			line.append(chapter.getTitle());
		} else if (node instanceof TextText) {
			TextText text = (TextText) node;
			line.append("text=");
			line.append(text.getText());
		}else if (node instanceof TextHyperLink) {
			TextHyperLink hyperLink= (TextHyperLink) node;
			line.append("text=");
			line.append(hyperLink.getText());
			line.append(" uri=");
			line.append(hyperLink.getUri());
		}else if (node instanceof ToDo) {
			ToDo toDo = (ToDo) node;
			line.append("text=");
			line.append(toDo.getText());
		}
		
		
		// add children if any
		if (node instanceof TextNodeContainer && ((TextNodeContainer)node).getChilderen().size()>0) {
			lines.add(line.toString());
			TextNodeContainer nodeContainer = (TextNodeContainer) node;
			for (TextNode child : nodeContainer.getChilderen()) {
				// recursive call to print children
				List<String> childLines = print(child);
				for (String childLine : childLines) {
					lines.add(INDENT + childLine);
				}
			}
			//close node
			lines.add(")");
		} else {
			//close node on the same line
			line.append(")");	
			lines.add(line.toString());
		}

		return lines;
	}

}
