package nth.innoforce.domain.innovationrevenue;

/**
 * 
 * @author nilsth
 * 
 *         Product Code XY-ZZZZ-SUFIX<br>
 * <br>
 *         X=<br>
 *         A Live Bird Handling<br>
 *         B Slaughter<br>
 *         C Evisceration<br>
 *         D Chilling<br>
 *         E Weighing Line<br>
 *         F Cut-up<br>
 *         G Not Used<br>
 *         H Offal Handling<br>
 *         J Sorting Line<br>
 *         K Packaging<br>
 *         L De-boning<br>
 *         M Undefined<br>
 *         V Further Processing<br>
 *         W Water Processing<br>
 *         Z Services<br>
 * <br>
 *         Y=<br>
 *         C Chickens<br>
 *         D Duck <br>
 *         T Turkey<br>
 *         M Undefined<br>
 * <br>
 *         Z= Unique product Code (4 digits)<br>
 * <br>
 *         SUFFIX= Identifier for product options. IE: 10l=10 units left turning<br>
 */

public enum ProductGroup {
	Live_Bird_Handling('A'), Defeathering('B'), Evisceration('C'), Chilling('D'), Weighing_Line('E'), Cut_up('F'), Offal_Handling('H'), Sorting_Line('J'), Packaging('K'), Deboning('L'), Undefined('M'), Further_Processing('V'), Water_Treatment('W'), Services('Z'), Project_Costs('*');
	;

	private final char productCodePreFix;

	private ProductGroup(char productCodePreFix) {
		this.productCodePreFix = productCodePreFix;
	}

	public static ProductGroup getFromProductCode(String productCode) {
		char productCodePreFix = productCode.charAt(0);
		if (productCode.substring(1, 3).contains("-")) {
			// is a product code
			for (ProductGroup productGroup : ProductGroup.values()) {
				if (productCodePreFix == productGroup.getProductCodePreFix()) {
					// found matching product group
					return productGroup;
				}
			}
			// not found
			return ProductGroup.Undefined;
		} else {
			// not a product code
			return Project_Costs;
		}

	}

	public char getProductCodePreFix() {
		return productCodePreFix;
	}
}
