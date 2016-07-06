package ws.daley.genealogy.db.structure.header.source;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.record.SubRecord;
import ws.daley.genealogy.db.structure.AddressStructure;
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

public class HeaderSourceCorporationStructure extends SubRecord
{
    private static final long serialVersionUID = 1L;
    
    private String headerSourceCorporation;
    public String getHeaderSourceCorporation() {return this.headerSourceCorporation;}
    public void setHeaderSourceCorporation(String headerSourceCorporation) {this.headerSourceCorporation = headerSourceCorporation;}
    
    private AddressStructure headerSourceCorporationAddress;
    public AddressStructure getHeaderSourceCorporationAddressStructure() {return this.headerSourceCorporationAddress;}
    public void setHeaderSourceCorporationAddressStructure(AddressStructure headerSourceCorporationAddress) {this.headerSourceCorporationAddress = headerSourceCorporationAddress;}
    
    public HeaderSourceCorporationStructure() {}
    
    public HeaderSourceCorporationStructure(Node node)
    {
    	super(node);
    	this.setHeaderSourceCorporation(Util.getNodeValue(node));
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("ADDR".equals(childNode.getNodeName()))
			{
				this.setHeaderSourceCorporationAddressStructure(new AddressStructure(childNode));
			}
			else if ("PHON".equals(childNode.getNodeName()))
			{
				this.getHeaderSourceCorporationAddressStructure().addPhoneNumberStructure(Util.getNodeValue(childNode));
			}
			else if ("EMAIL".equals(childNode.getNodeName()))
			{
				this.getHeaderSourceCorporationAddressStructure().addAddressEmailStructure(Util.getNodeValue(childNode));
			}
			else if ("FAX".equals(childNode.getNodeName()))
			{
				this.getHeaderSourceCorporationAddressStructure().addAddressFaxStructure(Util.getNodeValue(childNode));
			}
			else if ("WWW".equals(childNode.getNodeName()))
			{
				this.getHeaderSourceCorporationAddressStructure().addAddressWWWStructure(Util.getNodeValue(childNode));
			}
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }
}
