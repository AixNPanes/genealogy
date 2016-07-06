package ws.daley.genealogy.db.structure.event.individual;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.record.GedRecord;
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

public class FAMCStructure extends GedRecord
{
    private static final long serialVersionUID = 1L;

    private String adoptedByWhichParent;
    public String getAdoptedByWhichParent() {return this.adoptedByWhichParent;}
    public void setAdoptedByWhichParent(String adoptedByWhichParent) {this.adoptedByWhichParent = adoptedByWhichParent;}

    public FAMCStructure(){}

    public FAMCStructure(Node node)
    {
    	super(node);
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("ADOP".equals(childNode.getNodeName()))
				this.setAdoptedByWhichParent(Util.getNodeValue(childNode));
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }
}
