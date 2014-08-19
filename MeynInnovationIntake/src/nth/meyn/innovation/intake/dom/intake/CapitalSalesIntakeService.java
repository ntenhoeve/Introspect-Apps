package nth.meyn.innovation.intake.dom.intake;

import java.util.List;

import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;
import nth.meyn.innovation.intake.dom.capitalsalesintake.OrderLine;
import nth.meyn.innovation.intake.dom.capitalsalesintake.Period;

public class CapitalSalesIntakeService {

	private final CapitalSalesIntakeRepository capitalSalesIntakeRepository;

	public CapitalSalesIntakeService(CapitalSalesIntakeRepository intakeRepository) {
		this.capitalSalesIntakeRepository = intakeRepository;
	}

	@GenericReturnType(OrderLine.class)
	public List<OrderLine> intake(Period period) {
		return capitalSalesIntakeRepository.intake(period);
	}
}
