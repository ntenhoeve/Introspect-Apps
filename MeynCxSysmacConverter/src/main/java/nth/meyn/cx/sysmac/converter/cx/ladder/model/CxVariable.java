package nth.meyn.cx.sysmac.converter.cx.ladder.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CxVariable {

	private String name;
	private CxVariableType type;
	private String comment;

	public void setName(String name) {
		this.name = name;
	}

	public void setType(CxVariableType variableType) {
		this.type = variableType;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getName() {
		return name;
	}

	public CxVariableType getType() {
		return type;
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
		if (obj==null || !(obj instanceof CxVariable)) {
			return false;
		}
		if (obj==this) {
			return true;
		}
		CxVariable variable=(CxVariable) obj;
		return new EqualsBuilder().append(name, variable.getName()).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(name).append(type.toString()).append(comment)
				.toString();
	}
}
