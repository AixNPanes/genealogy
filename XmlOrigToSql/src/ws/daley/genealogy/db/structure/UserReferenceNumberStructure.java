package ws.daley.genealogy.db.structure;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.record.GedRecord;
import ws.daley.genealogy.util.Util;

/**
 * 
 * @author Tim.Daley
 *
 *				NOTE_RECORD:=
				n @<XREF:NOTE>@ NOTE <SUBMITTER_TEXT>									{1:1} 
					+1 [ CONC | CONT] <SUBMITTER_TEXT>									{0:M} 
					+1 <<SOURCE_CITATION>>												{0:M} 
					+1 REFN <USER_REFERENCE_NUMBER>										{0:M}
						+2 TYPE <USER_REFERENCE_TYPE>									{0:1} 
					+1 RIN <AUTOMATED_RECORD_ID>										{0:1} 
					+1 <<CHANGE_DATE>>													{0:1}

 */

public class UserReferenceNumberStructure extends GedRecord
{
    private static final long serialVersionUID = 1L;
    
    private String userReferenceType;
    public String getUserReferenceType() {return this.userReferenceType;}
    public void setUserReferenceType(String userReferenceType) {this.userReferenceType = userReferenceType;}
    
    public UserReferenceNumberStructure() {}
    
    public UserReferenceNumberStructure(Node node)
    {
    	super(node);
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("TYPE".equals(childNode.getNodeName()))
				this.setUserReferenceType(Util.getNodeValue(childNode));
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }
}
