package nth.innoforce.domain.product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nth.innoforce.dataaccess.BaanDatabase;
import nth.introspect.Introspect;

public class ProductNodeBaanRepository {
	private static final String POULTRY_PLANT = "POULTRY PLANT";
	private static final String SIGNAL_CODE = "signalCode";
	private static String ALL_GENERIC_PRODUCTS_QUERY = null;
	private static String ENGLISH_TRANSLATION_QUERY = null;
	private static String GENERIC_PRODUCT_RELATIONS_QUERY;
	private static BaanDatabase BAAN_DATABASE = null;
	private static String ITEM = "item";
	private static String PARENT_ITEM = "parentItem";
	private static String SALES_CODE = "salesCode";
	private static String NAME_IN_DUTCH = "nameInDutch";
	private static String NAME_IN_ENGLISH = "nameInEnglish";

	static {
		BAAN_DATABASE = (BaanDatabase) Introspect.getDataAccessProvider(BaanDatabase.class);
		ALL_GENERIC_PRODUCTS_QUERY = createAllGenericProductsQuery();
		GENERIC_PRODUCT_RELATIONS_QUERY = createGenericProductRelationsQuery();
		ENGLISH_TRANSLATION_QUERY = createEnglishTranslationQuery();
	}

	private static String createGenericProductRelationsQuery() {
		StringBuffer sql = new StringBuffer();
		sql.append("select T2.T$MITM as ");
		sql.append(PARENT_ITEM);
		sql.append(",T2.T$PONO,T2.T$SERN,T2.T$SITM as ");
		sql.append(ITEM);
		sql.append(",T1.T$DSCA as ");
		sql.append(NAME_IN_DUTCH);
		sql.append(",T1.T$SEAB as ");
		sql.append(SALES_CODE);
		sql.append(",T1.T$CSIG as ");
		sql.append(SIGNAL_CODE);
		sql.append("\n");
		sql.append(" from baan.ttiitm001100 T1,baan.ttipcf310100 T2\n");
		sql.append(" where (T1.T$CSEL = 'GEN') and (T1.T$ITEM = T2.T$SITM)\n");
		sql.append(" order by T2.T$MITM asc,T2.T$PONO asc,T2.T$SERN asc");
		return sql.toString();
	}

	private static String createAllGenericProductsQuery() {
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct T1.T$ITEM as ");
		sql.append(ITEM);
		sql.append(",T1.T$DSCA as ");
		sql.append(NAME_IN_DUTCH);
		sql.append(",T1.T$SEAB as ");
		sql.append(SALES_CODE);
		sql.append(",T1.T$CSIG as ");
		sql.append(SIGNAL_CODE);
		sql.append("\n");
		sql.append(" from baan.ttiitm001100 T1,baan.ttipcf100100 T2\n");
		//sql.append(" where (T1.T$KITM = 3) and (T1.T$ITEM > 'A               ') and (T1.T$ITEM <= 'ZZZZZZZZZZZZZZZZ') and (T1.T$CSIG NOT IN ('021','130','630','BLA','BLC','BLS','BLT','BLV','NB2','NOT','PRT','PUR','TXT','XBO','XBR','XRO','XRR')) and (T2.T$ITEM = T1.T$ITEM) and (T1.T$CTYP >= '  A') and (T1.T$CTYP <= 'ZZZ')\n");
		sql.append(" where (T1.T$KITM = 3) and (T1.T$CSIG NOT IN ('021','130','630','BLA','BLC','BLS','BLT','BLV','NB2','NOT','PRT','PUR','TXT','XBO','XBR','XRO','XRR','EOL')) and (T2.T$ITEM = T1.T$ITEM) and (T1.T$CTYP >= '  A') and (T1.T$CTYP <= 'ZZZ')\n");
		// sql.append(" order by T1.T$CTYP asc,T1.T$SEAB asc");
		return sql.toString();
	}

	private static String createEnglishTranslationQuery() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select T1.T$ITEM as ");
		sql.append(ITEM);
		sql.append(",T1.T$DSCA as ");
		sql.append(NAME_IN_ENGLISH);
		sql.append("\n");
		sql.append(" from baan.ttiitm901100 T1\n");
		sql.append(" where (T1.T$CLAN = ' 02') and (T1.T$ITEM >= 'AAAAAAAAAAAAAAAAAAAAAAAAAAA') and (T1.T$ITEM <= 'ZZZZZZZZZZZZZZZZZZZZZZ')  and (T1.T$ITEM NOT like 'DAN%')\n");
		return sql.toString();
	}

	public static List<ProductNode> getAllProductNodes() throws Exception {
		// get all generic products from Baan

		List<ProductNode> productNodes = new ArrayList<ProductNode>();

		// create all productGroups without setting parent and child relations

		// create all PRODUCTS without setting parent and child relations
		Statement statement = BAAN_DATABASE.executeSQL(ALL_GENERIC_PRODUCTS_QUERY);
		ResultSet resultSet = statement.getResultSet();
		populateProductNodes(productNodes, resultSet);

		ProductNode productGroup = new ProductNode();
		productGroup.setItem(POULTRY_PLANT);
		productGroup.setNameInDutch("Generieke product structuur");
		productGroup.setNameInEnglish("Generic Product Tree");
		productNodes.add(productGroup);

		// create all PRODUCT_GROUPS without setting parent and child relations
		ArrayList<ProductNode> roots = new ArrayList<ProductNode>();
		roots.addAll(productNodes);
		statement = BAAN_DATABASE.executeSQL(GENERIC_PRODUCT_RELATIONS_QUERY);
		resultSet = statement.getResultSet();
		populateProductNodes(productNodes, resultSet);

		// set parent and child relations
		resultSet.beforeFirst();
		while (resultSet.next()) {
			String parentName = resultSet.getString(PARENT_ITEM);
			String childName = resultSet.getString(ITEM);
			ProductNode parent = findProductGroup(productNodes, parentName);
			ProductNode child = findProductGroup(productNodes, childName);
			child.setParent(parent);
			if (parent != null) {
				// add to parent
				parent.getChildren().add(child);
				// remove child from roots because it has has moved to a parent
				roots.remove(child);
			}
		}

		// override description when available in Baan translation table
		statement = BAAN_DATABASE.executeSQL(ENGLISH_TRANSLATION_QUERY);
		resultSet = statement.getResultSet();
		while (resultSet.next()) {
			String item = resultSet.getString(ITEM);
			String nameInEnglish = resultSet.getString(NAME_IN_ENGLISH);
			productGroup = findProductGroup(productNodes, item);
			if (productGroup != null) {
				productGroup.setNameInEnglish(nameInEnglish);
			}
		}

		return roots;
	}

	private static void populateProductNodes(List<ProductNode> productGroups, ResultSet resultSet) throws SQLException {
		while (resultSet.next()) {
			ProductNode productNode = new ProductNode();
			productNode.setItem(resultSet.getString(ITEM));
			productNode.setNameInDutch(resultSet.getString(NAME_IN_DUTCH));
			productNode.setSalesCode(resultSet.getString(SALES_CODE));
			productNode.setSignalCode(resultSet.getString(SIGNAL_CODE));
			productGroups.add(productNode);
		}
	}

	private static ProductNode findProductGroup(List<ProductNode> productGroups, String name) {
		String trimmedName = name.trim();
		for (ProductNode productGroup : productGroups) {
			if (productGroup.getItem().trim().equals(trimmedName)) {
				return productGroup;
			}
		}
		return null;
	}

	public static ProductNode getGenericProductTree() throws Exception {
		List<ProductNode> productGroups = getAllProductNodes();
		return findProductGroup(productGroups, POULTRY_PLANT);
	}

	public static ProductNode getMissingProductsInGenericProductTree() throws Exception {
		List<ProductNode> productGroups = getAllProductNodes();
		ProductNode poultryPlant = findProductGroup(productGroups, POULTRY_PLANT);
		productGroups.remove(poultryPlant);

		ProductNode missingProducts = new ProductNode();
		missingProducts.setItem("Missing Products");
		missingProducts.setNameInEnglish("Missend in de generieke product structuur");
		missingProducts.setNameInEnglish("Missing in Generic Product Tree");
		missingProducts.getChildren().addAll(productGroups);
		return missingProducts;
	}
}
