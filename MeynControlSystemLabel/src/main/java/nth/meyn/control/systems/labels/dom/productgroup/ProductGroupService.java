package nth.meyn.control.systems.labels.dom.productgroup;

import java.util.List;

import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;
import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;

public class ProductGroupService {

	private final ProductGroupRepository productGroupRepository;

	public ProductGroupService(ProductGroupRepository productGroupRepository) {
		this.productGroupRepository = productGroupRepository;
	}

	@Order(sequenceNumber = 1)
	public List<ProductGroup> allProductGroups() throws Exception {
		return productGroupRepository.getAll();
	}

	@Order(sequenceNumber = 2)
	@ParameterFactory
	public void createProductGroup(ProductGroup productGroup) throws Exception {
		productGroupRepository.create(productGroup);
	}

	@Order(sequenceNumber = 3)
	public void modifyProductGroup(ProductGroup productGroup) throws Exception {
		productGroupRepository.modify(productGroup);
	}

	@Order(sequenceNumber = 4)
	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION)
	public void deleteProductGroup(ProductGroup productGroup) throws Exception {
		productGroupRepository.delete(productGroup);
	}

}
