package ws.daley.genealogy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;

import org.apache.openjpa.persistence.OpenJPAEntityManager;
import org.apache.xerces.dom.DeferredDocumentImpl;

import ws.daley.genealogy.db.Gedcom55;
import ws.daley.genealogy.gedcom.GedDocument;
import ws.daley.genealogy.util.Util;
import ws.daley.genealogy.xml.Line;
import ws.daley.genealogy.xml.XmlLine;
import ws.daley.genealogy.xml.XmlLinesGroup;
import ws.daley.genealogy.xml.XmlLinesSection;

public class Ged2XML
{
	private ArrayList<Line> lines = new ArrayList<Line>();
	public ArrayList<Line> getLines() {return this.lines;}

	private XmlLinesGroup xmlLinesGroup = new XmlLinesGroup(); 
	public XmlLinesGroup getXmlLines() {return this.xmlLinesGroup;}

	private XmlLinesSection xmlLinesSection = null;
	public XmlLinesSection getXmlLinesSection() {return this.xmlLinesSection;}
	public void setXmlLinesSection(XmlLinesSection section) {this.xmlLinesSection = section;}

	public static void generateSQL(OpenJPAEntityManager em, String gedFile, String wildcardXsdFile) throws Throwable
	{
		File inFile = Util.getInFile(gedFile);
		String buffer = Util.getString(inFile);
		Ged2XML gedcom = new Ged2XML(buffer);
	    ByteArrayInputStream is = gedcom.generateXml(buffer);
	    DeferredDocumentImpl document = GedDocument.parse(wildcardXsdFile, is);
	    Gedcom55 gedcom55 = new Gedcom55(document.getChildNodes().item(0));
	    gedcom55.processInstructions(em);
	}
	
	private Ged2XML(String buffer)
	{
		Line line = new Line();
		for(int i = 0; i < buffer.length();i += line.getLen() + line.getCrlnLen())
		{
			line = new Line(buffer, i);
			this.getLines().add(line);
			XmlLine xmlLine = new XmlLine(line, true);
			this.getXmlLines().add(xmlLine);
		}
	}
	
	public ByteArrayInputStream generateXml(String buffer)
	{
		XmlLinesSection sect = new XmlLinesSection(this.getXmlLines());
		this.setXmlLinesSection(sect);
		ByteArrayOutputStream baos = new ByteArrayOutputStream(buffer.length()*10);
		PrintStream ps = new PrintStream(baos);
		ps.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		ps.println("<gedcom55>"); // xmlns=\"file:///C/EclipseProjects/Genealogy/Ged2GedXML55/gedcom55.dtd\";>
		this.getXmlLinesSection().generateXml(ps, false);
		ps.println("</gedcom55>");
		ps.close();
	    return new ByteArrayInputStream(baos.toByteArray());
	}
}
