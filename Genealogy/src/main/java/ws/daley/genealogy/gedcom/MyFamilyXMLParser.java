package ws.daley.genealogy.gedcom;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class MyFamilyXMLParser
{
	DocumentBuilder documentBuilder;

	public MyFamilyXMLParser()
	{
		try {this.documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();}
		catch (ParserConfigurationException e) {throw new RuntimeException(e);}
	}

	public Document getDocument(InputStream inputStream) throws SAXException, IOException
	{
		return this.documentBuilder.parse(inputStream);
	}
}
