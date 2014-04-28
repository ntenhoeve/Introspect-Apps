package nth.github.page.generator.wiki.model;

import java.util.ArrayList;
import java.util.List;

public class WikiNodeContainer extends WikiNode {
	private List<WikiNode> childeren;

	public WikiNodeContainer() {
		childeren = new ArrayList<>();
	}

	public List<WikiNode> getChilderen() {
		return childeren;
	}

	public void setChilderen(List<WikiNode> childeren) {
		this.childeren = childeren;
	}
	
	public List<WikiNode> findChilderenOfType(  Class<? extends WikiNode> classToFind) {
		return findChilderenOfType(this, classToFind);
	}
	
	private List<WikiNode> findChilderenOfType(WikiNode node,  Class<? extends WikiNode> classToFind) {
		List<WikiNode> results=new ArrayList<>();
		if (classToFind==node.getClass()) {
			results.add(node);
		}
		if (node instanceof WikiNodeContainer) {
			WikiNodeContainer nodeContainer = (WikiNodeContainer) node;
			for (WikiNode child:nodeContainer.getChilderen()) {
				//recursive call on children
				results.addAll(findChilderenOfType(child,classToFind));
			}	
		}
		return results;
	}

}
