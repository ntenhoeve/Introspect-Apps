package nth.foldersynch.dom.device;

import java.util.ArrayList;
import java.util.List;

import nth.foldersynch.dom.task.Direction;
import nth.foldersynch.dom.task.FolderSynchTask;

public class DeviceRepository {

	public List<Device> getAll() {
		List<Device> devices=new ArrayList<>();
		Device device=new Device();
		device.setOwnerName("Nils");
		device.setDeviceType(DeviceType.LAPTOP);
		FolderSynchTask task=new FolderSynchTask();
		task.setDirection(Direction.COPY_LOCAL_TO_SERVER);
		task.setServerFolder("/My Documents");
		device.getTasks().add(task);
		devices.add(device );
		return devices;
	}

	public void persist(Device device) {
		// TODO Auto-generated method stub
		
	}

	
}
