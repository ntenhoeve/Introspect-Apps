package nth.github.page.generator.model.html.element;

import java.util.ArrayList;
import java.util.List;

public class Html5Element extends Element {

	public Html5Element() {
		super(ElementType.SPECIAL_HTML5);
	}

	@Override
	public List<String> print() {
		List<String> lines = new ArrayList<>();
		// add special line at the top to indicate this document is to be
		// interpreted as HTML5
		lines.add(0, "<!DOCTYPE html>");

		// add lines of child elements
		for (Element child : getChildren()) {
			List<String> childLines = child.print();
			lines.addAll(childLines);
		}

		return lines;
	}

}
