package nth.meyn.control.systems.dom.timeline;

import java.awt.Point;

import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SheetUtil {

	public static void setCellText(XSSFSheet sheet, Point position, String text) {
		XSSFCell cell = SheetUtil.getCell(sheet, position);
		cell.setCellValue(text);
	}

	public static XSSFCell getCell(XSSFSheet sheet, Point position) {
		XSSFRow row = sheet.getRow(position.y);
		if (row==null) {
			row=sheet.createRow(position.y);
		}
		XSSFCell cell = row.getCell(position.x);
		if (cell==null) {
			cell=row.createCell(position.x);
		}
		return cell;
	}

	public static void mergeCells(XSSFSheet sheet, Point position, int nrOfRows, int nrOfColumns) {
		sheet.addMergedRegion(new CellRangeAddress(position.y, position.y+nrOfRows,position.x, position.x+nrOfColumns));
	}

	public static void setCellStyle(XSSFSheet sheet, Point position,
			MyCellStyle myCellStyle) {
		XSSFCell cell = getCell(sheet, position);
		XSSFCellStyle cellStyle = myCellStyle.getCellStyle(cell);
		cell.setCellStyle(cellStyle);
	}

	public static void setCellText(XSSFSheet sheet, Point position,
			String text, MyCellStyle myCellStyle) {
		setCellText(sheet, position, text);
		setCellStyle(sheet, position, myCellStyle);
	}

	public static void setCellComment(XSSFSheet sheet, Point position,
			String text, int width, int height) {
		XSSFCell cell = getCell(sheet, position);
		
		Drawing drawing = sheet.createDrawingPatriarch();

		XSSFWorkbook workbook=sheet.getWorkbook();
		CreationHelper factory = workbook.getCreationHelper();

	    ClientAnchor anchor = factory.createClientAnchor();
	    anchor.setCol1(position.x);
	    anchor.setCol2(position.x+width);
	    anchor.setRow1(position.y);
	    anchor.setRow2(position.y+height);

	    // Create the comment and set the text+author
	    Comment comment = drawing.createCellComment(anchor);
	    RichTextString str = factory.createRichTextString(text);
	    comment.setString(str);
//	    comment.setAuthor("Apache POI");

	    // Assign the comment to the cell
	    cell.setCellComment(comment);
	}

}
