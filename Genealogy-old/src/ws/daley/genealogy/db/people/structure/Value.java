package ws.daley.genealogy.db.people.structure;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.IdNumber;

@Entity
public class Value extends IdNumber
{
	@Transient
    private static final long serialVersionUID = 1L;
    
	@Basic
    private String value;
    public String getValue() {return this.value;}
    public void setValud(String newValue) {this.value = newValue;}

    public Value() {super();}

    public Value(Node node)
    {
    	super(node);
    	this.value = this.attributeMap.get("value").getNodeValue();
    }
}
