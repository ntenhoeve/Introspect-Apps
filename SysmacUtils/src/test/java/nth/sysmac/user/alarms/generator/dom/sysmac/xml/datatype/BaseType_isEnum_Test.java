package nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import nth.sysmac.user.alarms.generator.dom.sysmac.basetype.BaseType;
import nth.sysmac.user.alarms.generator.dom.sysmac.basetype.OmronBaseType;


class BaseType_isEnum_Test {

	
	private static final String ARRAY_1_5_OF = "ARRAY[1..5] OF ";

	@Test
	void isEnum_givenNull_mustReturnFalse() {
		BaseType baseType=new BaseType(null);
		assertThat(baseType.isEnum()).isFalse();
	}

	@Test
	void isEnum_givenEmpty_mustReturnFalse() {
		BaseType baseType=new BaseType("");
		assertThat(baseType.isEnum()).isFalse();
	}
	
	@Test
	void isEnum_givenEnum_mustReturnTrue() {
		BaseType baseType=new BaseType(BaseType.ENUM);
		assertThat(baseType.isEnum()).isTrue();
	}


	@Test
	void isEnum_givenArrayOfStruct_mustReturnTrue() {
		BaseType baseType=new BaseType(ARRAY_1_5_OF+BaseType.ENUM);
		assertThat(baseType.isEnum()).isTrue();
	}

	@Test
	void isEnum_givenNoneStruct_mustReturnFalse() {
		BaseType baseType=new BaseType(OmronBaseType.BOOL.toString());
		assertThat(baseType.isEnum()).isFalse();
	}

}
