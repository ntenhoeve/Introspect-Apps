package nth.github.page.generator.model.html.element;

import java.util.ArrayList;
import java.util.List;

public class TextBlock extends Element{

	private String textBlock;

	public TextBlock(String textBlock) {
		super(ElementType.SPECIAL_TEXT_BLOCK);
		this.textBlock = textBlock;
	}

	@Override
	public List<String> print() {
		List<String> lines=new ArrayList<>();
		lines.add(textBlock);
		return lines;
	}
	
	

}
