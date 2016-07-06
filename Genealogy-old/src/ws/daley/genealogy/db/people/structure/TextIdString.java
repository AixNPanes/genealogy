package ws.daley.genealogy.db.people.structure;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.IdString;

@Entity
public class TextIdString extends IdString
{
	@Transient
    private static final long serialVersionUID = 1L;
    
	@Basic
	private String text;
    public String getText() {return this.text;}
    public void setText(String text) {this.text = text;}

	public TextIdString() {}
	
	public TextIdString(Node node)
	{
		super(node);
		Node valueNode =  this.attributeMap.get("value");
		this.text = "";
		if (valueNode != null)
			this.text = valueNode.getNodeValue();
	}
}
