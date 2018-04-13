package nth.meyn.control.systems.dom.timeline;

import java.awt.Color;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public enum MyCellStyle {
	WEEK_HEADER_STYLE(createWeekHeaderStyleFactory()), ACTIVITY_ZONE_TEXT(
			createActivityZoneTextStyleFactory()), CUSTOMER_ORDER_TITLE_STYLE(
			createCustomerOrderTitleStyleFactory()), FACTORY_ORDER_NAME_STYLE(
			createFactoryOrderStyleFactory()), ACTIVITY_ASSIGNED_STYLE(
			createActivityAssignedStyle()), ACTIVITY_COMPLETED_STYLE(
			createActivityCompletedStyle()), ACTIVITY_NOT_APPLICABLE_STYLE(
			createActivityNotCompletedStyle()), ACTIVITY_UNASSIGNED_STYLE(
			createActivityUnAssignedStyle());

	private final CellStyleFactory cellStyleFactory;

	MyCellStyle(CellStyleFactory cellStyleFactory) {
		this.cellStyleFactory = cellStyleFactory;
	}

	private static CellStyleFactory createActivityUnAssignedStyle() {
		return new CellStyleFactory() {

			@Override
			public XSSFCellStyle create(XSSFWorkbook workbook) {
				return createActivityStyle(workbook, Color.WHITE);
			}
		};
	}

	private static CellStyleFactory createActivityNotCompletedStyle() {
		return new CellStyleFactory() {

			@Override
			public XSSFCellStyle create(XSSFWorkbook workbook) {
				return createActivityStyle(workbook, new Color(122, 166, 146));
			}
		};
	}

	private static CellStyleFactory createActivityCompletedStyle() {
		return new CellStyleFactory() {

			@Override
			public XSSFCellStyle create(XSSFWorkbook workbook) {
				return createActivityStyle(workbook, new Color(0, 120, 91));
			}
		};
	}

	private static CellStyleFactory createActivityAssignedStyle() {
		return new CellStyleFactory() {

			@Override
			public XSSFCellStyle create(XSSFWorkbook workbook) {
				return createActivityStyle(workbook, Color.YELLOW);
			}
		};
	}

	protected static XSSFCellStyle createActivityStyle(XSSFWorkbook workbook,
			Color color) {
		XSSFCellStyle activityStyle = workbook.createCellStyle();
		activityStyle.setBorderTop(BorderStyle.THIN);
		activityStyle.setBorderRight(BorderStyle.THIN);
		activityStyle.setBorderBottom(BorderStyle.THIN);
		activityStyle.setBorderLeft(BorderStyle.THIN);
		activityStyle.setAlignment(HorizontalAlignment.CENTER);
		activityStyle.setFillForegroundColor(new XSSFColor(color));
		activityStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		return activityStyle;
	}

	private static CellStyleFactory createFactoryOrderStyleFactory() {
		return new CellStyleFactory() {

			@Override
			public XSSFCellStyle create(XSSFWorkbook workbook) {
				XSSFCellStyle factoryOrderNameStyle = workbook
						.createCellStyle();
				factoryOrderNameStyle.setBorderTop(BorderStyle.THIN);
				factoryOrderNameStyle.setBorderRight(BorderStyle.THIN);
				factoryOrderNameStyle.setBorderBottom(BorderStyle.THIN);
				factoryOrderNameStyle.setBorderLeft(BorderStyle.THIN);
				return factoryOrderNameStyle;
			}
		};
	}

	private static CellStyleFactory createCustomerOrderTitleStyleFactory() {
		return new CellStyleFactory() {

			@Override
			public XSSFCellStyle create(XSSFWorkbook workbook) {
				XSSFCellStyle customerOrderTitleStyle = workbook
						.createCellStyle();
				customerOrderTitleStyle.setBorderTop(BorderStyle.THIN);
				customerOrderTitleStyle.setBorderRight(BorderStyle.THIN);
				customerOrderTitleStyle.setBorderBottom(BorderStyle.THIN);
				customerOrderTitleStyle.setBorderLeft(BorderStyle.THIN);
				customerOrderTitleStyle
						.setAlignment(HorizontalAlignment.CENTER);
				Font font = workbook.createFont();
				font.setBoldweight(Font.BOLDWEIGHT_BOLD);
				customerOrderTitleStyle.setFont(font);
				return customerOrderTitleStyle;
			}
		};
	}

	private static CellStyleFactory createActivityZoneTextStyleFactory() {

		return new CellStyleFactory() {

			@Override
			public XSSFCellStyle create(XSSFWorkbook workbook) {
				XSSFCellStyle activityZoneStyle = workbook.createCellStyle();
				activityZoneStyle.setAlignment(HorizontalAlignment.CENTER);
				Font font = workbook.createFont();
				font.setBoldweight(Font.BOLDWEIGHT_BOLD);
				font.setFontHeight((short) 400);
				activityZoneStyle.setFont(font);
				activityZoneStyle.setRotation((short) 90);
				activityZoneStyle.setAlignment(HorizontalAlignment.CENTER);
				activityZoneStyle.setVerticalAlignment(VerticalAlignment.TOP);
				return activityZoneStyle;
			}
		};
	}

	private static CellStyleFactory createWeekHeaderStyleFactory() {
		return new CellStyleFactory() {

			@Override
			public XSSFCellStyle create(XSSFWorkbook workbook) {
				XSSFCellStyle weekHeaderStyle = workbook.createCellStyle();
				weekHeaderStyle.setAlignment(HorizontalAlignment.CENTER);
				Font font = workbook.createFont();
				font.setBoldweight(Font.BOLDWEIGHT_BOLD);
				weekHeaderStyle.setFont(font);
				return weekHeaderStyle;
			}
		};
	}

	public int index() {
		MyCellStyle[] values = values();
		int index = 0;
		for (MyCellStyle cellStyle : values) {
			if (cellStyle == this) {
				return index + 1;
			}
			index++;
		}
		return -1;
	}

	public CellStyleFactory getCellStyleFactory() {
		return cellStyleFactory;
	}

	public static void createFor(XSSFWorkbook workbook) {
		for (MyCellStyle cellStyle : values()) {
			cellStyle.getCellStyleFactory().create(workbook);
		}
	}

	public XSSFCellStyle getCellStyle(XSSFCell cell) {
		XSSFWorkbook workbook = cell.getRow().getSheet().getWorkbook();
		XSSFCellStyle cellStyle = workbook.getCellStyleAt((short) index());
		return cellStyle;
	}

}
