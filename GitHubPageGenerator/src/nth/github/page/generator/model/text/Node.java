package nth.github.page.generator.model.text;

import java.util.ArrayList;
import java.util.List;

import nth.github.page.generator.model.pagefactories.StringDocumentFactory;

public class Node {

	@Override
	public String toString() {
		return StringDocumentFactory.create(this);
	}

}
