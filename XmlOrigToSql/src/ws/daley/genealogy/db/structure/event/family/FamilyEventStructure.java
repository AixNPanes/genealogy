package ws.daley.genealogy.db.structure.event.family;

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
 *				FAMILY_EVENT_STRUCTURE:= 
				[ 
				n ANUL [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n DIV [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n DIVF [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n ENGA [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n MARR [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n MARB [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n MARC [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n MARL [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n MARS [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n CENS [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n EVEN																	{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				]
 */

/**
 * 
 * @author Tim.Daley
 *
 *				EVENT_DETAIL:= 
				n TYPE <EVENT_DESCRIPTOR>												{0:1} 
				n DATE <DATE_VALUE>														{0:1}
				n <<PLACE_STRUCTURE>>													{0:1} 
				n <<ADDRESS_STRUCTURE>>													{0:1} 
				n AGE <AGE_AT_EVENT>													0:1} 
				n AGNC <RESPONSIBLE_AGENCY>												{0:1} 
				n CAUS <CAUSE_OF_EVENT>													{0:1} 
				n <<SOURCE_CITATION>>													{0:M} 
				n <<MULTIMEDIA_LINK>>													{0:M}
				n <<NOTE_STRUCTURE>>													{0:M}
 */

public class FamilyEventStructure extends GedRecord
{
    private static final long serialVersionUID = 1L;

    private String eventType;
    public String getEventType() {return this.eventType;}
	public void setEventType(String eventType) {this.eventType = eventType;}

    private EventDetailStructure eventDetail/* = new EventDetailStructure(this.getClass())*/;
    public EventDetailStructure getEventDetail() {return this.eventDetail;}
    public void setEventDetail(EventDetailStructure eventDetail) {this.eventDetail = eventDetail;}
    
	public FamilyEventStructure() {}
    
    public FamilyEventStructure(Node node)
    {
    	super(node);
		this.setEventDetail(new EventDetailStructure(node));
    	this.setEventType(node.getNodeName());
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
