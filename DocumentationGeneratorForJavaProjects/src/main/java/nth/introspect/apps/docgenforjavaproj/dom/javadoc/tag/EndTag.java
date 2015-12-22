package nth.introspect.apps.docgenforjavaproj.dom.javadoc.tag;

import nth.introspect.apps.docgenforjavaproj.dom.javafile.Regex;


public enum EndTag {
	AUTHOR ,VERSION ,PARAM ,RETURN ,DEPRECATED ,SINCE ,THROWS ,EXCEPTION ,SEE ,SERIAL ,SERIALFIELD ,SERIALDATA;
	
	public Regex asRegex() {
		String name="@"+this.name();
		Regex regex=new Regex().caseUnsensativeMode().literal(name);
		return regex;
	}
	
	
}
