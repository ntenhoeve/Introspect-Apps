package nth.software.doc.generator.model;

import java.io.File;
import java.util.List;

import nth.software.doc.generator.model.inlinetag.InlineTag;
import nth.software.doc.generator.tokenizer.ElementName;
import nth.software.doc.generator.tokenizer.InlineTagName;

public class DocumentationModel extends NodeContainer<Node> {

	private final File javaProjectsFolder;

	public DocumentationModel(File javaProjectsFolder, List<Node> nodes) {
		super();
		this.javaProjectsFolder = javaProjectsFolder;
		getChildren().addAll(nodes);
	}

	public Node findChapterOrParagraphWithBeginOfFileTag(String tagName) {
		for (Node node : getChildren()) {
			if (node instanceof Chapter) {
				Chapter chapter = (Chapter) node;
				if (chapter.containsBeginOfFileTag(tagName)) {
					return chapter;
				}

				for (Node child : chapter.getChildren()) {
					if (child instanceof Paragraph) {
						Paragraph paragraph = (Paragraph) child;
						if (paragraph.containsBeginOfFileTag(tagName)) {
							return paragraph;
						}
					}
				}
			}
		}
		return null;
	}

	public File getJavaProjectsFolder() {
		return javaProjectsFolder;
	}

	public InlineTag findBeginOffFileInParent(Node node) {
		NodeContainer<Node> parent = null;
		NodeContainer<Node> currentNode = this;
		InlineTag beginOfFile = null;
		do {
			parent = findParent(currentNode, node);
			beginOfFile = findBeginOffFile(parent);
		} while (beginOfFile == null && parent != this);
		return beginOfFile;
	}

	public InlineTag findBeginOffFile(NodeContainer<Node> container) {
		for (Node child :container.getChildren()) {
			if (child instanceof InlineTag) {
				InlineTag inlineTag = (InlineTag) child;
				if (inlineTag.getName()==InlineTagName.BEGINOFFILE) {
					return inlineTag;
				}
			}
		}
		//not found;
		return null;
	}

	private NodeContainer<Node> findParent(NodeContainer<Node> parent,
			Node nodeToFind) {
		for (Node child : parent.getChildren()) {
			if (child == nodeToFind) {
				return parent;
			}
		}
		// try one level deeper
		for (Node child : parent.getChildren()) {
			if (child instanceof NodeContainer) {
				NodeContainer<Node> deeper_parent = (NodeContainer<Node>) child;
				NodeContainer<Node> foundParent = findParent(deeper_parent,
						nodeToFind);
				if (foundParent != null) {
					return foundParent;
				}
			}
		}
		// not found
		return null;
	}

}
