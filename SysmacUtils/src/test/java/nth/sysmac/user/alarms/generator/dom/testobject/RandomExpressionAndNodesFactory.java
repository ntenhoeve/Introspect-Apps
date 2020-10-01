package nth.sysmac.user.alarms.generator.dom.testobject;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.util.random.Random;

public abstract class RandomExpressionAndNodesFactory {

	public abstract ExpressionAndNodes create();

	public List<ExpressionAndNodes> createList() {
		int max = Random.integer().forRange(0,4).generate();
		return createList(0, max);
	}

	public List<ExpressionAndNodes> createList(int min, int max) {
		List<ExpressionAndNodes> results = new ArrayList<>();
		int n = Random.integer().forRange(min, max).generate();
		for (int i = 0; i < n; i++) {
			ExpressionAndNodes expressionAndNodes = create();
			results.add(expressionAndNodes);
		}
		return results;
	}
}
