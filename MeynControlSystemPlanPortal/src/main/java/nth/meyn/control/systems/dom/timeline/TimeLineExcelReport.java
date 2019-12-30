package nth.meyn.control.systems.dom.timeline;

import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static java.time.temporal.ChronoField.YEAR;

import java.awt.Point;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import nth.meyn.control.systems.dom.activity.Activity;
import nth.meyn.control.systems.dom.activity.ActivityStatus;
import nth.meyn.control.systems.dom.activity.ActivityType;
import nth.meyn.control.systems.dom.customerorder.CustomerOrder;
import nth.meyn.control.systems.dom.customerorder.CustomerOrderRepository;
import nth.meyn.control.systems.dom.factoryorder.FactoryOrder;
import nth.reflect.fw.layer1userinterface.controller.DownloadStream;

//TODO Activity status late 
//TODO waiting dialog
//TODO executable

public class TimeLineExcelReport {
	private static final int MAX_TITLE_LENGTH = 40;
	private static final int NR_COLUMNS_FOR_WEEK = 7;
	private static final int MONDAY = 1;
	private CustomerOrderRepository customerOrderRepository;

	public TimeLineExcelReport(CustomerOrderRepository customerOrderRepository) {
		this.customerOrderRepository = customerOrderRepository;
	}

	public DownloadStream createTimeLineOverview() throws IOException {
		List<CustomerOrder> orders = customerOrderRepository.allCustomerOrders();

		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheet = workbook.createSheet("Meyn Control Systems-Timeline");

		LocalDate newestOrderWeek = getNewestOrderWeek(orders);
		LocalDate now = getDateOfFirstDayInWeek(new Date());
		LocalDate latestOrderWeek = getLatestOrderWeek(orders);

		Point position = new Point(0, 0);
		for (LocalDate week = newestOrderWeek; week.isAfter(latestOrderWeek); week = week.minusDays(7)) {
			position = addWeek(sheet, position, orders, week, now);
		}

		String fileName = createtFileName();
		DownloadStream downloadStream = WorkBookUtil.createDownloadStream(fileName, workbook);
		return downloadStream;
	}

	private String createtFileName() {
		StringBuilder fileName = new StringBuilder();
		DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive().appendValue(YEAR, 4)
				.appendLiteral('-').appendValue(MONTH_OF_YEAR, 2).appendLiteral('-').appendValue(DAY_OF_MONTH, 2)
				.appendLiteral('-').optionalStart().appendOffset("+HHMMss", "Z").toFormatter();
		fileName.append("MeynControlSystem-Timeline-");
		fileName.append(formatter.format(LocalDateTime.now()));
		fileName.append(".xlsx");
		return fileName.toString();
	}

	private LocalDate getLatestOrderWeek(List<CustomerOrder> orders) {
		CustomerOrder latestOrder = latestActiveProject(orders);
		Date exWorksDate = latestOrder.getExWorksDate();
		LocalDate firstDate = getDateOfFirstDayInWeek(exWorksDate);
		return firstDate;
	}

	private LocalDate getNewestOrderWeek(List<CustomerOrder> orders) {
		CustomerOrder newestOrder = orders.get(orders.size() - 1);
		Date exWorksDate = newestOrder.getExWorksDate();
		LocalDate latestOrderWeek = getDateOfFirstDayInWeek(exWorksDate);
		return latestOrderWeek;
	}

	private CustomerOrder latestActiveProject(List<CustomerOrder> orders) {
		for (CustomerOrder customerOrder : orders) {
			if (!customerOrder.isCompleted() && customerOrder.getExWorksDate().getTime() > 10) {
				return customerOrder;
			}
		}
		// all orders are completed: not realistic, but lets not return null
		return orders.get(orders.size() - 1);
	}

	private Point addWeek(XSSFSheet sheet, Point position, List<CustomerOrder> customerOrders, LocalDate date,
			LocalDate now) {

		long weeksFromNow = ChronoUnit.WEEKS.between(date, now);
		// weeksFromNow : -3=over 3 weeks, +5=5 weeks late

		position = addWeekHeader(sheet, position, date, now, weeksFromNow);

		position = addOrders(sheet, position, customerOrders, date, weeksFromNow);

		position.move(position.x + NR_COLUMNS_FOR_WEEK, 0);

		return position;
	}

	private Point addOrders(XSSFSheet sheet, Point position, List<CustomerOrder> customerOrders, LocalDate date,
			long weeksFromNow) {

		// TODO merge same orders (move factory orders to first similar customer
		// order)
		int yearToFind = date.getYear();
		int WeekToFind = date.get(WeekFields.of(Locale.ENGLISH).weekOfWeekBasedYear());
		for (CustomerOrder customerOrder : customerOrders) {
			if (customerOrder.getFactoryOrders().size() > 0) {
				LocalDate orderDate = customerOrder.getExWorksDate().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate();
				int orderYear = orderDate.getYear();
				int orderWeek = orderDate.get(WeekFields.of(Locale.ENGLISH).weekOfWeekBasedYear());
				if (yearToFind == orderYear && WeekToFind == orderWeek
						&& (weeksFromNow <= 0 || !customerOrder.isCompleted())) {
					position = addOrder(sheet, position, customerOrder);
				}
			}
		}
		return position;
	}

	private Point addOrder(XSSFSheet sheet, Point position, CustomerOrder customerOrder) {

		String shortOrderTitle = createShortCustomerOrderTitle(customerOrder);

		SheetUtil.setCellText(sheet, position, shortOrderTitle, MyCellStyle.CUSTOMER_ORDER_TITLE_STYLE);
		position.translate(1, 0);
		SheetUtil.setCellStyle(sheet, position, MyCellStyle.CUSTOMER_ORDER_TITLE_STYLE);
		position.translate(1, 0);
		SheetUtil.setCellStyle(sheet, position, MyCellStyle.CUSTOMER_ORDER_TITLE_STYLE);
		position.translate(1, 0);
		SheetUtil.setCellStyle(sheet, position, MyCellStyle.CUSTOMER_ORDER_TITLE_STYLE);
		position.translate(1, 0);
		SheetUtil.setCellStyle(sheet, position, MyCellStyle.CUSTOMER_ORDER_TITLE_STYLE);
		position.translate(1, 0);
		SheetUtil.setCellStyle(sheet, position, MyCellStyle.CUSTOMER_ORDER_TITLE_STYLE);
		position.translate(-5, 0);

		String longOrderTitle = createLongCustomerOrderTitle(customerOrder);
		SheetUtil.setCellComment(sheet, position, longOrderTitle, 8, 2);

		SheetUtil.mergeCells(sheet, position, 0, 5);

		position.translate(0, 1);

		for (FactoryOrder factoryOrder : customerOrder.getFactoryOrders()) {
			position = addFactoryOrder(sheet, position, factoryOrder);
		}

		position.translate(0, 1);

		return position;
	}

	private String createLongCustomerOrderTitle(CustomerOrder customerOrder) {
		StringBuilder title = new StringBuilder();
		title.append("PropertyActionMethod:");
		title.append(customerOrder.getCustomerOrderNumber());
		title.append(", Project related costs:");
		title.append(customerOrder.getProjectRelatedCosts());
		title.append("\n");
		title.append(customerOrder.getCustomer());
		return title.toString();
	}

	private String createShortCustomerOrderTitle(CustomerOrder customerOrder) {
		String title = customerOrder.toString();
		if (title.length() > MAX_TITLE_LENGTH + 3) {
			title = title.substring(0, MAX_TITLE_LENGTH) + "...";
		}
		return title;
	}

	private Point addFactoryOrder(XSSFSheet sheet, Point position, FactoryOrder factoryOrder) {
		int xStartPos = position.x;
		SheetUtil.setCellText(sheet, position, factoryOrder.getName(), MyCellStyle.FACTORY_ORDER_NAME_STYLE);
		position.translate(1, 0);

		for (Activity activity : factoryOrder.getActivities()) {

			ActivityStatus status = activity.getStatus();
			switch (status) {
			case ASSIGNED:
				SheetUtil.setCellText(sheet, position, activity.getName().substring(0, 1),
						MyCellStyle.ACTIVITY_ASSIGNED_STYLE);
				SheetUtil.setCellComment(sheet, position, "Assigned to: " + activity.getScheduledFor().getName(), 7, 1);
				break;
			case COMPLETED:
				SheetUtil.setCellText(sheet, position, activity.getName().substring(0, 1),
						MyCellStyle.ACTIVITY_COMPLETED_STYLE);
				SheetUtil.setCellComment(sheet, position, "Completed by: " + activity.getCompletedBy().toString(), 7,
						1);
				break;
			case NOT_APPLICABLE:
				SheetUtil.setCellText(sheet, position, activity.getName().substring(0, 1),
						MyCellStyle.ACTIVITY_NOT_APPLICABLE_STYLE);
				SheetUtil.setCellComment(sheet, position, "Not Applicable", 7, 1);
				break;
			case UNASSIGNED:
				SheetUtil.setCellText(sheet, position, activity.getName().substring(0, 1),
						MyCellStyle.ACTIVITY_UNASSIGNED_STYLE);
				SheetUtil.setCellComment(sheet, position, "Not Assigned", 7, 1);
				break;
			case LATE:
				SheetUtil.setCellText(sheet, position, activity.getName().substring(0, 1),
						MyCellStyle.ACTIVITY_UNASSIGNED_STYLE);
				SheetUtil.setCellComment(sheet, position, "Late", 7, 1);
				break;
			}

			position.translate(1, 0);
		}
		position.move(xStartPos, position.y + 1);
		return position;
	}

	private Point addWeekHeader(XSSFSheet sheet, Point position, LocalDate date, LocalDate now, long weeksFromNow) {

		setColumnWidths(sheet, position);

		StringBuilder text = new StringBuilder();
		text.append("Ex works date: ");
		text.append(date.getYear());
		text.append(" wk ");
		text.append(date.get(WeekFields.of(Locale.ENGLISH).weekOfWeekBasedYear()));
		text.append(" (");

		if (weeksFromNow < -1) {
			text.append(weeksFromNow * -1);
			text.append(" weeks from now)");
		} else if (weeksFromNow == -1) {
			text.append("1 week from now)");
		} else if (weeksFromNow == 0) {
			text.append("this week)");
		} else if (weeksFromNow == 1) {
			text.append("1 week late)");
		} else {
			text.append(weeksFromNow);
			text.append(" weeks late)");
		}

		XSSFWorkbook workBook = sheet.getWorkbook();
		MyCellStyle.createFor(workBook);

		SheetUtil.setCellText(sheet, position, text.toString(), MyCellStyle.WEEK_HEADER_STYLE);
		SheetUtil.mergeCells(sheet, position, 0, 5);

		addStartActivityZoneTextIfNeeded(sheet, position, weeksFromNow);

		position.translate(0, 2);

		return position;
	}

	private void addStartActivityZoneTextIfNeeded(XSSFSheet sheet, Point position, long weeksFromNow) {
		Point position2 = position.getLocation();
		position2.translate(6, 2);
		SheetUtil.mergeCells(sheet, position2, 20, 0);
		for (ActivityType activityType : ActivityType.values()) {
			if (activityType.getStartInWeeksFromNow() == (weeksFromNow * -1)) {
				SheetUtil.setCellText(sheet, position2, activityType.toString(), MyCellStyle.ACTIVITY_ZONE_TEXT);
			}
		}

	}

	private void setColumnWidths(XSSFSheet sheet, Point position) {
		sheet.setColumnWidth(position.x, 8000);
		sheet.setColumnWidth(position.x + 1, 700);
		sheet.setColumnWidth(position.x + 2, 700);
		sheet.setColumnWidth(position.x + 3, 700);
		sheet.setColumnWidth(position.x + 4, 700);
		sheet.setColumnWidth(position.x + 5, 700);
		sheet.setColumnWidth(position.x + 6, 1500);
	}

	private LocalDate getDateOfFirstDayInWeek(Date date) {
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int dayOfWeek = localDate.get(WeekFields.of(Locale.ENGLISH).weekOfWeekBasedYear());
		int daysToSubstract = dayOfWeek - MONDAY;
		localDate = localDate.minusDays(daysToSubstract);
		return localDate;
	}
}
