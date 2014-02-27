	package nth.innoforce.domain.product;

import java.net.MalformedURLException;
import java.net.URL;

import nth.introspect.provider.domain.info.method.MethodInfo.ExecutionModeType;
import nth.introspect.provider.domain.info.valuemodel.annotations.ExecutionMode;
import nth.introspect.provider.domain.info.valuemodel.annotations.Icon;


public class ProductsService {

//	@GenericReturnType(ProductGroup.class)
//	@Icon("find")
//	public List<ProductGroup> findProduct(FindParameter findParameter) {
//		StringBuffer query = new StringBuffer("select e from ");
//		query.append(ProductGroup.class.getCanonicalName());
//		query.append(" e");
//		query.append(" where e.deleted=false and e.path like '%");
//		query.append(findParameter.getTextToFind());
//		query.append("%'");
//		return productGroupRepository.executeQuery(query.toString());
//	}
//	

	
	public ProductNode genericProductTree() throws Exception {
		return ProductNodeBaanRepository.getGenericProductTree();
	}

	public ProductNode missingProductsInGenericProductTree() throws Exception {
		return ProductNodeBaanRepository.getMissingProductsInGenericProductTree();
	}

	@Icon("View")
	@ExecutionMode(ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public ProductNode viewProductNode(ProductNode productNode) {
		return productNode;
	}
	
	@Icon("Modify")
	@ExecutionMode(ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public void modifyProductNode(ProductNode productNode) {
		throw new RuntimeException("The product can only be modified in Baan.<br>Please contact the responsible product manager for product changes in Baan.");
	}
	

	public URL productReport() throws  MalformedURLException {
		return new URL("http://localhost:8080/CrystalTest/");
	}
	
}
