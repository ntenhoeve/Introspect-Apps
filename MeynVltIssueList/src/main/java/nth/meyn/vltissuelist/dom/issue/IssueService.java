package nth.meyn.vltissuelist.dom.issue;

import java.io.IOException;

import nth.introspect.layer1userinterface.controller.DownloadStream;
import nth.introspect.layer1userinterface.controller.UploadStream;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.meyn.vltissuelist.dom.vlt.VltFile;

public class IssueService {

	private final IssueExcelRepository2 issueExcelRepository2;

	public IssueService(IssueExcelRepository2 issueExcelRepository2) {
		this.issueExcelRepository2 = issueExcelRepository2;
	}

	// @Hidden()
	// public List<Issue> issues() throws Exception {
	// List<Issue> issues = issueExcelRepository.readAll();
	//
	// List<Customer> customers = customerRepository.getAll();
	// for (Issue issue : issues) {
	// System.out.println(issues.indexOf(issue) + " of " + issues.size());
	// setCustomerInfo(customers, issue);
	// setFolderInfo(issue);
	// }
	// return issues;
	// }
	//
	// @Hidden
	// public DownloadStream issuesToExcel() throws Exception {
	// List<Issue> issues = issues();
	// XSSFWorkbook workbook = issueExcelRepository.createWorkbook();
	// String MaterialAppBarTitle="Meyn-VLT 804 issue";
	// issueExcelRepository.writeAsTable(workbook, MaterialAppBarTitle, Issue.class, issues);
	// DownloadStream downloadStream =
	// issueExcelRepository.createDownloadStream(workbook, MaterialAppBarTitle);
	// return downloadStream;
	// }

	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION)
	public DownloadStream updateIssueList() throws Exception {
		DownloadStream downloadStream = issueExcelRepository2.update();
		return downloadStream;
	}

	// private void setFolderInfo(Issue issue) {
	// try {
	// File projectFolder = meynControlFolderFinder.find(issue
	// .getElectricalSchematic());
	// issue.setProjectFolder(projectFolder);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// }
	//
	// private void setCustomerInfo(List<Customer> customers, Issue issue) {
	// Customer customer = null;
	// try {
	// Integer customerNr = Integer.parseInt(issue
	// .getElectricalSchematic().subSequence(0, 4).toString());
	// customer = findCustomer(customers, customerNr);
	// } catch (NumberFormatException exception) {
	// }
	// if (customer == null) {
	// issue.setCustomerNumber(0);
	// issue.setCustomerName(UNKNOWN);
	// issue.setCustomerCountry(UNKNOWN);
	// issue.setCustomerCity(UNKNOWN);
	// } else {
	// issue.setCustomerNumber(customer.getNumber());
	// issue.setCustomerName(customer.getName());
	// issue.setCustomerCountry(customer.getCountry());
	// issue.setCustomerCity(customer.getCity());
	// }
	// }
	//
	// private Customer findCustomer(List<Customer> customers, Integer
	// customerNr) {
	// for (Customer customer : customers) {
	// if (customer.getNumber() == customerNr) {
	// return customer;
	// }
	// }
	// return null;
	// }

	public VltReport checkVltFile(UploadStream uploadStream) throws IOException {
		VltFile vltFile = new VltFile(uploadStream.getFile());
		VltReportImpl vltReport = new VltReportImpl(vltFile);
		return vltReport;
	}

	public UploadStream checkVltFileParameterFactory() {
		UploadStream uploadStream = new UploadStream("Danfoss VLT file", "ssp");
		return uploadStream;
	}

}
