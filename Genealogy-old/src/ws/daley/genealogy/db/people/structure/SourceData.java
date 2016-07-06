package ws.daley.genealogy.db.people.structure;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.Instruction;
import ws.daley.genealogy.db.people.element.ResponsibleAgency;

@Entity
public class SourceData extends Text
{
	@Transient
    private static final long serialVersionUID = 1L;
    
    @OneToMany
    private ArrayList<EventsRecorded> eventsRecorded = new ArrayList<EventsRecorded>();
    public ArrayList<EventsRecorded> getEventsRecorded() {return this.eventsRecorded;}
    public void setEventsRecorded(ArrayList<EventsRecorded> newEventsRecorded) {this.eventsRecorded = newEventsRecorded;}
    public void addEventsRecorded(EventsRecorded newEventsRecorded)
    {
    	if (this.getEventsRecorded() == null)
    		this.setEventsRecorded(new ArrayList<EventsRecorded>());
    	this.getEventsRecorded().add(newEventsRecorded);
    }

    @OneToMany
    private ArrayList<ResponsibleAgency> responsibleAgency = new ArrayList<ResponsibleAgency>();
    public ArrayList<ResponsibleAgency> getResponsibleAgency() {return this.responsibleAgency;}
    public void setResponsibleAgency(ArrayList<ResponsibleAgency> newResponsibleAgency) {this.responsibleAgency = newResponsibleAgency;}
    public void addResponsibleAgency(ResponsibleAgency newResponsibleAgency)
    {
    	if (this.getResponsibleAgency() == null)
    		this.setResponsibleAgency(new ArrayList<ResponsibleAgency>());
    	this.getResponsibleAgency().add(newResponsibleAgency);
    }

    @OneToMany
    private ArrayList<NoteStructure> noteStructure = new ArrayList<NoteStructure>();
    public ArrayList<NoteStructure> getNoteStructure() {return this.noteStructure;}
    public void setNoteStructure(ArrayList<NoteStructure> newNoteStructure) {this.noteStructure = newNoteStructure;}
    public void addNoteStructure(NoteStructure newNoteStructure)
    {
    	if (this.getNoteStructure() == null)
    		this.setNoteStructure(new ArrayList<NoteStructure>());
    	this.getNoteStructure().add(newNoteStructure);
    }
    
	@Transient
    private static Instruction[] instructions = new Instruction[] {
    	new Instruction("EVEN", "eventsRecorded"),
    	new Instruction("AGNC", "responsibleAgency"),
    	new Instruction("NOTE", "noteStructure")
    };

	public SourceData() {super();}

    public SourceData(Node node)
    {
    	super(node);
		Instruction.buildChildren(this, instructions, this.nodeChildren);
    }
}
