package nth.github.page.generator.model.text;

import java.util.ArrayList;
import java.util.List;

public class TextNodeContainer extends TextNode {
	private List<TextNode> childeren;

	public TextNodeContainer() {
		childeren = new ArrayList<>();
	}

	public List<TextNode> getChilderen() {
		return childeren;
	}

	public void setChilderen(List<TextNode> childeren) {
		this.childeren = childeren;
	}
	
	public List<TextNode> findChilderenOfType(  Class<? extends TextNode> classToFind) {
		return findChilderenOfType(this, classToFind);
	}
	
	private List<TextNode> findChilderenOfType(TextNode node,  Class<? extends TextNode> classToFind) {
		List<TextNode> results=new ArrayList<>();
		if (classToFind==node.getClass()) {
			results.add(node);
		}
		if (node instanceof TextNodeContainer) {
			TextNodeContainer nodeContainer = (TextNodeContainer) node;
			for (TextNode child:nodeContainer.getChilderen()) {
				//recursive call on children
				results.addAll(findChilderenOfType(child,classToFind));
			}	
		}
		return results;
	}

}
