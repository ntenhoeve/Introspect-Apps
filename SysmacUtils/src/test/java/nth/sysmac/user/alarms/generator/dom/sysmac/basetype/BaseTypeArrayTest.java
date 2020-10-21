package nth.sysmac.user.alarms.generator.dom.sysmac.basetype;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class BaseTypeArrayTest {

	@Test
	void test_GivenArray5_7and1_3and0_2_resultsInCurrectHmiVariables() {
		BaseType baseType = new BaseType("ARRAY[5..7,1..3,0..2] OF BOOL");
		BaseTypeArray baseTypeArray = baseType.getArray().get();
		List<String> results = new ArrayList<>();

		boolean done = false;
		while (!done) {
			if (!baseTypeArray.canGoToNext()) {
				done = true;
			}
			results.add(baseTypeArray.toString());
			if (baseTypeArray.canGoToNext()) {
				baseTypeArray.goToNext();
			}
		}
		List<String> expected = new ArrayList<>();
		expected.add("(5,1,0)");
		expected.add("(5,1,1)");
		expected.add("(5,1,2)");
		expected.add("(5,2,0)");
		expected.add("(5,2,1)");
		expected.add("(5,2,2)");
		expected.add("(5,3,0)");
		expected.add("(5,3,1)");
		expected.add("(5,3,2)");
		expected.add("(6,1,0)");
		expected.add("(6,1,1)");
		expected.add("(6,1,2)");
		expected.add("(6,2,0)");
		expected.add("(6,2,1)");
		expected.add("(6,2,2)");
		expected.add("(6,3,0)");
		expected.add("(6,3,1)");
		expected.add("(6,3,2)");
		expected.add("(7,1,0)");
		expected.add("(7,1,1)");
		expected.add("(7,1,2)");
		expected.add("(7,2,0)");
		expected.add("(7,2,1)");
		expected.add("(7,2,2)");
		expected.add("(7,3,0)");
		expected.add("(7,3,1)");
		expected.add("(7,3,2)");
		assertThat(results).containsExactlyElementsOf(expected);
	}

}
