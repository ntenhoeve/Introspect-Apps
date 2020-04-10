package nth.meyn.vltissuelist.dom.issue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import nth.meyn.vltissuelist.dom.folder.customer.CustomerFolder;
import nth.meyn.vltissuelist.dom.folder.mcs.MeynControlSystemsFolder;
import nth.meyn.vltissuelist.dom.folder.plc.PlcFolder;
import nth.meyn.vltissuelist.dom.folder.project.ProjectFolder;
import nth.meyn.vltissuelist.dom.folder.software.SoftwareFolder;
import nth.meyn.vltissuelist.dom.folder.vlt.VltFolder;
import nth.meyn.vltissuelist.dom.vlt.VltFile;
import nth.reflect.fw.layer1userinterface.controller.DownloadStream;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.infra.excel.repository.ExcelRepository;

public class IssueExcelRepository2 extends ExcelRepository<Issue> {

	private static final String UNKNOWN = "Unknown";

	private static final String TITLE = "VLT Parameter 804 issue";

	private static final int ELECTRIC_SCHEMATIC_COLUMN = 4;
	private static final int NR_OF_VLTS_COLUMN = 9;
	private static final int PRIORITY_COLUMN = 10;
	private static final int CAN_READ_VLT_FILES_COLUMN = 11;
	private static final int HAS_WIRED_QUICKSTOP_COLUMN = 12;
	private static final int PARAMETER_804_OK_COLUMN = 13;
	private static final int PARAMETER_CORRECTION_DATE_COLUMN = 14;
	private static final int NR_OF_VLT_FILES_COLUMN = 15;
	private static final int ERROR_COLUMN = 16;
	private static final int ALTERNATIVE_PROJECT_FOLDER_COLUMN = 17;
	private static final int PROJECT_FOLDER_COLUMN_LINK = 18;
	private static final int VLT_FOLDER_START_COLUMN = 19;

	private final XSSFWorkbook workbook;
	private final XSSFCellStyle hlinkstyle;
	private String error="";

	public IssueExcelRepository2(ReflectionProvider reflectionProvider)
			throws URISyntaxException, IOException {
		super(reflectionProvider);
		File excelFile = searchForLastModifiedExcelFile();

		// File excelFile = new File(getClass().getResource(
		// "/" + TITLE + "-2015-12-22-15-07.xlsx").toURI());
		if (excelFile == null) {
			workbook = new XSSFWorkbook();
		} else {
			workbook = readExcelFile(excelFile);
		}
		hlinkstyle = workbook.createCellStyle();
		XSSFFont hlinkfont = workbook.createFont();
		hlinkfont.setUnderline(XSSFFont.U_SINGLE);
		hlinkfont.setColor(HSSFColor.BLUE.index);
		hlinkstyle.setFont(hlinkfont);

	}

	private File searchForLastModifiedExcelFile() throws URISyntaxException, FileNotFoundException {
		File appFolder = new File("//meyn.nl/Project/BESTURINGSTECHNIEK/VLT Parameter 804 issue");
		if (!appFolder.exists()) {
			return null;
		}
		File lastModifiedExcelFile = null;
		for (File file : appFolder.listFiles()) {
			if (file.isFile() && file.getName().contains(".xlsx") && file.getName().contains(TITLE)
					&& (lastModifiedExcelFile == null
							|| file.lastModified() >= lastModifiedExcelFile.lastModified())) {
				lastModifiedExcelFile = file;
			}
		}
		return lastModifiedExcelFile;
	}

	public DownloadStream update() throws IOException {
		MeynControlSystemsFolder meynControlSystemsFolder = new MeynControlSystemsFolder();
		Map<String, CustomerFolder> customerFolders = meynControlSystemsFolder
				.getCustomerFoldersMap();

		XSSFSheet sheet = workbook.getSheetAt(0);
		for (Row row : sheet) {
			int rowNr = row.getRowNum();
			if (rowNr > 1) {// skip header
				System.out.println(error);
				error = "";
				System.out.print(row.getRowNum() + " ");
				update(row, customerFolders);

			}
			// if ((rowNr ^50) ==0) {
			// write(workbook);
			// }
		}
		DownloadStream downloadStream = createDownloadStream(workbook, TITLE);

		return downloadStream;
	}

	private void update(Row row, Map<String, CustomerFolder> customerFolders)
			throws FileNotFoundException, IOException {
		ProjectFolder projectFolder = getProjectFolder(row, customerFolders);
		if (projectFolder != null) {
			List<VltFolder> vltFolders = getVltFolders(projectFolder);
			List<VltFile> vltFiles = getVltFiles(vltFolders);
			VltReport vltReport = getVltReport(vltFiles);

			updatePriority(row, vltReport);
			updateCanReadVltFile(row, vltReport);
			updateVltWithWiredQuickstop(row, vltReport);
			updateVltParameter804Correct(row, vltReport, vltFiles);
			updateNumberOfVltFiles(row, vltFiles);
			updateError(row);
			updateProjectFolder(row, projectFolder);
			updateVltFolders(row, vltFolders);
		} else {
			updateError(row);
		}
	}

	private ProjectFolder getProjectFolder(Row row, Map<String, CustomerFolder> customerFolders) {
		try {
			String alternativeProjectFolder = getOrCreateCell(row,
					ALTERNATIVE_PROJECT_FOLDER_COLUMN).getStringCellValue();
			if (alternativeProjectFolder != null && !alternativeProjectFolder.isEmpty()) {
				return new ProjectFolder(new File(alternativeProjectFolder));
			} else {
				String electricSchematic = getOrCreateCell(row, ELECTRIC_SCHEMATIC_COLUMN)
						.getStringCellValue();
				String customerNumber = electricSchematic.substring(0, 4);
				CustomerFolder customerFolder = customerFolders.get(customerNumber);
				if (customerFolder==null) {
					throw new RuntimeException("Could not find customer folder for customer number: "+customerNumber);
				}
				ProjectFolder projectFolder = customerFolder.getSystemsFolder()
						.getProjectFolder(electricSchematic);
				return projectFolder;
			}
		} catch (Exception e) {
			error = e.getMessage();
			return null;
		}
	}

	private void updatePriority(Row row, VltReport vltReport) {
		Cell cell = getOrCreateCell(row, PRIORITY_COLUMN);
		String priority;
		if (vltReport.isAllVltsStopAndTripAfterDeviceNetError() != null
				&& vltReport.isAllVltsStopAndTripAfterDeviceNetError() == true) {
			priority = "Ok";
		} else if (vltReport.isReadSuccesfull() == false) {
			priority = "Priority-Unknown";
		} else if (vltReport.isContainsVltWithWiredQuickstop() == true) {
			priority = "Priority-Low";
		} else {
			priority = "Priority-High";
		}
		cell.setCellValue(priority);
	}

	private void updateCanReadVltFile(Row row, VltReport vltReport) {
		Cell cell = getOrCreateCell(row, CAN_READ_VLT_FILES_COLUMN);
		cell.setCellValue(vltReport.isReadSuccesfull());
	}

	private void updateProjectFolder(Row row, ProjectFolder projectFolder) {
		Cell cell = getOrCreateCell(row, PROJECT_FOLDER_COLUMN_LINK);
		if (projectFolder == null) {
			cell.setCellValue("");
		} else {
			CreationHelper createHelper = workbook.getCreationHelper();
			cell.setCellValue("PROJECT folder");
			XSSFHyperlink link = (XSSFHyperlink) createHelper.createHyperlink(Hyperlink.LINK_FILE);
			link.setAddress(projectFolder.getPath().toURI().toString());
			cell.setHyperlink((XSSFHyperlink) link);
			cell.setCellStyle(hlinkstyle);
		}
	}

	private void updateVltFolders(Row row, List<VltFolder> vltFolders) {

		for (int index = 0; index <= 9; index++) {
			Cell cell = getOrCreateCell(row, VLT_FOLDER_START_COLUMN + index);
			if (index <= vltFolders.size() - 1) {
				VltFolder vltFolder = vltFolders.get(index);
				CreationHelper createHelper = workbook.getCreationHelper();
				cell.setCellValue("VLT folder " + index + 1);
				XSSFHyperlink link = (XSSFHyperlink) createHelper
						.createHyperlink(Hyperlink.LINK_FILE);
				link.setAddress(vltFolder.getPath().toURI().toString());
				cell.setHyperlink((XSSFHyperlink) link);
				cell.setCellStyle(hlinkstyle);
			} else {
				cell.setCellValue("");
			}
		}
	}

	private void updateNumberOfVltFiles(Row row, List<VltFile> vltFiles) {
		Cell cell = getOrCreateCell(row, NR_OF_VLT_FILES_COLUMN);
		cell.setCellValue(vltFiles.size());
	}

	private List<VltFile> getVltFiles(List<VltFolder> vltFolders) {

		List<VltFile> vltFiles = new ArrayList<>();
		for (VltFolder vltFolder : vltFolders) {
			try {
				vltFiles.addAll(vltFolder.getVltFiles());
			} catch (IOException e) {
				error = e.getMessage();
			}
		}
		return vltFiles;
	}

	private void updateError(Row row) {
		Cell cell = getOrCreateCell(row, ERROR_COLUMN);
		cell.setCellValue(error);
	}

	private List<VltFolder> getVltFolders(ProjectFolder projectFolder) {
		try {
			SoftwareFolder softwareFolder = projectFolder.getSoftwareFolder();
			List<PlcFolder> latestPlcFolders = softwareFolder.getLatestPlcFolders();
			latestPlcFolders = removeRehangerFolders(latestPlcFolders);
			latestPlcFolders = removeMettlerFolders(latestPlcFolders);
			List<VltFolder> vltFolders = getVltFolders(latestPlcFolders);
			return vltFolders;
		} catch (Exception e) {
			error = e.getMessage();
			return new ArrayList<>();
		}

	}

	private List<VltFolder> getVltFolders(List<PlcFolder> latestPlcFolders)
			throws FileNotFoundException {
		List<VltFolder> vltFolders = new ArrayList<>();
		for (PlcFolder plcFolder : latestPlcFolders) {
			try {
				VltFolder vltFolder = plcFolder.getVltFolder();
				vltFolders.add(vltFolder);
			} catch (Exception e) {
				error = e.getMessage();
			}
		}
		return vltFolders;
	}

	private List<PlcFolder> removeRehangerFolders(List<PlcFolder> plcFolders) {
		List<PlcFolder> foundFolders = new ArrayList<>();
		for (PlcFolder plcFolder : plcFolders) {
			if (!plcFolder.getFirstLetters().equals("BUF")) {
				foundFolders.add(plcFolder);
			}
		}
		return foundFolders;
	}

	private List<PlcFolder> removeMettlerFolders(List<PlcFolder> plcFolders) {
		List<PlcFolder> foundFolders = new ArrayList<>();
		for (PlcFolder plcFolder : plcFolders) {
			if (!plcFolder.getFirstLetters().equals("METTLER")) {
				foundFolders.add(plcFolder);
			}
		}
		return foundFolders;
	}

	private VltReport getVltReport(List<VltFile> vltFiles)
			throws FileNotFoundException, IOException {
		if (vltFiles.size() == 0) {
			return new VltReportImpl(false, null, null, null);
		}
		VltReportImpl vltReport = null;
		for (VltFile vltFile : vltFiles) {
			if (vltReport == null) {
				vltReport = new VltReportImpl(vltFile);
			} else {
				vltReport.and(vltFile);
			}
		}
		return vltReport;
	}

	private void updateVltParameter804Correct(Row row, VltReport vltReport,
			List<VltFile> vltFiles) {
		Cell parameter804OkCell = getOrCreateCell(row, PARAMETER_804_OK_COLUMN);
		Cell parameterCorrectionDateCell = getOrCreateCell(row, PARAMETER_CORRECTION_DATE_COLUMN);
		if (vltReport != null && vltReport.isReadSuccesfull()) {
			if (vltReport.isAllVltsStopAndTripAfterDeviceNetError()) {
				parameter804OkCell.setCellValue(true);
				Date date = getLastModified(vltFiles);
				parameterCorrectionDateCell.setCellValue(date);
			} else {
				parameter804OkCell.setCellValue(false);
				parameterCorrectionDateCell.setCellValue("");
			}
		} else {
			parameter804OkCell.setCellValue(UNKNOWN);
			parameterCorrectionDateCell.setCellValue("");
		}
	}

	private Date getLastModified(List<VltFile> vltFiles) {
		Date date = new Date();
		long lastModified = 0;
		for (VltFile vltFile : vltFiles) {
			if (vltFile.getFile().lastModified() > lastModified) {
				lastModified = vltFile.getFile().lastModified();
			}
		}
		date.setTime(lastModified);
		return date;
	}

	private void updateVltWithWiredQuickstop(Row row, VltReport vltReport) {
		Cell cell = getOrCreateCell(row, HAS_WIRED_QUICKSTOP_COLUMN);
		if (vltReport == null || !vltReport.isReadSuccesfull()) {
			cell.setCellValue(UNKNOWN);
		} else {
			cell.setCellValue(vltReport.isContainsVltWithWiredQuickstop());
		}
	}

	private Cell getOrCreateCell(Row row, int columnNr) {
		Cell cell = row.getCell(columnNr);
		if (cell == null) {
			cell = row.createCell(columnNr);
		}
		return cell;
	}

}
