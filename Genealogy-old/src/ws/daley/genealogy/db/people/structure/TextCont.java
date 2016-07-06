package ws.daley.genealogy.db.people.structure;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.w3c.dom.Node;

@Entity
public class TextCont extends SimpleText
{
	@Transient
    private static final long serialVersionUID = 1L;

	public TextCont(Node node) {super(node);}
}
