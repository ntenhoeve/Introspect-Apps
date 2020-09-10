package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.obsolete.token.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ComponentCodes {

	private final List<ComponentCode> componentCodesWithoutBrackets;
	private final List<ComponentCode> componentCodesWithBrackets;
	private final List<ComponentCode> allComponentCodes;
	private final List<Character> componentCodeReferences;

	public ComponentCodes(List<ComponentCode> componentCodesWithoutBrackets,
			List<ComponentCode> componentCodesWithBrackets, List<Character> componentCodeReferences) {
		this.componentCodesWithoutBrackets = componentCodesWithoutBrackets;
		this.componentCodesWithBrackets = componentCodesWithBrackets;
		allComponentCodes=new ArrayList<>();
		allComponentCodes.addAll(componentCodesWithoutBrackets);
		allComponentCodes.addAll(componentCodesWithBrackets);
		this.componentCodeReferences = componentCodeReferences.stream().map(c->Character.toUpperCase(c)).collect(Collectors.toUnmodifiableList());
	}

	public List<String> findVisible() {
		List<String> visibleComponentCodes = new ArrayList<>();
		List<String> componentCodesWithoutBrackets = this.componentCodesWithoutBrackets.stream()
				.map(ComponentCode::toString).collect(Collectors.toList());
		visibleComponentCodes.addAll(componentCodesWithoutBrackets);

		for (Character reference : componentCodeReferences) {
			Optional<ComponentCode> result = componentCodesWithBrackets.stream().filter(c-> reference.equals(c.getLetter())).findAny();
			if (result.isPresent()) {
				String foundComponentCode = result.get().toString();
				visibleComponentCodes.add(foundComponentCode);
			} else {
				result=allComponentCodes.stream().filter(c->reference.equals(c.getLetter())).findAny();
				if (result.isPresent()) {
					String derivedComponentCode = result.get().getDerivedComponentCode(reference).toString();
					visibleComponentCodes.add(derivedComponentCode);
				}
			}
		}
		
		return visibleComponentCodes;
	}
	
	public void next() {
		allComponentCodes.stream().forEach(c->c.goToNext());
	}

}
