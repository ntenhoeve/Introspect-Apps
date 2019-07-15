package nth.meyn.control.system.configurator.dom.ethernetdevice;

import java.util.List;

import nth.meyn.control.system.configurator.dom.repository.MeynControlSystemRepository;
import nth.reflect.fw.gui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIcon;
import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;

public class EthernetDeviceTypeService {
	private final MeynControlSystemRepository meynControlSystemRepository;

	public EthernetDeviceTypeService(MeynControlSystemRepository meynControlSystemRepository) {
		this.meynControlSystemRepository = meynControlSystemRepository;
	}

	public List<EthernetDeviceType> allEthernetDeviceTypes() throws Exception {
		@SuppressWarnings("unchecked")
		List<EthernetDeviceType> ethernetDeviceTypes = (List<EthernetDeviceType>) meynControlSystemRepository
				.getAll(EthernetDeviceType.class);
		return ethernetDeviceTypes;
	}

	@FontIcon(fontIconUrl = FontAwesomeUrl.EYE)
	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public EthernetDeviceType viewEthernetDeviceType(EthernetDeviceType ethernetDeviceType) {
		return ethernetDeviceType;
	}

	@FontIcon(fontIconUrl = FontAwesomeUrl.EDIT)
	@ExecutionMode(mode = ExecutionModeType.EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL)
	public void modifyEthernetDeviceType(EthernetDeviceType ethernetDeviceType) throws Exception {
		meynControlSystemRepository.persist(ethernetDeviceType);
	}

	@ParameterFactory
	@FontIcon(fontIconUrl = FontAwesomeUrl.PLUS)
	@ExecutionMode(mode = ExecutionModeType.EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL)
	public void newEthernetDeviceType(EthernetDeviceType ethernetDeviceType) throws Exception {
		meynControlSystemRepository.persist(ethernetDeviceType);
	}

}
