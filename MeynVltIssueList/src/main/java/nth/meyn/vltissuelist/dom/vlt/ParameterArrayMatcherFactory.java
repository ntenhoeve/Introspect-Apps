package nth.meyn.vltissuelist.dom.vlt;

import java.util.ArrayList;
import java.util.List;

public class ParameterArrayMatcherFactory {

	static List<ParameterArrayMatcher> create(int parameter) {
		List<ParameterArrayMatcher> parameterArrayMatchers = new ArrayList<>();
		for (int setup = 0; setup <= 3; setup++) {
			ParameterArrayMatcher matcher = new ParameterArrayMatcher(
					parameter, setup);
			parameterArrayMatchers.add(matcher);
		}
		return parameterArrayMatchers;
	}

}
