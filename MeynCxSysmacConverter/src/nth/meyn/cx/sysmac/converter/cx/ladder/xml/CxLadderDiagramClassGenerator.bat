REM create a java class file to represent the CX-One ladder XML from the clipboard
REM First get the Cx-One xml from the clipboad (DataFormat: OMRON POU Ladder ClipBoard Format)
REM Generate an XSD from this xml file with: http://www.xmlforasp.net/CodeBank/System_Xml_Schema/BuildSchema/BuildXMLSchema.aspx
REM Than generate the java class with the following command:
xjc CxLadderDiagramSchema.xsd
