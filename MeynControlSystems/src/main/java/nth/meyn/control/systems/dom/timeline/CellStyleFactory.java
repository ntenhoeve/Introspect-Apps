package nth.meyn.control.systems.dom.timeline;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface CellStyleFactory {
  public XSSFCellStyle create(XSSFWorkbook workbook) ;
}
