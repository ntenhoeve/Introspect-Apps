package nth.reflect.app.swdocgen.dom.documentation;

import java.io.File;

import nth.introspect.layer5provider.reflection.behavior.order.Order;

public class HtmlInfo extends DocumentationInfo {

	private File destinationFolder;

	@Order(sequenceNumber= 11)
	public File getDestinationFolder() {
		return destinationFolder;
	}

	public void setDestinationFolder(File destinationFolder) {
		this.destinationFolder = destinationFolder;
	}
}
