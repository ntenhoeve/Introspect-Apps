package nth.github.page.generator.model.html.element.page;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;

import nth.github.page.generator.model.html.element.Element;
import nth.github.page.generator.model.html.element.Html5Element;

public abstract class Page extends Html5Element implements
nth.github.page.generator.model.Page {

	public abstract Element getElement();
	
	
	public void createPageFile() {
		File file = new File(getPath());
		String html = toString();
		try {
			OutputStreamWriter out=new OutputStreamWriter(new FileOutputStream(file),"UTF8");
			//BufferedWriter out = new BufferedWriter(new FileWriter(file));
			out.write(html);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public String toString() {
		if (getChildren().size()==0) {
			addElement(getElement());
		}
		return super.toString();
	}


}
