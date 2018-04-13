package nth.meyn.vltissuelist.dom.issue;

import java.io.File;
import java.util.Map;

import nth.introspect.excel.repository.reader.ExcelTableReader;
import nth.introspect.excel.repository.sheetfilter.AllSheetsFilter;
import nth.introspect.excel.repository.sheetfilter.SheetFilter;

import org.apache.poi.ss.usermodel.Row;

public class IssueExcelRepositoryReader extends ExcelTableReader<Issue> {
	private static final String NUMBER_OF_UNITS = "Number of units";
	private static final String DESIGN_DATE = "Design date";
	private static final String ELECTRICAL_PANEL = "Electrical Panel";
	private static final String DESCRIPTION = "Description";
	
	@Override
	public Issue readRow(Row row, Map<String, Integer> columnIndexes) {
		Issue issue = new Issue();
		String electricSchematic = getString(row, ELECTRICAL_PANEL);
		if (electricSchematic.startsWith("0000")) {
			return null;
		}
		issue.setElectricalSchematic(electricSchematic);
		issue.setProjectDescription(getString(row, DESCRIPTION));
		issue.setDesignDate(getDate(row, DESIGN_DATE));
		issue.setNumberOfVlts(getInteger(row, NUMBER_OF_UNITS));
		return issue;
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { DESCRIPTION, ELECTRICAL_PANEL, DESIGN_DATE,
				NUMBER_OF_UNITS };
	}

	@Override
	protected File getExcelFile() throws Exception {
		File excelFile = new File(IssueService.class.getResource(
				"/VLT Parameter 208 issue-2015-12-20.xlsx").toURI());
		return excelFile;
	}

	@Override
	public SheetFilter getSheetFilter() {
		return new AllSheetsFilter();
	}
}
