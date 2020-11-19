package nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.counter.skip.column;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.counter.CounterNode;
import nth.sysmac.user.alarms.generator.dom.sysmac.useralarm.parser.rule.counter.skip.even.SkipEvenCounterNode;

class SkipEvenCounterNodeTest {

	private static SkipEvenCounterNode skipEvenCounterNode;

	@BeforeAll
	public static void beforeAll() {
		skipEvenCounterNode = new SkipEvenCounterNode();
	}

	@Test
	void testAppliesTo_givenUnevenComponentCode_returnsFalse() {
		CounterNode counterNode = new CounterNode(0);
		counterNode.setValue(3);
		assertThat(skipEvenCounterNode.appliesTo(counterNode)).isFalse();
	}

	@Test
	void testAppliesTo_givenEvenComponentCode_returnsTrue() {
		CounterNode counterNode = new CounterNode(0);
		counterNode.setValue(2);
		assertThat(skipEvenCounterNode.appliesTo(counterNode)).isTrue();
	}

	@Test
	void testGoToNext_givenEvenComponentCode_returnsNextCounterValue() {
		CounterNode counterNode = new CounterNode(0);
		counterNode.setValue(2);
		assertThat(counterNode.getValue()).isEqualTo(4);
	}

}
