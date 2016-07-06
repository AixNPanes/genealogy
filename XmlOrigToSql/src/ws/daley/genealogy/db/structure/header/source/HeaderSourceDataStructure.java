package ws.daley.genealogy.db.structure.header.source;

import java.io.Serializable;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

public class HeaderSourceDataStructure implements Serializable
{
    private static final long serialVersionUID = 1L;

	private String headerSourceDataValue = "";
    public String getValue() {return this.headerSourceDataValue;}
    public void setHeaderSourceDataValue(String value) {this.headerSourceDataValue = value;}
    
    private String headerSourceDataDate;
    public String getHeaderSourceDataDate() {return this.headerSourceDataDate;}
    public void setHeaderSourceDataDate(String headerSourceDataDate) {this.headerSourceDataDate = headerSourceDataDate;}
    
    private String headerSourceDataCopyright;
    public String getHeaderSourceDataCopyright() {return this.headerSourceDataCopyright;}
    public void setHeaderSourceDataCopyright(String headerSourceDataCopyright) {this.headerSourceDataCopyright = headerSourceDataCopyright;}
    
    public HeaderSourceDataStructure() {}
    
    public HeaderSourceDataStructure(Node node)
    {
		NamedNodeMap attributes = node.getAttributes();
		if (attributes != null)
			this.setHeaderSourceDataValue(Util.getStringAttribute(attributes, "headerSourceDataValue"));
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("DATE".equals(childNode.getNodeName()))
				this.setHeaderSourceDataDate(Util.getNodeValue(childNode));
			else if ("COPR".equals(childNode.getNodeName()))
				this.setHeaderSourceDataCopyright(Util.getNodeValue(childNode));
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }
}
