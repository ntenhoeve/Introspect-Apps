package nth.github.page.generator.model.text;

import java.util.ArrayList;
import java.util.List;

import nth.github.page.generator.dom.text.service.StringDocumentFactory;

public class TextNode {

	@Override
	public String toString() {
		return StringDocumentFactory.create(this);
	}

}
