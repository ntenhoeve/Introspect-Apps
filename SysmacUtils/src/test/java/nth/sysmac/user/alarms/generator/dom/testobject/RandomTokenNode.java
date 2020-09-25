package nth.sysmac.user.alarms.generator.dom.testobject;

import nth.reflect.util.parser.node.TokenNode;
import nth.reflect.util.random.Random;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.token.rule.Comma;

public class RandomTokenNode extends ExpressionAndNodes {

	public RandomTokenNode() {
		super( generateRandomToken());
	}

	@Override
	public ExpressionAndNodes repeatRandomly(int min, int max) {
			ExpressionAndNodes result=new ExpressionAndNodes();
			Integer repetition = Random.integer().forRange(min, max).generate();
			for (int i=0;i<repetition;i++) {
				result=result.append(generateRandomToken());
			}
			return result;
	}

	/**
	 * Generates a random {@link TokenNode} (except  {@link Comma})
	 * @return
	 */
	private static ExpressionAndNodes generateRandomToken() {
		int i = Random.integer().forRange(0, 3).generate();
		switch (i) {
		case 0:
			return TestObjectFactory.tokenNodeEqual();
		case 1:
			return TestObjectFactory.tokenNodeRandomRest().append(TestObjectFactory.tokenNodeWhiteSpace());
		case 2:
			return TestObjectFactory.tokenNodeRandomUnsignedInteger().append(TestObjectFactory.tokenNodeWhiteSpace());
		case 3:
			return TestObjectFactory.tokenNodeDot();
		default:
			return TestObjectFactory.tokenNodeWhiteSpace();
		}
	}

	
}
