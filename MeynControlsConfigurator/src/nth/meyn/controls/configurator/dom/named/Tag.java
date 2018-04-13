package nth.meyn.controls.configurator.dom.named;

public class Tag implements Named {
	private String name;

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
