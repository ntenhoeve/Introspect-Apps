package nth.meyn.display.translate.dom.translate;

import java.io.IOException;
import java.net.URISyntaxException;

import nth.introspect.layer1userinterface.controller.DownloadStream;
import nth.introspect.layer1userinterface.controller.UploadStream;

public class TranslateService {

	//public DownloadStream createTranslateRequest(UploadStreamParameterAction uploadStream) throws URISyntaxException, IOException {
	public DownloadStream createTranslateRequest() throws URISyntaxException, IOException {
		//FIXME take InputStream (file open dialog) as argument
		return TranslateFactory.createTranslateRequest();
	}
	

	public String findAbreviationCandidates() throws URISyntaxException,
			IOException {
		//FIXME take InputStream (file open dialog) as argument
		//FIXME: open in view (not popup)
		String candidates = TranslateFactory.findAbreviationCandidates();
		return candidates;
	}
	
	public String validateTranslations() throws URISyntaxException,
	IOException {
		//FIXME: to implement
		//FIXME: open in view (not in pop up)
		//FIXME take InputStream (file open dialog) as argument
		String candidates = TranslateFactory.validateTranslations();
		return candidates;
	}
}
