package nth.innoforce.domain.project.event;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectStart extends ProjectEvent {

	private SimpleDateFormat formatter;

	public ProjectStart(Date startDate) {
		setDate(startDate);
	}

	@Override
	public String getSummary() {
		String text = "Project started on: ";//on language only to be stored in DB
		if (formatter == null) {
			formatter = new SimpleDateFormat("yyyy-MM-dd");
		}
		text = text + formatter.format(getDate());
		return text;
	}

}
