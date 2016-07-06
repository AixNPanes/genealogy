package ws.daley.genealogy.db.people.element;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.people.structure.Text;

@Entity
public class Title extends Text
{
	@Transient
    private static final long serialVersionUID = 1L;

	public Title() {super();}
	
	public Title(Node node) {super(node);}
}
