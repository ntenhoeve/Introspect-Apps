package nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.factory;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import nth.meyn.cx.sysmac.converter.sysmac.ladder.xml.Rungs.RungXML.LadderElement.PinViewModel;
import nth.meyn.cx.sysmac.converter.sysmac.types.SysmacConnectionPointType;
import nth.meyn.cx.sysmac.converter.sysmac.types.SysmacDataType;

public class PinViewModelFactory {

	public static JAXBElement<PinViewModel> create(SysmacConnectionPointType sysmacConnectionPointType, String name, String comment, SysmacDataType dataType,
			boolean isPowerPin) {
		PinViewModel pinViewModel = new PinViewModel();
		pinViewModel.setIsInput(Boolean.toString(sysmacConnectionPointType==SysmacConnectionPointType.INPUT));
		pinViewModel.setName(name);
		pinViewModel.setDatatype(dataType.toString());
		pinViewModel.setComment(comment);
		pinViewModel.setNegated(Boolean.FALSE.toString());
		pinViewModel.setIsInOutVariable(Boolean.FALSE.toString());
		pinViewModel.setPowerPin(Boolean.toString(isPowerPin));
		pinViewModel.setVisible(Boolean.TRUE.toString());
		pinViewModel.setEdgeDirectionType("NoEdge");
		
		JAXBElement<PinViewModel> jaxbElement = new JAXBElement<PinViewModel>(
				QName.valueOf(PinViewModel.class.getSimpleName()), PinViewModel.class,
				pinViewModel);
		return jaxbElement;
	}

}
