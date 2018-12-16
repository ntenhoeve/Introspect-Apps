package nth.meyn.control.systems.labels.dom.productgroup;

import java.util.List;

import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;

public class ProductGroupService {

	private final ProductGroupRepository productGroupRepository;

	public ProductGroupService(ProductGroupRepository productGroupRepository) {
		this.productGroupRepository = productGroupRepository;
	}

	public List<ProductGroup> allProductGroups() throws Exception {
		return productGroupRepository.getAll();
	}

	@ParameterFactory
	public void createProductGroup(ProductGroup productGroup) throws Exception {
		productGroupRepository.create(productGroup);
	}

	public void deleteProductGroup(ProductGroup productGroup) throws Exception {
		productGroupRepository.delete(productGroup);
	}

	public void modifyProductGroup(ProductGroup productGroup) throws Exception {
		productGroupRepository.modify(productGroup);
	}

}
