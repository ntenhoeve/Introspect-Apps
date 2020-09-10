package nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import nth.sysmac.user.alarms.generator.dom.sysmac.basetype.BaseType;
import nth.sysmac.user.alarms.generator.dom.sysmac.basetype.OmronBaseType;

class BaseType_getOmronType_Test {

	@Test
	void getOmronType_givenNull_mustReturnOptionalEmpty() {
		BaseType baseType = new BaseType(null);
		assertThat(baseType.getOmronType()).isEmpty();
	}

	@Test
	void getOmronType_givenEmpty_mustReturnOptionaEmpty() {
		BaseType baseType = new BaseType("");
		assertThat(baseType.getOmronType()).isEmpty();
	}

	@Test
	void getOmronType_givenStruct_mustReturnOptionalEmpty() {
		BaseType baseType = new BaseType(BaseType.STRUCT);
		assertThat(baseType.getOmronType()).isEmpty();
	}

	@Test
	void getOmronType_givenInt_mustReturnInt() {
		BaseType baseType = new BaseType(OmronBaseType.INT.name());
		assertThat(baseType.getOmronType()).isPresent().hasValueSatisfying(omronType -> 
			assertThat(omronType).isEqualTo(OmronBaseType.INT));
	}

}
