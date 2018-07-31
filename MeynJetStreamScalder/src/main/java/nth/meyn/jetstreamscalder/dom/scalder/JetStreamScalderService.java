package nth.meyn.jetstreamscalder.dom.scalder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import nth.reflect.fw.layer1userinterface.controller.DownloadStream;
import nth.reflect.fw.layer5provider.notification.NotificationProvider;
import nth.reflect.infra.generic.xml.XmlConverter;

public class JetStreamScalderService {

	
	private final XmlConverter xmlConverter;
	private final NotificationProvider notificationProvider;

	//FIXME: this constructor is causing CloneUtil not being able to create a clone, which is needed to edit the object before it is commited (in ordeer to be able to cancel)
	public JetStreamScalderService(XmlConverter xmlConverter, NotificationProvider notificationProvider) {
		this.xmlConverter = xmlConverter;
		this.notificationProvider = notificationProvider;
	}
	
	public DownloadStream newJetStreamScalderConfiguration(
			JetStreamScalder jetStreamScalder) throws Exception {
		String xml = xmlConverter.marshal(jetStreamScalder, true);
		InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
		String customer = jetStreamScalder.getCustomer();
		StringBuilder fileName = new StringBuilder("Meyn Jet Stream Scalder-");
		if (customer == null || customer.trim().length() == 0) {
			fileName.append("Customer-Location");
		} else {
			fileName.append(customer);
		}
		fileName.append(".xml");
		File file = new File(fileName.toString());
		DownloadStream downloadStream = new DownloadStream(file, inputStream);
		return downloadStream;
	}

	public JetStreamScalder newJetStreamScalderConfigurationParameterFactory() {
		return new JetStreamScalder(notificationProvider);
	}

	public List<ScaldingMethod> scaldingMethods() {
		ScaldingMethod[] scaldingMethods = ScaldingMethod.values();
		List<ScaldingMethod> scaldingMethodList = Arrays
				.asList(scaldingMethods);
		return scaldingMethodList;  
	}
}
