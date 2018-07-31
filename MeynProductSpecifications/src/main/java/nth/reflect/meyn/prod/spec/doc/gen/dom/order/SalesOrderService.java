package nth.reflect.meyn.prod.spec.doc.gen.dom.order;

import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;

import nth.reflect.fw.layer5provider.reflection.behavior.hidden.Hidden;


public class SalesOrderService {

	public SalesOrder read() throws InvalidPasswordException, IOException {
		InputStream inputStream = SalesOrderService.class.getResourceAsStream("SalesOrder.pdf");
		PDDocument document = PDDocument.load(inputStream);
		PDFTextStripper stripper = new PDFTextStripper();
		String text = stripper.getText(document);
		return new SalesOrder();
	}

}
