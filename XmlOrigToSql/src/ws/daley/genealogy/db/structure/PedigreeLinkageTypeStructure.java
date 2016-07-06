package ws.daley.genealogy.db.structure;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.record.GedRecord;

/**
 * 
 * @author Tim.Daley
 *
 *				CHILD_TO_FAMILY_LINK:= 
				n FAMC @<XREF:FAM>@														{1:1} 
					+1 PEDI <PEDIGREE_LINKAGE_TYPE>										{0:M} 
					+1 <<NOTE_STRUCTURE>>												{0:M}
 */

public class PedigreeLinkageTypeStructure extends GedRecord
{
    private static final long serialVersionUID = 1L;

	public PedigreeLinkageTypeStructure() {}
    
    public PedigreeLinkageTypeStructure(Node node)
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
