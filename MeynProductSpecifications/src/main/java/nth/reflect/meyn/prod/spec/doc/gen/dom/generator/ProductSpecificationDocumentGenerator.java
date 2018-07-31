package nth.reflect.meyn.prod.spec.doc.gen.dom.generator;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

import nth.reflect.fw.layer1userinterface.controller.DownloadStream;
import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;
import nth.reflect.meyn.prod.spec.doc.gen.dom.order.SalesOrder;
import nth.reflect.meyn.prod.spec.doc.gen.dom.order.SalesOrderService;

public class ProductSpecificationDocumentGenerator {
	
	private SalesOrderService salesOrderService;

	public ProductSpecificationDocumentGenerator(SalesOrderService salesOrderService) {
		this.salesOrderService = salesOrderService;
	}
	

	@ParameterFactory()
	public DownloadStream createSpecificationDocument(SpecificationDocumentParamaters specificationDocumentParamaters) throws InvalidPasswordException, IOException {
		SalesOrder salesOrder=salesOrderService.read();
		return null;
	
	}
}
