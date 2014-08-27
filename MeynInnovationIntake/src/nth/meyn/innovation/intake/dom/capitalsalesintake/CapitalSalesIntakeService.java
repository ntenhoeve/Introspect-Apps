package nth.meyn.innovation.intake.dom.capitalsalesintake;

import java.util.Calendar;
import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.container.IntrospectContainer;
import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.domain.info.method.MethodInfo.ExecutionModeType;
import nth.introspect.provider.domain.info.valuemodel.annotations.ExecutionMode;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;
import nth.introspect.provider.userinterface.DownloadStream;
import nth.introspect.report.msexcel.item.ExcelReportFactory;
import nth.introspect.report.msexcel.item.ExportTableToExcelItem;
import nth.introspect.valuemodel.ReadOnlyValueModel;
import nth.meyn.innovation.intake.database.BaanDatabase;
import nth.meyn.innovation.intake.dom.projectlaunch.ProjectLaunch;
import nth.meyn.innovation.intake.dom.projectlaunch.ProjectLaunchService;

public class CapitalSalesIntakeService {

	private final ProjectLaunchService projectLaunchService;
	private final ExcelReportFactory excelReportFactory;

	public CapitalSalesIntakeService(ProjectLaunchService projectLaunchService, ExcelReportFactory excelReportFactory) {
		this.projectLaunchService = projectLaunchService;
		this.excelReportFactory = excelReportFactory;
	}
	
	@GenericReturnType(OrderLine.class)
	@ExecutionMode(ExecutionModeType.EDIT_PARAMETER_THAN_EXECUTE_METHOD_OR_CANCEL)
	@SuppressWarnings("unchecked")
	public List<OrderLine> capitalSalesIntake(Period period) throws Exception {
		
		BaanDatabase baanDatabase=new BaanDatabase(); //TODO inject Introspect.getDataAccessProvider(BaanDatabase.class);
		StringBuffer sql=new StringBuffer();
		sql.append("select T2.T$ORNO as orderNumber, T2.T$ODAT as orderDate, to_number(T2.T$CPRJ) as projectNumber,T2.T$ITEM as productCode,T3.T$DSCA as description,T2.T$OQUA as count,T1.T$NAMA as customerName,T1.T$CCTY as country,T1.T$CREG as salesRegion,T2.T$AMTA as amount,T2.T$LPRC as listPrice,T2.T$MPRC as minimumSalesPrice,T2.T$TPRC as transferPrice ");
		sql.append(" from baan.ttccom010100 T1,baan.ttdsls041100 T2,baan.ttipcs020100 T3 ");
		sql.append(" where (T2.T$CUNO = T1.T$CUNO) and (T2.T$CPRJ = T3.T$CPRJ) and (T2.T$ORNO >= 104690) and (T2.T$ORNO <= 199999) and (T2.T$ODAT >= to_date('0101");
		sql.append(period.getYear());
		sql.append("','DDMMYYYY')) and (T2.T$ODAT <= to_date('3112");
		sql.append(period.getYear());
		sql.append("','DDMMYYYY'))");
		List<OrderLine> orderLines = (List<OrderLine>) baanDatabase.getResultList(sql.toString(), OrderLine.class);
		//set orderline type of all orderLines
		
		List<ProjectLaunch> projectLaunchs = projectLaunchService.allProjectLaunches();
		for (OrderLine orderLine:orderLines) {
			orderLine.setOrderLineType(OrderLineType.INTAKE_OLD_PRODUCTS);
			for (ProjectLaunch projectLaunch: projectLaunchs) {
				if (projectLaunch.orderLineCountsAsInnovationRevenue(orderLine)) {
					orderLine.setOrderLineType(OrderLineType.INTAKE_NEW_PRODUCTS);
					break;
				}
			}
		}
		return orderLines;
	}
	
	public Period capitalSalesIntakeParameterFactory()  {
		Period year=new Period();
		year.setYear(Calendar.getInstance().get(Calendar.YEAR));
		return year;
	}
	
	
	@ExecutionMode(ExecutionModeType.EDIT_PARAMETER_THAN_EXECUTE_METHOD_OR_CANCEL)
	public DownloadStream capitalSalesIntakeToExcel(Period period) throws Exception  {
		final List<OrderLine> orderLines = capitalSalesIntake(period);
		StringBuilder reportTitle = new StringBuilder("Meyn Capital Sales Intake (");
		reportTitle.append(period.getYear());
		reportTitle.append(") ");
		DownloadStream downloadStream = excelReportFactory.createReport(orderLines, OrderLine.class, reportTitle.toString());
		return downloadStream;
	}
	
	public Period capitalSalesIntakeToExcelParameterFactory()  {
		Period year=new Period();
		year.setYear(Calendar.getInstance().get(Calendar.YEAR));
		return year;
	}
	
	@ExecutionMode(ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public OrderLine viewOrderLine(OrderLine orderLine) {
		return orderLine;
	}
}
