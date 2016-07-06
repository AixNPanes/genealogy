package ws.daley.genealogy.db.structure;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import ws.daley.genealogy.util.Util;

public class SubStructure
{
    private static final long serialVersionUID = 1L;

	private String structureType = "empty";
    public String getStructureType() {return this.structureType;}
    public void setStructureType(String structureType) {this.structureType = structureType;}

	private Long structureLevel = -1l;
    public Long getStructureLevel() {return this.structureLevel;}
    public void setStructureLevel(Long structureLevel) {this.structureLevel = structureLevel;}
    
	private String id = "none";
    public String getId() {return this.id;}
    public void setId(String id) {this.id = id;}

	private String value = "";
    public String getValue() {return this.value;}
    public void setValue(String value) {this.value = value;}
    
    public SubStructure() {}
    
    public SubStructure(Node node)
    {
		if ("#text".equals(node.getNodeName()))
			return;
		this.setStructureType(node.getNodeName());
		NamedNodeMap attributes = node.getAttributes();
		if (attributes != null)
		{
			this.setStructureLevel(Util.getLongAttribute(attributes, "structureLevel"));
			this.setId(Util.getStringAttribute(attributes, "id"));
			this.setValue(Util.getStringAttribute(attributes, "value"));
		}
    }
}
