package nth.meyn.innovation.intake.dom.capitalsalesintake;

import nth.introspect.provider.domain.info.valuemodel.annotations.Format;

public class Period {
	private int year;

	public void setYear(Integer year) {
		this.year = year;
	}

	@Format("####")
	public Integer getYear() {
		return year;
	}
}
