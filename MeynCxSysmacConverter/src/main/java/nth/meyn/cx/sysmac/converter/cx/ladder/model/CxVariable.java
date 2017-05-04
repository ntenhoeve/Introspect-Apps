package nth.meyn.cx.sysmac.converter.cx.ladder.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CxVariable {

	private String name;
	private CxDataType dataType;
	private String comment;
	private boolean retained;
	private String functionBlockNameOrStructName;
	private CxVariableType variableType;
	private Integer arraySize;

	public void setName(String name) {
		this.name = name;
	}

	public void setDataType(CxDataType dataType) {
		this.dataType = dataType;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getName() {
		return name;
	}

	public CxDataType getDataType() {
		return dataType;
	}

	public String getComment() {
		return comment;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof CxVariable)) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		CxVariable variable = (CxVariable) obj;
		return new EqualsBuilder().append(name, variable.getName()).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(name).append(dataType.toString()).append(comment)
				.append(functionBlockNameOrStructName).append(variableType.toString())
				.append(retained).append(arraySize).
				toString();
	}

	public void setRetained(boolean retained) {
		this.retained = retained;
	}

	public void setFunctionBlockNameOrStructName(String functionBlockNameOrStructName) {
		this.functionBlockNameOrStructName = functionBlockNameOrStructName;
	}

	public void setType(CxVariableType variableType) {
		this.variableType = variableType;
	}

	public void setArraySize(Integer arraySize) {
		this.arraySize = arraySize;
	}
}
