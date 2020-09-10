package nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import nth.sysmac.user.alarms.generator.dom.sysmac.basetype.BaseType;
import nth.sysmac.user.alarms.generator.dom.sysmac.basetype.OmronBaseType;

class BaseType_getReference_Test {

	private static final String COMMON_EVENT = "Common\sEvent";
	private static final String ARRAY_1_5_OF = "ARRAY[1..5] OF ";
	private static final String STRING = "STRING[10]";

	@Test
	void getReference_givenNull_mustReturnOptionalEmpty() {
		BaseType baseType = new BaseType(null);
		assertThat(baseType.getReference()).isEmpty();
	}

	@Test
	void getReference_givenEmpty_mustReturnOptionaEmpty() {
		BaseType baseType = new BaseType("");
		assertThat(baseType.getReference()).isEmpty();
	}

	@Test
	void getReference_givenStruct_mustReturnOptionalEmpty() {
		BaseType baseType = new BaseType(BaseType.STRUCT);
		assertThat(baseType.getReference()).isEmpty();
	}

	@Test
	void getReference_givenArrayOfStruct_mustReturnOptionalEmpty() {
		BaseType baseType = new BaseType(ARRAY_1_5_OF + BaseType.STRUCT);
		assertThat(baseType.getReference()).isEmpty();
	}

	@Test
	void getReference_givenEnum_mustReturnOptionalEmpty() {
		BaseType baseType = new BaseType(BaseType.ENUM);
		assertThat(baseType.getReference()).isEmpty();
	}

	@Test
	void getReference_givenInt_mustReturnOptionalEmpty() {
		BaseType baseType = new BaseType(OmronBaseType.INT.name());
		assertThat(baseType.getReference()).isEmpty();
	}
	
	@Test
	void getReference_givenString_mustReturnOptionalEmpty() {
		BaseType baseType = new BaseType(STRING);
		assertThat(baseType.getReference()).isEmpty();
	}

	@Test
	void getReference_givenRef_mustReturnOptionalRef() {
		BaseType baseType = new BaseType(COMMON_EVENT);
		assertThat(baseType.getReference()).isPresent()
				.hasValueSatisfying(ref -> assertThat(ref).isEqualTo(COMMON_EVENT));
	}

}
