REM create a java class file to represent the Sysmac ladder XML from the clipboard
REM First get the Sysmac xml from the clipboad (DataFormat: ladderSnippetXML)
REM Generate an XSD from this xml file with: http://www.xmlforasp.net/CodeBank/System_Xml_Schema/BuildSchema/BuildXMLSchema.aspx
REM Than generate the java class with the following command:
xjc SysmacRungsSchema.xsd
