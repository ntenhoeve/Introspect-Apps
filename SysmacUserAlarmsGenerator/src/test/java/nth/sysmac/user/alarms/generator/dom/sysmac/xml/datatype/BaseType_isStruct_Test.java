package nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import nth.sysmac.user.alarms.generator.dom.sysmac.basetype.BaseType;
import nth.sysmac.user.alarms.generator.dom.sysmac.basetype.OmronBaseType;


class BaseType_isStruct_Test {

	
	private static final String ARRAY_1_5_OF = "ARRAY[1..5] OF ";

	@Test
	void isStruct_givenNull_mustReturnFalse() {
		BaseType baseType=new BaseType(null);
		assertThat(baseType.isStruct()).isFalse();
	}

	@Test
	void isStruct_givenEmpty_mustReturnFalse() {
		BaseType baseType=new BaseType("");
		assertThat(baseType.isStruct()).isFalse();
	}
	
	@Test
	void isStruct_givenStruct_mustReturnTrue() {
		BaseType baseType=new BaseType(BaseType.STRUCT);
		assertThat(baseType.isStruct()).isTrue();
	}


	@Test
	void isStruct_givenArrayOfStruct_mustReturnTrue() {
		BaseType baseType=new BaseType(ARRAY_1_5_OF+BaseType.STRUCT);
		assertThat(baseType.isStruct()).isTrue();
	}

	@Test
	void isStruct_givenNoneStruct_mustReturnFalse() {
		BaseType baseType=new BaseType(OmronBaseType.BOOL.toString());
		assertThat(baseType.isStruct()).isFalse();
	}

}
