package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.componentcode;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @see {@link VisibleComponentCodeRule}.
 * @author nilsth
 *
 */
public class DerivedComponentCodeNode extends ComponentCodeNode {

	public DerivedComponentCodeNode(List<ComponentCodeNode> componentCodeNodes, char letter, int index) {
		super(createComponentCodeNode(componentCodeNodes, letter, index));
	}

	/**
	 * 
	 * @param componentCodeNodes
	 * @param letter
	 * @param index: 1= first item in the collection: collection.get(0)
	 * @return
	 */
	private static ComponentCodeNode createComponentCodeNode(List<ComponentCodeNode> componentCodeNodes, char letter, int index) {
		if (componentCodeNodes.isEmpty()) {
			throw new RuntimeException("No hidden or visible component code(s) defined while using a DervicedComponentCode");
		}
		letter=Character.toUpperCase(letter);
		List<ComponentCodeNode> candidates=getComponentCodesWithLetter(componentCodeNodes, letter);
		if (candidates.isEmpty()) {
			ComponentCodeNode firstComponentCode = componentCodeNodes.get(0);
			ComponentCodeNode derived=firstComponentCode.getDerivedComponentCode(letter);
			return derived;
		} else {
			if (index<=candidates.size()) {
				ComponentCodeNode candidate = candidates.get(index-1);
				ComponentCodeNode derived=candidate.getDerivedComponentCode(letter);
				return derived;
			}
		}
		throw new RuntimeException("No component code defined while using a ComponentCodeReference");
	}

	private static List<ComponentCodeNode> getComponentCodesWithLetter(List<ComponentCodeNode> componentCodeNodes,
			char letter) {
		List<ComponentCodeNode> componentCodes = componentCodeNodes.//
				stream()//
				.filter(cc -> cc.getLetter().equals(letter))//
				.collect(Collectors.toList());
		return componentCodes;
	}

	

}
