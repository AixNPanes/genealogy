package ws.daley.genealogy.db.people.element;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.people.structure.Text;

@Entity
public class Sex extends Text
{
	@Transient
    private static final long serialVersionUID = 1L;
	
	public Sex() {}
	
	public Sex(Node node) {super(node);}
	
	public void processInstruction()
	{
		throw new RuntimeException("");
	}
}
