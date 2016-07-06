package ws.daley.genealogy.db.structure;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.record.GedRecord;

public class SubmitterStructure extends GedRecord
{
    private static final long serialVersionUID = 1L;

    public SubmitterStructure() {}
    
    public SubmitterStructure(Node node)
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
