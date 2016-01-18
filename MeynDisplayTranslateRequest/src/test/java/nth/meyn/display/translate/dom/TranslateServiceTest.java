package nth.meyn.display.translate.dom;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.generic.util.ClassList;
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
		IntrospectApplicationForJUnit application=new IntrospectApplicationForJUnit() {
			@Override
			public List<Class<?>> getServiceClasses() {
				return new ClassList(TranslateService.class);
			}
		};
		DependencyInjectionContainer container = application.createContainer();
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
