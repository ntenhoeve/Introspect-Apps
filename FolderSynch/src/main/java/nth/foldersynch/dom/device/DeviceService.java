package nth.foldersynch.dom.device;

import java.util.List;

import nth.introspect.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;

public class DeviceService {

	private DeviceRepository deviceRepository;

	public DeviceService(DeviceRepository deviceRepository) {
		this.deviceRepository = deviceRepository;
	}

	public List<Device> allDevices() {
		List<Device> devices = deviceRepository.getAll();
		return devices;
	}

	public void modifyDevice(Device device) {
		deviceRepository.persist(device);
	}
	
	@ParameterFactory 
	public void createDevice(Device device) {
		deviceRepository.persist(device);
	}	
	
}
