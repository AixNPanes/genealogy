package ws.daley.genealogy.db.structure.event.individual;

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
 *				INDIVIDUAL_EVENT_STRUCTURE:= 
				[ 
				n BIRT [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
					+1 FAMC @<XREF:FAM>@												{0:1} 
				| 
				n CHR [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
					+1 FAMC @<XREF:FAM>@												{0:1} 
				| 
				n ADOP [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
					+1 FAMC @<XREF:FAM>@												{0:1} 
						+2 ADOP <ADOPTED_BY_WHICH_PARENT>								{0:1} 
				| 
				n DEAT [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n BURI [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n CREM [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n BAPM [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n BARM [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n BASM [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n BLES [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n CHRA [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n CONF [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n FCOM [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n ORDN [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n NATU [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n EMIG [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n IMMI [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n PROB [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n WILL [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n GRAD [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n RETI [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n CENS [Y|<NULL>]													{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n EVEN																	{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				]
 */

public class IndividualEventStructure extends GedRecord
{
    private static final long serialVersionUID = 1L;

    private String eventType;
    public String getEventType() {return this.eventType;}
	public void setEventType(String eventType) {this.eventType = eventType;}

    private String famc;
    public String getFamc() {return this.famc;}
    public void setFamc(String famc) {this.famc = famc;}

    private EventDetailStructure eventDetail;
    public EventDetailStructure getEventDetail() {return this.eventDetail;}
    public void setEventDetail(EventDetailStructure eventDetail) {this.eventDetail = eventDetail;}
    
	public IndividualEventStructure() {}
    
    public IndividualEventStructure(Node node)
    {
    	super(node);
    	this.setEventType(node.getNodeName());
		this.setEventDetail(new EventDetailStructure(node));
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
			else if ("FAMC".equals(childNode.getNodeName()))
				this.setFamc(Util.getNodeValue(childNode));
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }

}
