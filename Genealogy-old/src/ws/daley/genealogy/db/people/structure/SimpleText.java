package ws.daley.genealogy.db.people.structure;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.IdNumber;

@Entity
public class SimpleText extends IdNumber
{
	@Transient
    private static final long serialVersionUID = 1L;

	@Basic
    private String text;
    public String getText() {return this.text;}
    public void setText(String newText) {this.text = newText;}
    public void addText(String newText) {this.setText(this.getText() + newText);}

	public SimpleText() {super();}
	
	public SimpleText(Node node)
	{
		super(node);
		Node valueNode =  this.attributeMap.get("value");
		this.text = "";
		if (valueNode != null)
			this.text = valueNode.getNodeValue();
	}
}
