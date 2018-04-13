package nth.meyn.vltissuelist.dom.folder.customer;

import java.io.File;
import java.io.FileFilter;
import java.security.InvalidParameterException;

import nth.introspect.generic.regex.Regex;
import nth.introspect.generic.regex.Repetition;

public class CustomerFolderFilter implements FileFilter {

	private Regex regex;

	public CustomerFolderFilter() {
		regex=new Regex().beginOfLine().decimal(Repetition.times(4)).literal("-");
	}
	
	public CustomerFolderFilter(String customerNumber) {
		Regex fourDigits=new Regex().beginOfLine().decimal(Repetition.times(4));
		if (customerNumber==null || customerNumber.length()<4 || !fourDigits.hasMatchIn(customerNumber)) {
			throw new InvalidParameterException("Invalid CustomerNumber: "+customerNumber);
		}
		customerNumber=customerNumber.substring(0,4);
		regex=new Regex().beginOfLine().literal(customerNumber).literal("-");
	}


	public boolean accept(File file) {
		return file.isDirectory() && regex.hasMatchIn(file.getName());
	}

}
