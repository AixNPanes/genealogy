package ws.daley.genealogy.db.record;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.Gedcom55;
import ws.daley.genealogy.db.structure.header.HeaderCharacterSetStructure;
import ws.daley.genealogy.db.structure.header.HeaderDateStructure;
import ws.daley.genealogy.db.structure.header.HeaderGedcomContentDescriptionStructure;
import ws.daley.genealogy.db.structure.header.HeaderGedcomStructure;
import ws.daley.genealogy.db.structure.header.HeaderPlaceHierarchyStructure;
import ws.daley.genealogy.db.structure.header.HeaderSourceStructure;
import ws.daley.genealogy.util.Util;

/**
 * 
 * @author Tim.Daley
 *				HEADER:=
				n HEAD																	{1:1}
					+1 SOUR <APPROVED_SYSTEM_ID>										{1:1}
						+2 VERS <VERSION_NUMBER>										{0:1} 
						+2 NAME <NAME_OF_PRODUCT>										{0:1} 
						+2 CORP <NAME_OF_BUSINESS>										{0:1} 
							+3 <<ADDRESS_STRUCTURE>>									{0:1} 
						+2 DATA <NAME_OF_SOURCE_DATA>									{0:1} 
							+3 DATE <PUBLICATION_DATE>									{0:1} 
							+3 COPR <COPYRIGHT_SOURCE_DATA>								{0:1} 
					+1 DEST <RECEIVING_SYSTEM_NAME>										{0:1*} 
					+1 DATE <TRANSMISSION_DATE>											{0:1} 
						+2 TIME <TIME_VALUE>											{0:1} 
					+1 SUBM @XREF:SUBM@													{1:1} 
					+1 SUBN @XREF:SUBN@													{0:1} 
					+1 FILE <FILE_NAME>													{0:1} 
					+1 COPR <COPYRIGHT_GEDCOM_FILE>										{0:1} 
					+1 GEDC																{1:1} 
						+2 VERS <VERSION_NUMBER>										{1:1} 
						+2 FORM <GEDCOM_FORM>											{1:1} 
					+1 CHAR <CHARACTER_SET>												{1:1} 
						+2 VERS <VERSION_NUMBER>										{0:1} 
					+1 LANG <LANGUAGE_OF_TEXT>											{0:1} 
					+1 PLAC																{0:1} 
						+2 FORM <PLACE_HIERARCHY>										{1:1} 
					+1 NOTE <GEDCOM_CONTENT_DESCRIPTION>								{0:1} 
						+2 [CONT|CONC] <GEDCOM_CONTENT_DESCRIPTION>						{0:M}

 */

public class HeaderRecord extends GedRecord
{
    private static final long serialVersionUID = 1L;
    
    private Gedcom55 gedcom55;
    public Gedcom55 getGedcom55() {return this.gedcom55;}
    public void setGedcom55(Gedcom55 gedcom55) {this.gedcom55 = gedcom55;}
    
    private HeaderSourceStructure headerSourceStructure;
    public HeaderSourceStructure getHeaderSourceStructure() {return this.headerSourceStructure;}
    public void setSourceStructure(HeaderSourceStructure headerSourceStructure) {this.headerSourceStructure = headerSourceStructure;}
    
    private String characterSet;
    public String getCharacterSet() {return this.characterSet;}
    public void setCharacterSet(String characterSet) {this.characterSet = characterSet;}
    
    private String date;
    public String getDate() {return this.date;}
    public void setDate(String date) {this.date = date;}
    
    private String destination;
    public String getDestination() {return this.destination;}
    public void setDestination(String destination) {this.destination = destination;}
    
    private String file;
    public String getFile() {return this.file;}
    public void setFile(String file) {this.file = file;}
    
    private String copyright;
    public String getCopyright() {return this.copyright;}
    public void setCopyright(String copyright) {this.copyright = copyright;}
    
    private HeaderGedcomStructure headerGedcomStructure;
    public HeaderGedcomStructure getHeaderGedcomStructure() {return this.headerGedcomStructure;}
    public void setHeaderGedcomStructure(HeaderGedcomStructure headerGedcomStructure) {this.headerGedcomStructure = headerGedcomStructure;}
    
    private String submitter;
    public String getSubmitter() {return this.submitter;}
    public void setSubmitter(String submitter) {this.submitter = submitter;}
    
    private String submission;
    public String getSubmission() {return this.submission;}
    public void setSubmission(String submission) {this.submission = submission;}
    
    private String language;
    public String getLanguage() {return this.language;}
    public void setLanguage(String language) {this.language = language;}
    
    private String placeHierarchy;
    public String getPlaceHierarchy() {return this.placeHierarchy;}
    public void setPlaceHierarchy(String placeHierarchy) {this.placeHierarchy = placeHierarchy;}
    
    private HeaderGedcomContentDescriptionStructure headerGedcomContentDescriptionStructure;
    public HeaderGedcomContentDescriptionStructure getHeaderGedcomContentDexcriptionStructure() {return this.headerGedcomContentDescriptionStructure;}
    public void setHeaderGedcomContentDescriptionStructure(HeaderGedcomContentDescriptionStructure headerGedcomContentDexcriptionStructure) {this.headerGedcomContentDescriptionStructure = headerGedcomContentDexcriptionStructure;}
    
    public HeaderRecord() {}
    
    public HeaderRecord(Gedcom55 parent, Node node)
    {
    	super(node);
    	this.gedcom55 = parent;
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("SOUR".equals(childNode.getNodeName()))
				this.setSourceStructure(new HeaderSourceStructure(childNode));
			else if ("CHAR".equals(childNode.getNodeName()))
				this.setCharacterSet((new HeaderCharacterSetStructure(childNode)).getRecordValue());
			else if ("DATE".equals(childNode.getNodeName()))
				this.setDate((new HeaderDateStructure(childNode)).getRecordValue());
			else if ("DEST".equals(childNode.getNodeName()))
				this.setDestination(Util.getNodeValue(childNode));
			else if ("COPR".equals(childNode.getNodeName()))
				this.setCopyright(Util.getNodeValue(childNode));
			else if ("FILE".equals(childNode.getNodeName()))
				this.setFile(Util.getNodeValue(childNode));
			else if ("GEDC".equals(childNode.getNodeName()))
				this.setHeaderGedcomStructure(new HeaderGedcomStructure(childNode));
			else if ("SUBM".equals(childNode.getNodeName()))
				this.setSubmitter(Util.getNodeValue(childNode));
			else if ("SUBN".equals(childNode.getNodeName()))
				this.setSubmission(Util.getNodeValue(childNode));
			else if ("LANG".equals(childNode.getNodeName()))
				this.setLanguage(Util.getNodeValue(childNode));
			else if ("NOTE".equals(childNode.getNodeName()))
				this.setHeaderGedcomContentDescriptionStructure(new HeaderGedcomContentDescriptionStructure(childNode));
			else if ("PLAC".equals(childNode.getNodeName()))
				this.setPlaceHierarchy((new HeaderPlaceHierarchyStructure(childNode)).getRecordValue());
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }
}
