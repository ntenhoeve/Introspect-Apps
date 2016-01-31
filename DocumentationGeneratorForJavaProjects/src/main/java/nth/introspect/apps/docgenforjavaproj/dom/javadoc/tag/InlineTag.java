package nth.introspect.apps.docgenforjavaproj.dom.javadoc.tag;

import java.io.IOException;

import nth.introspect.generic.regex.Regex;
import nth.introspect.generic.regex.Repetition;

import org.jsoup.nodes.Element;

public abstract class InlineTag {
	
	
	public Regex getRegex() {
		Regex tagBegin = createTagBegin();
		Regex tagMiddle = createTagMiddle();
		Regex ragEnd = createTagEnd();
		Regex tag = new Regex().append(tagBegin).append(tagMiddle)
				.append(ragEnd);
		return tag;
	}

	protected Regex createTagEnd() {
		return new Regex().whiteSpace(
				Repetition.zeroOrMoreTimes()).literal("}");
	}

	private Regex createTagMiddle() {
		return new Regex().anyCharacter(Repetition
				.oneOrMoreTimes().reluctant());
	}

	protected Regex createTagBegin() {
		return new Regex().ignoreCase()
				.literal("{").whiteSpace(Repetition.zeroOrMoreTimes()).literal("@")
				.whiteSpace(Repetition.zeroOrMoreTimes()).literal(getName())
				.whiteSpace(Repetition.oneOrMoreTimes());
	};

	protected abstract  String getName();

	
	public String getReplacementText(String tag) throws IOException {
		String tagValue = tag.replaceFirst(createTagBegin().toString(), "").replaceFirst(createTagEnd().toString(), "");
		return getElement(tagValue).toString();
	}

	protected abstract Element getElement(String tagValue); 
}
