package nth.meyn.innovation.intake.dom.capitalsalesintake;

import java.util.Calendar;
import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.provider.domain.DomainProvider;
import nth.introspect.provider.domain.info.method.MethodInfo.FormModeType;
import nth.introspect.provider.domain.info.valuemodel.annotations.FormMode;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;
import nth.introspect.provider.report.ReportProvider;
import nth.introspect.report.msexcel.MsExcelReportProvider;
import nth.introspect.report.msexcel.item.ExportTableToExcelItem;
import nth.introspect.valuemodel.ReadOnlyValueModel;
import nth.meyn.innovation.intake.database.BaanDatabase;
import nth.meyn.innovation.intake.dom.projectlaunch.ProjectLaunch;
import nth.meyn.innovation.intake.dom.projectlaunch.ProjectLaunchService;

public class CapitalSalesIntakeService {

	@GenericReturnType(OrderLine.class)
	@FormMode(FormModeType.editParameterThenExecuteMethodOrCancel)
	@SuppressWarnings("unchecked")
	public List<OrderLine> capitalSalesIntake(Period period) throws Exception {
		
		BaanDatabase baanDatabase=(BaanDatabase) Introspect.getDataAccessProvider(BaanDatabase.class);
		StringBuffer sql=new StringBuffer();
		sql.append("select T2.T$ORNO as orderNumber, T2.T$ODAT as orderDate, to_number(T2.T$CPRJ) as projectNumber,T2.T$ITEM as productCode,T3.T$DSCA as description,T2.T$OQUA as count,T1.T$NAMA as customerName,T1.T$CCTY as country,T1.T$CREG as salesRegion,T2.T$AMTA as amount,T2.T$LPRC as listPrice,T2.T$MPRC as manufactoringPrice,T2.T$TPRC as transferPrice ");
		sql.append(" from baan.ttccom010100 T1,baan.ttdsls041100 T2,baan.ttipcs020100 T3 ");
		sql.append(" where (T2.T$CUNO = T1.T$CUNO) and (T2.T$CPRJ = T3.T$CPRJ) and (T2.T$ORNO >= 104690) and (T2.T$ORNO <= 199999) and (T2.T$ODAT >= to_date('0101");
		sql.append(period.getYear());
		sql.append("','DDMMYYYY')) and (T2.T$ODAT <= to_date('3112");
		sql.append(period.getYear());
		sql.append("','DDMMYYYY'))");
		List<OrderLine> orderLines = (List<OrderLine>) baanDatabase.getResultList(sql.toString(), OrderLine.class);
		//set orderline type of all orderLines
		
		DomainProvider domainProvider=Introspect.getDomainProvider();
		ProjectLaunchService projectLaunchService = (ProjectLaunchService) domainProvider.getServiceObject(ProjectLaunchService.class);
		
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
	
	
	@FormMode(FormModeType.editParameterThenExecuteMethodOrCancel)
	//TODO do this better (this was a hot fix) 
	public void capitalSalesIntakeToExcel(Period period) throws Exception  {
		final List<OrderLine> orderLines = capitalSalesIntake(period);
		
		ReadOnlyValueModel valueModel = new ReadOnlyValueModel() {
			
			@Override
			public Class<?> getValueType() {
				return OrderLine.class;
			}
			
			@Override
			public Object getValue() {
				return orderLines;
			}
			
			@Override
			public boolean canGetValue() {
				return true;
			}
		};
		
		StringBuilder reportTitle = new StringBuilder("Meyn Capital Sales Intake (");
		reportTitle.append(period.getYear());
		reportTitle.append(") ");
		ExportTableToExcelItem item=new ExportTableToExcelItem(valueModel, "create excel", reportTitle.toString());
		
		item.exportToExcel();
		
	}
	
	public Period capitalSalesIntakeToExcelParameterFactory()  {
		Period year=new Period();
		year.setYear(Calendar.getInstance().get(Calendar.YEAR));
		return year;
	}
}
