package nth.meyn.control.systems.labels.dom.productgroup;

import java.util.List;

import nth.meyn.control.systems.labels.dom.repository.MeynControlSystemRepository;

public class ProductGroupRepository {

	private final MeynControlSystemRepository meynControlSystemRepository;

	public ProductGroupRepository(MeynControlSystemRepository meynControlSystemRepository) {
		this.meynControlSystemRepository = meynControlSystemRepository;
	}

	public List<ProductGroup> getAll() throws Exception {
		return (List<ProductGroup>) meynControlSystemRepository.getAll(ProductGroup.class);
	}

	public void create(ProductGroup productGroup) throws Exception {
		meynControlSystemRepository.persist(productGroup);
	}

	public void modify(ProductGroup productGroup) throws Exception {
		meynControlSystemRepository.persist(productGroup);
	}

	public void delete(ProductGroup productGroup) throws Exception {
		meynControlSystemRepository.delete(productGroup);
	}

}
