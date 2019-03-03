package nth.meyn.control.system.configurator.dom._7controlmodule;

import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

@Description(englishDescription = "Represents a PLC input or output (of any data type)")
public class Io {
	private String name;
	private IoType IoType;
	private DataType dataType;

	@Order(value = 1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Order(value = 10)
	public IoType getIoType() {
		return IoType;
	}

	public void setIoType(IoType ioType) {
		IoType = ioType;
	}

	@Order(value = 20)
	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

}
