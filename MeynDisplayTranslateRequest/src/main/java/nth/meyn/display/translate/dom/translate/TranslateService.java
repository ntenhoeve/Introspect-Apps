package nth.meyn.display.translate.dom.translate;

import java.io.IOException;
import java.net.URISyntaxException;

import nth.reflect.fw.gui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.fw.layer1userinterface.controller.DownloadStream;
import nth.reflect.fw.layer1userinterface.controller.UploadStream;
import nth.reflect.fw.layer5provider.reflection.behavior.displayname.DisplayName;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIcon;

@DisplayName(defaultEnglish = "Translate")
@FontIcon(fontIconUrl = FontAwesomeUrl.FLAG)
public class TranslateService {

	private static final String CSV_EXTENTION_FILTER = "*.csv";
	private static final String CSV_LANGUAGE_FILE_EXPORTED_FROM_OMRON_DISPLAY = "CSV language file exported from CX-Designer";

	// public DownloadStream createTranslateRequest(TranslateInfo translateInfo)
	// throws URISyntaxException, IOException {
	// return TranslateFactory.createTranslateRequest(translateInfo);
	// }
	//
	// public TranslateInfo createTranslateRequestParameterFactory() {
	// return new TranslateInfo();
	// }

	public DownloadStream createTranslateRequest(UploadStream uploadStream)
			throws URISyntaxException, IOException {
		TranslateInfo translateInfo = new TranslateInfo();
		translateInfo.setCxDesignerExportFile(uploadStream);
		translateInfo.setTranslateToLanguage("???");
		return TranslateFactory.createTranslateRequest(translateInfo);
	}

	public UploadStream createTranslateRequestParameterFactory() {
		return new UploadStream(CSV_LANGUAGE_FILE_EXPORTED_FROM_OMRON_DISPLAY,
				CSV_EXTENTION_FILTER);
	}

	// public String findAbreviationCandidates(UploadStream uploadStream) throws
	// URISyntaxException,
	// IOException {
	// //FIXME: open in view (not popup)
	// String candidates =
	// TranslateFactory.findAbreviationCandidates(uploadStream.getFile());
	// return candidates;
	// }
	//
	// public UploadStream findAbreviationCandidatesParameterFactory() {
	// return new UploadStream(CSV_LANGUAGE_FILE_EXPORTED_FROM_OMRON_DISPLAY,
	// CSV_EXTENTION_FILTER);
	// }

	// public String validateTranslations(UploadStream uploadStream) throws
	// URISyntaxException,
	// IOException {
	// //FIXME: to implement
	// //FIXME: open in view (not in pop up)
	// String candidates =
	// TranslateFactory.validateTranslations(uploadStream.getFile());
	// return candidates;
	// }
	//
	// public UploadStream validateTranslationsParameterFactory() {
	// return new UploadStream(CSV_LANGUAGE_FILE_EXPORTED_FROM_OMRON_DISPLAY,
	// CSV_EXTENTION_FILTER);
	// }
}
