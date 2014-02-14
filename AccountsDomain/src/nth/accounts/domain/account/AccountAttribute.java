package nth.accounts.domain.account;

public class AccountAttribute {
	private String name;
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		StringBuffer title = new StringBuffer();
		title.append(name.trim());
		title.append(": ");
		title.append(value.trim());
		return title.toString();
	}

}
