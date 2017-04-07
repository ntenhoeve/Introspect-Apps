package nth.meyn.cx.sysmac.converter.cx.symbolOLD;

public class CxSymbol {


	private String name;
	private String address;
	private String type;
	private boolean automaticAddressing;
	private String comment;

	public void setName(String name) {
		this.name = name;
		
	}

	public void setAddress(String address) {
		this.address = address;
		
	}

	public void setType(String type) {
		this.type = type;
		
	}

	public void setAutomaticAddressing(boolean automaticAddressing) {
		this.automaticAddressing = automaticAddressing;
	}

	public void setComment(String comment) {
		this.comment = comment;
		
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getType() {
		return type;
	}

	public boolean isAutomaticAddressing() {
		return automaticAddressing;
	}

	public String getComment() {
		return comment;
	}

}
