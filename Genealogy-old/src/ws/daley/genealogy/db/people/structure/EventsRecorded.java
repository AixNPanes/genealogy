package ws.daley.genealogy.db.people.structure;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.Instruction;
import ws.daley.genealogy.db.people.element.EventsDate;
import ws.daley.genealogy.db.people.element.EventsPlace;

@Entity
public class EventsRecorded extends Text
{
	@Transient
    private static final long serialVersionUID = 1L;

    @OneToMany
    private ArrayList<EventsDate> eventsDate = new ArrayList<EventsDate>();
    public ArrayList<EventsDate> getEventsDate() {return this.eventsDate;}
    public void setEventsDate(ArrayList<EventsDate> eventsDate) {this.eventsDate = eventsDate;}
    public void addEventsDate(EventsDate newEventsDate)
    {
    	if (this.getEventsDate() == null)
    		this.setEventsDate(new ArrayList<EventsDate>());
    	this.getEventsDate().add(newEventsDate);
    }

    @OneToMany
    private ArrayList<EventsPlace> eventsPlace = new ArrayList<EventsPlace>();
    public ArrayList<EventsPlace> getEventsPlace() {return this.eventsPlace;}
    public void setEventsPlace(ArrayList<EventsPlace> eventsPlace) {this.eventsPlace = eventsPlace;}
    public void addEventsPlace(EventsPlace newEventsPlace)
    {
    	if (this.getEventsPlace() == null)
    		this.setEventsPlace(new ArrayList<EventsPlace>());
    	this.getEventsPlace().add(newEventsPlace);
    }
    
	@Transient
    private static Instruction[] instructions = new Instruction[] {
    	new Instruction("DATE", "eventsDate"),
    	new Instruction("PLAC", "eventsPlace")
    };
    
    public EventsRecorded() {super();}
    
    public EventsRecorded(Node node)
    {
    	super(node);
		Instruction.buildChildren(this, instructions, this.nodeChildren);
    }
}
