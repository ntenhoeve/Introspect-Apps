package nth.meyn.display.translate.dom.translate;

import org.apache.bval.constraints.NotEmpty;

import nth.reflect.fw.layer1userinterface.controller.UploadStream;
import nth.reflect.fw.layer5provider.reflection.behavior.displayname.DisplayName;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public class TranslateInfo {

	private UploadStream cxDesignerExportFile;
	private String translateToLanguage;
	private static final String CSV_EXTENTION_FILTER = "*.csv";
	private static final String CSV_LANGUAGE_FILE_EXPORTED_FROM_OMRON_DISPLAY = "CSV language file exported from CX-Designer";

	
	public TranslateInfo() {
		cxDesignerExportFile=new UploadStream(CSV_LANGUAGE_FILE_EXPORTED_FROM_OMRON_DISPLAY, CSV_EXTENTION_FILTER);
	}
	
	@NotEmpty
	@Order(sequenceNumber=1)
	@DisplayName(englishName="CX Designer export file")
	public UploadStream getCxDesignerExportFile() {
		return cxDesignerExportFile;
	}
	public void setCxDesignerExportFile(UploadStream cxDesignerExportFile) {
		this.cxDesignerExportFile = cxDesignerExportFile;
	}
	
	@NotEmpty
	@Order(sequenceNumber=2)
	public String getTranslateToLanguage() {
		return translateToLanguage;
	}
	public void setTranslateToLanguage(String translateToLanguage) {
		this.translateToLanguage = translateToLanguage;
	}
	
	
}
