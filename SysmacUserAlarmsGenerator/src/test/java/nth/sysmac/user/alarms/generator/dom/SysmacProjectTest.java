package nth.sysmac.user.alarms.generator.dom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SysmacProjectTest {

	@Test
	public void constructor_withNull_throwsNPE() {
		Assertions.assertThrows(NullPointerException.class, () -> {new SysmacProject(null);});
	}

}
