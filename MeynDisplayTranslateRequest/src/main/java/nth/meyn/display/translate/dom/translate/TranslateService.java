package nth.meyn.display.translate.dom.translate;

import java.io.IOException;
import java.net.URISyntaxException;

import nth.introspect.layer1userinterface.controller.DownloadStream;
import nth.introspect.layer1userinterface.controller.UploadStream;

public class TranslateService {

	private static final String CSV = "csv";
	private static final String CSV_LANGUAGE_FILE_EXPORTED_FROM_OMRON_DISPLAY = "CSV language file exported from Omron Display";

	public DownloadStream createTranslateRequest(UploadStream uploadStream) throws URISyntaxException, IOException {
		return TranslateFactory.createTranslateRequest(uploadStream.getFile());
	}
	
	public UploadStream createTranslateRequestParameterFactory() {
		return new UploadStream(CSV_LANGUAGE_FILE_EXPORTED_FROM_OMRON_DISPLAY, CSV);
	}

	public String findAbreviationCandidates(UploadStream uploadStream) throws URISyntaxException,
			IOException {
		//FIXME: open in view (not popup)
		String candidates = TranslateFactory.findAbreviationCandidates(uploadStream.getFile());
		return candidates;
	}
	
	public UploadStream findAbreviationCandidatesParameterFactory() {
		return new UploadStream(CSV_LANGUAGE_FILE_EXPORTED_FROM_OMRON_DISPLAY, CSV);
	}
	
//	public String validateTranslations(UploadStream uploadStream) throws URISyntaxException,
//	IOException {
//		//FIXME: to implement
//		//FIXME: open in view (not in pop up)
//		String candidates = TranslateFactory.validateTranslations(uploadStream.getFile());
//		return candidates;
//	}
//	
	public UploadStream validateTranslationsParameterFactory() {
		return new UploadStream(CSV_LANGUAGE_FILE_EXPORTED_FROM_OMRON_DISPLAY, CSV);
	}
}
