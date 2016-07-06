package ws.daley.genealogy.db.people.structure;

import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.apache.xerces.dom.DeferredElementNSImpl;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.Instruction;

@Entity
public class Text extends SimpleText
{
	@Transient
    private static final long serialVersionUID = 1L;

	@Transient
    protected HashMap<String, Node> attributeMap = new HashMap<String, Node>();
    
	private static Instruction[] instructions = new Instruction[] {
    };

	public Text() {}
	
	public Text(Node node)
	{
		super(node);
		Instruction.buildChildren(this, instructions, this.nodeChildren);
		NodeList myChildren = node.getChildNodes();
		for(int i = 0; i < myChildren.getLength(); i++)
		{
			Node child = myChildren.item(i);
			if (child instanceof DeferredElementNSImpl)
			{
				if ("CONT".equals(child.getNodeName()))
					this.addText("\n");
				this.addText(child.getNodeValue());
			}
		}
	}
}
