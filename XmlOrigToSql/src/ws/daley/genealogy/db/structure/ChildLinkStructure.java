package ws.daley.genealogy.db.structure;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.record.GedRecord;
import ws.daley.genealogy.util.Util;

/**
 * 
 * @author Tim.Daley
 *
 *				CHILD_TO_FAMILY_LINK:= 
				n FAMC @<XREF:FAM>@														{1:1} 
					+1 PEDI <PEDIGREE_LINKAGE_TYPE>										{0:M} 
					+1 <<NOTE_STRUCTURE>>												{0:M}
 */

public class ChildLinkStructure extends GedRecord
{
    private static final long serialVersionUID = 1L;
    
    private String fatherRelationship;
    public String getFatherRelationship() {return this.fatherRelationship;}
    public void setFatherRelationship(String fatherRelationship) {this.fatherRelationship = fatherRelationship;}
    
    private String motherRelationship;
    public String getMotherRelationship() {return this.motherRelationship;}
    public void setMotherRelationship(String motherRelationship) {this.motherRelationship = motherRelationship;}

	public ChildLinkStructure() {}
    
    public ChildLinkStructure(Node node)
    {
    	super(node);
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("_FREL".equals(childNode.getNodeName()))
				this.setFatherRelationship(Util.getNodeValue(childNode));
			else if ("_MREL".equals(childNode.getNodeName()))
				this.setMotherRelationship(Util.getNodeValue(childNode));
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }
}
