package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag;

import java.util.ArrayList;
import java.util.List;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag.array.ArrayTag;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag.component.ComponentCode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag.component.ComponentCodeTagWithBrackets;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag.component.ComponentCodeTagWithoutBrackets;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.textexp.tag.component.ComponentCodes;
import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataType;

/**
 * {@link Tag}
 * <p>
 * The following paragraphs explain the types of {@link Tag}s you can use in the comment of a {@link DataType}: 
 * <p>
 * <h3>Component codes</h3>{@insert ComponentCode}
 * <p>
 * 
 * @author nilsth
 *
 */
public class TagService  {

	private final List<Tag> allTags;

	public TagService() {
		allTags = createAllTags();
	}

	private List<Tag> createAllTags() {
		List<Tag> allTags=new ArrayList<>();
		allTags.add(new ComponentCodeTagWithBrackets());
		allTags.add(new ComponentCodeTagWithoutBrackets());
		return allTags;
	}

	public String removeAllTagsFrom(String expression) {
		String result = expression;
		for (Tag tag : allTags) {
			result=tag.getRegex().removeAllFrom(result);
		}
		return result;
	}


	
	public ComponentCodes createComponentCodes(String expression) {
		List<ComponentCode> componentCodesWithoutBrackets = findAllComponentCodesWithoutBrackets(expression);
		List<ComponentCode> componentCodesWithBrackets = findAllComponentCodesWithBrackets(expression);
		List<Character> componentCodeReferences = findAllComponentCodeReferenses(expression);
		ComponentCodes componentCodes=new ComponentCodes(componentCodesWithoutBrackets, componentCodesWithBrackets, componentCodeReferences);
		return componentCodes;
	}
	
	
	private List<ComponentCode> findAllComponentCodesWithBrackets(String expression) {
		List<ComponentCode> componentCodes=new ArrayList<>();
		List<String> tagStings = ComponentCodeTagWithBrackets.REGEX.findMatches(expression);
		ComponentCodeTagWithBrackets componentCodeTagWithBrackets=new ComponentCodeTagWithBrackets();
		for (String tagString : tagStings) {
			System.out.println(expression+":"+tagString);
			ComponentCode componentCode = componentCodeTagWithBrackets.parse(tagString);
			componentCodes.add(componentCode);
		}
		return componentCodes;
	}

	private List<ComponentCode> findAllComponentCodesWithoutBrackets(String expression) {
		List<ComponentCode> componentCodes=new ArrayList<>();
		List<String> tagStings = ComponentCodeTagWithoutBrackets.REGEX.findMatches(expression);
		ComponentCodeTagWithoutBrackets componentCodeTagWithoutBrackets=new ComponentCodeTagWithoutBrackets();
		for (String tagString : tagStings) {
			System.out.println(expression+":"+tagString);
			ComponentCode componentCode = componentCodeTagWithoutBrackets.parse(tagString);
			componentCodes.add(componentCode);
		}
		return componentCodes;
	}

	private List<Character> findAllComponentCodeReferenses(String expression) {
		return new ArrayList<>();
	}

	public String replaceArrayTags(String expression, int arrayIndex) {
		String result = ArrayTag.REGEX.replaceAll(expression, Integer.toString(arrayIndex));
		return result;
	}
}
