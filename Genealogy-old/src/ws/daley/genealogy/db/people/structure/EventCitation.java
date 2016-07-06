package ws.daley.genealogy.db.people.structure;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.Instruction;
import ws.daley.genealogy.db.people.element.EventRole;

@Entity
public class EventCitation extends Text
{
	@Transient
    private static final long serialVersionUID = 1L;
    
	@OneToMany
	private ArrayList<EventRole> eventRole = new ArrayList<EventRole>();
    public ArrayList<EventRole> getEventRole() {return this.eventRole;}
    public void setEventRole(ArrayList<EventRole> eventRole) {this.eventRole = eventRole;}
    public void addEventRole(EventRole newEventRole)
    {
    	if (this.getEventRole() == null)
    		this.setEventRole(new ArrayList<EventRole>());
    	this.getEventRole().add(newEventRole);
    }
    
	@Transient
    private static Instruction[] instructions = new Instruction[] {
    	new Instruction("EVEN", "eventRole")
    };

	public EventCitation() {super();}
    
    public EventCitation(Node node)
    {
    	super(node);
		Instruction.buildChildren(this, instructions, this.nodeChildren);
    }
}
