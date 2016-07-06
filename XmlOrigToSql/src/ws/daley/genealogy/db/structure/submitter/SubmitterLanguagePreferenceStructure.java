package ws.daley.genealogy.db.structure.submitter;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.record.GedRecord;

/**
 * 
 * @author Tim.Daley
 *
 *				SUBMITTER_RECORD:=

				n @<XREF:SUBM>@ SUBM													{1:1} 
					+1 NAME <SUBMITTER_NAME>											{1:1} 
					+1 <<ADDRESS_STRUCTURE>>											{0:1} 
					+1 <<MULTIMEDIA_LINK>>												{0:M} 
					+1 LANG <LANGUAGE_PREFERENCE>										{0:3} 
					+1 RFN <SUBMITTER_REGISTERED_RFN>									{0:1} 
					+1 RIN <AUTOMATED_RECORD_ID>										{0:1} 
					+1 <<CHANGE_DATE>>													{0:1}
 */

public class SubmitterLanguagePreferenceStructure extends GedRecord
{
    private static final long serialVersionUID = 1L;
    
    public SubmitterLanguagePreferenceStructure() {}
    
    public SubmitterLanguagePreferenceStructure(Node node)
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
