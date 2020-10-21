package nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import nth.sysmac.user.alarms.generator.dom.sysmac.basetype.BaseType;

class BaseType_getArray_Test {


	@Test
	void getArray_givenNull_mustReturnOptionalEmpty() {
		BaseType baseType = new BaseType(null);
		assertThat(baseType.getArray()).isEmpty();
	}

	@Test
	void getArray_givenEmpty_mustReturnOptionaEmpty() {
		BaseType baseType = new BaseType("");
		assertThat(baseType.getArray()).isEmpty();
	}

	@Test
	void getArray_givenStruct_mustReturnOptionalEmpty() {
		BaseType baseType = new BaseType(BaseType.STRUCT);
		assertThat(baseType.getArray()).isEmpty();
	}

	

}
