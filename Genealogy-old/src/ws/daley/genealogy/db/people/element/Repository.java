package ws.daley.genealogy.db.people.element;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.people.structure.Value;

@Entity
public class Repository extends Value
{
	@Transient
    private static final long serialVersionUID = 1L;

    public Repository() {}
    
    public Repository(Node node) {super(node);}
}
