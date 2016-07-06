package ws.daley.genealogy.db;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

@Entity
public class IdString extends GenealogyItem
{
	private static final long serialVersionUID = 1L;	

	@Id
	private String idString;
	public String getIdString() {return this.idString;}
	public void setIdString(String id) {this.idString = id;}
	
	public IdString() {super();}
	
	public IdString(Node node)
	{
		super(node);
		NamedNodeMap namedNodeMap = node.getAttributes();
		if (namedNodeMap != null)
		{
			Node namedItem = namedNodeMap.getNamedItem("id");
			if (namedItem != null)
				this.idString = namedItem.getNodeValue();
		}
	}
}
