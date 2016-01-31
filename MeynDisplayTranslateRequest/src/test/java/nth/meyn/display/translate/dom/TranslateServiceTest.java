package nth.meyn.display.translate.dom;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.layer1userinterface.controller.UploadStream;
import nth.introspect.ui.junit.IntrospectApplicationForJUnit;
import nth.meyn.display.translate.dom.translate.TranslateFactory;
import nth.meyn.display.translate.dom.translate.TranslateService;

import org.junit.Before;
import org.junit.Test;

public class TranslateServiceTest {

	private TranslateService translateService;

	@Before
	public void setUp() throws Exception {
		DependencyInjectionContainer container = new IntrospectApplicationForJUnit().addServiceClass(TranslateService.class).createContainer();
		translateService = container.get(TranslateService.class);
	}

	@Test
	public void testCreateTranslateRequest() throws URISyntaxException, IOException {
		Path path = Paths.get(TranslateFactory.class.getResource(
				"/sourceFileToTranslate.csv").toURI());
		InputStream inputStream = Files.newInputStream(path);
		UploadStream uploadStream=new UploadStream(new File("Dontcare"), inputStream);
		//translateService.createTranslateRequest(uploadStream);
		translateService.createTranslateRequest();
		// TODO asserts
	}

	@Test
	public void findAbreviationCandidates() throws URISyntaxException, IOException {
		String candidates=translateService.findAbreviationCandidates();
		assertEquals("[ON, ETR, OK, V, AMA, TO, LOW, OFF, YOU, IGBT]", candidates);
	}
}
