package nth.foldersynch.dom.device;

import java.util.ArrayList;
import java.util.List;

import nth.foldersynch.dom.task.FolderSynchTask;
import nth.reflect.fw.generic.util.TitleBuilder;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;
import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;

public class Device {
	private String ownerName;
	private DeviceType deviceType;
	private List<FolderSynchTask> tasks;

	public Device() {
		tasks = new ArrayList<>();
	}

	@Order(value = 10)
	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	@Order(value = 20)
	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	@Order(value = 30)
	public List<FolderSynchTask> getTasks() {
		return tasks;
	}

	public void setTasks(List<FolderSynchTask> tasks) {
		this.tasks = tasks;
	}

	@Override
	public String toString() {
		return TitleBuilder.getInstance().append(ownerName).append(deviceType).toString();
	}

	public void modifyTasks(FolderSynchTask task) {

	}

	@ParameterFactory
	public void addTasks(FolderSynchTask task) {
		tasks.add(task);
	}
}
