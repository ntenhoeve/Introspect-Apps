package nth.software.doc.generator.model.tag;

import nth.software.doc.generator.model.Node;
import nth.software.doc.generator.model.NodeContainer;
import nth.software.doc.generator.tokenizer.TagName;

public class Tag extends NodeContainer<Node> {
 
	private TagName tagName;

	public Tag(TagName tagName ) {
		this.tagName = tagName;
	}
}
