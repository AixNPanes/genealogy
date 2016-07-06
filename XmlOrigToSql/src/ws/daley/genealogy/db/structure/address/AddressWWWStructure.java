package ws.daley.genealogy.db.structure.address;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.record.GedRecord;

/**
 * 
 * @author Tim.Daley
 *
 *	ADDRESS_STRUCTURE:=
		n ADDR <ADDRESS_LINE> {1:1} p.41
			+1 CONT <ADDRESS_LINE> {0:3} p.41
			+1 ADR1 <ADDRESS_LINE1> {0:1} p.41
			+1 ADR2 <ADDRESS_LINE2> {0:1} p.41
			+1 ADR3 <ADDRESS_LINE3> {0:1} p.41
			+1 CITY <ADDRESS_CITY> {0:1} p.41
			+1 STAE <ADDRESS_STATE> {0:1} p.42
			+1 POST <ADDRESS_POSTAL_CODE> {0:1} p.41
			+1 CTRY <ADDRESS_COUNTRY> {0:1} p.41
		n PHON <PHONE_NUMBER> {0:3} p.57
		n EMAIL <ADDRESS_EMAIL> {0:3} p.41
		n FAX <ADDRESS_FAX> {0:3} p.41
		n WWW <ADDRESS_WEB_PAGE> {0:3} p.42
 */
public class AddressWWWStructure extends GedRecord
{
    private static final long serialVersionUID = 1L;
    
    public AddressWWWStructure() {}
    
    public AddressWWWStructure(Node node)
    {
    	super(node);
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }
}
