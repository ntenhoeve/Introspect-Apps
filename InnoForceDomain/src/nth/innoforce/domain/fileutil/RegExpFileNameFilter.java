package nth.innoforce.domain.fileutil;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpFileNameFilter implements FileFilter {

	private Pattern pattern;

	public RegExpFileNameFilter(String regExp) {
		pattern = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
	}

	@Override
	public boolean accept(File file) {
		Matcher matcher = pattern.matcher(file.getName());
		return matcher.matches();
	}

}
