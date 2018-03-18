package nth.meyn.connect.dom.module.maintenance;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceOrder {
	private MaintenanceOrderPriority priority;
	private Asset asset;
	private String reportedBy;
	private LocalDateTime dateTimeReported;
	private List<String> assignedTo;
	private String toDo;
	private String whatWasDone;
	private LocalDateTime completed;

	public MaintenanceOrder() {
		assignedTo = new ArrayList<>();
	}

	public MaintenanceOrderPriority getPriority() {
		return priority;
	}

	public void setPriority(MaintenanceOrderPriority priority) {
		this.priority = priority;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public String getReportedBy() {
		return reportedBy;
	}

	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
	}

	public LocalDateTime getDateTimeReported() {
		return dateTimeReported;
	}

	public void setDateTimeReported(LocalDateTime dateTimeReported) {
		this.dateTimeReported = dateTimeReported;
	}

	public List<String> getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(List<String> assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getToDo() {
		return toDo;
	}

	public void setToDo(String toDo) {
		this.toDo = toDo;
	}

	public String getWhatWasDone() {
		return whatWasDone;
	}

	public void setWhatWasDone(String whatWasDone) {
		this.whatWasDone = whatWasDone;
	}

	public LocalDateTime getCompleted() {
		return completed;
	}

	public void setCompleted(LocalDateTime completed) {
		this.completed = completed;
	}

}
