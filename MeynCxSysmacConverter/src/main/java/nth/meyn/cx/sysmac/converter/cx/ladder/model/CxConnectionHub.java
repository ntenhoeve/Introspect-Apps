package nth.meyn.cx.sysmac.converter.cx.ladder.model;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CxConnectionHub {

	private final List<Object> inputs;
	private final List<Object> outputs;

	public CxConnectionHub(List<Object> inputs, List<Object> outputs) {
		this.inputs = inputs;
		this.outputs = outputs;
	}

	public List<Object> getInputs() {
		return inputs;
	}

	public List<Object> getOutputs() {
		return outputs;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof CxConnectionHub)) {
			return false;
		}
		CxConnectionHub connectionHub = (CxConnectionHub) obj;

		return new EqualsBuilder().append(inputs, connectionHub.getInputs())
				.append(outputs, connectionHub.getOutputs()).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(inputs).append(outputs).hashCode();
	}

	@Override
	public String toString() {
		StringBuilder reply = new StringBuilder();
		reply.append("[");
		boolean comma=false;
		for (Object object : inputs) {
			if (comma) {
				reply.append(", ");
			}
			reply.append(getVarName(object));
			comma=true;
		}
		reply.append("] => [");
		comma=false;
		for (Object object : outputs) {
			if (comma) {
				reply.append(", ");
			}
			reply.append(getVarName(object));
			comma=true;
		}
		reply.append("]");
		return reply.toString();
	}

	private String getVarName(Object object) {
		try {
			Method getOperandsMethod = object.getClass().getDeclaredMethod("getOperands");
			Object operands = getOperandsMethod.invoke(object);

			Method getOperandMethod = operands.getClass().getMethod("getOperand");
			Object operand = getOperandMethod.invoke(operands);

			Method getNameMethod = operand.getClass().getMethod("getName");
			String name = (String) getNameMethod.invoke(operand);
			return name;
		} catch (Exception e) {
			return "";
		}
	}
}
