package ws.daley.genealogy.db.structure.attribute.individual;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.record.GedRecord;
import ws.daley.genealogy.db.structure.AddressStructure;
import ws.daley.genealogy.db.structure.MultimediaLinkStructure;
import ws.daley.genealogy.db.structure.NoteStructure;
import ws.daley.genealogy.db.structure.PlaceStructure;
import ws.daley.genealogy.db.structure.SourceCitationStructure;
import ws.daley.genealogy.db.structure.event.EventDetailStructure;
import ws.daley.genealogy.util.Util;

/**
 * 
 * @author Tim.Daley
 *
 *				INDIVIDUAL_ATTRIBUTE_STRUCTURE:= 
				[ 
				n CAST <CASTE_NAME> 													{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n DSCR <PHYSICAL_DESCRIPTION> 											{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n EDUC <SCHOLASTIC_ACHIEVEMENT> 										{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n IDNO <NATIONAL_ID_NUMBER> 											{1:1}* 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n NATI <NATIONAL_OR_TRIBAL_ORIGIN> 										{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n NCHI <COUNT_OF_CHILDREN> 												{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n NMR <COUNT_OF_MARRIAGES> 												{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n OCCU <OCCUPATION> 													{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n PROP <POSSESSIONS> 													{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n RELI <RELIGIOUS_AFFILIATION> 											{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n RESI 																	{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n SSN <SOCIAL_SECURITY_NUMBER> 											{0:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n TITL <NOBILITY_TYPE_TITLE>											{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				]
 */

public class IndividualAttributeStructure extends GedRecord
{
    private static final long serialVersionUID = 1L;

    private String attributeType;
    public String getAttributeType() {return this.attributeType;}
    public void setAttributeType(String attributeType) {this.attributeType = attributeType;}

	private EventDetailStructure eventDetail= new EventDetailStructure(this.getClass());
    public EventDetailStructure getEventDetail() {return this.eventDetail;}
    public void setEventDetail(EventDetailStructure eventDetail) {this.eventDetail = eventDetail;}
    
	public IndividualAttributeStructure() {}
    
    public IndividualAttributeStructure(Node node)
    {
    	super(node);
		this.setEventDetail(new EventDetailStructure(node));
    	this.setAttributeType(node.getNodeName());
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("TYPE".equals(childNode.getNodeName()))
				this.getEventDetail().setEventDescriptor(Util.getNodeValue(childNode));
			else if ("DATE".equals(childNode.getNodeName()))
				this.getEventDetail().setDate(Util.getNodeValue(childNode));
			else if ("PLAC".equals(childNode.getNodeName()))
				this.getEventDetail().setPlaceStructure(new PlaceStructure(childNode));
			else if ("ADDR".equals(childNode.getNodeName()))
				this.getEventDetail().setAddressStructure(new AddressStructure(childNode));
			else if ("PHON".equals(childNode.getNodeName()))
				this.getEventDetail().getAddressStructure().addPhoneNumberStructure(Util.getNodeValue(childNode));
			else if ("ADDR".equals(childNode.getNodeName()))
				this.getEventDetail().getAddressStructure().addAddressEmailStructure(Util.getNodeValue(childNode));
			else if ("FAX".equals(childNode.getNodeName()))
				this.getEventDetail().getAddressStructure().addAddressFaxStructure(Util.getNodeValue(childNode));
			else if ("WWW".equals(childNode.getNodeName()))
				this.getEventDetail().getAddressStructure().addAddressWWWStructure(Util.getNodeValue(childNode));
			else if ("SOUR".equals(childNode.getNodeName()))
				this.getEventDetail().addSourceCitationStructure(new SourceCitationStructure(childNode));
			else if ("OBJE".equals(childNode.getNodeName()))
				this.getEventDetail().addMultimediaLinkStructure(new MultimediaLinkStructure(childNode));
			else if ("NOTE".equals(childNode.getNodeName()))
				this.getEventDetail().addNoteStructure(new NoteStructure(childNode));
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }

}
