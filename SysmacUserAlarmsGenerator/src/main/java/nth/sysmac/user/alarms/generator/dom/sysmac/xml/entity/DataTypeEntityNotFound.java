package nth.sysmac.user.alarms.generator.dom.sysmac.xml.entity;

import nth.sysmac.user.alarms.generator.dom.sysmac.xml.datatype.DataType;

public class DataTypeEntityNotFound extends RuntimeException {

	private static final long serialVersionUID = -5014108758547784984L;

	public DataTypeEntityNotFound() {
		super("Could not find a: "+Entity.class.getSimpleName()+" containing "+DataType.class.getSimpleName()+"s");
	}
}
