package ws.daley.genealogy.db.people.structure;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.Instruction;
import ws.daley.genealogy.db.people.element.Page;
import ws.daley.genealogy.db.people.element.Quay;

@Entity
public class SourceCitation extends Text
{
	@Transient
    private static final long serialVersionUID = 1L;
    
    @OneToMany
    private ArrayList<Page> page = new ArrayList<Page>();
    public ArrayList<Page> getPage() {return this.page;}
    public void setPage(ArrayList<Page> page) {this.page = page;}
    public void addPage(Page newPage)
    {
    	if (this.getPage() == null)
    		this.setPage(new ArrayList<Page>());
    	this.getPage().add(newPage);
    }

    @OneToMany
    private ArrayList<EventCitation> eventCitation = new ArrayList<EventCitation>();
    public ArrayList<EventCitation> getEventCitation() {return this.eventCitation;}
    public void setEventCitation(ArrayList<EventCitation> eventCitation) {this.eventCitation = eventCitation;}
    public void addEventCitation(EventCitation newEeventCitation)
    {
    	if (this.getEventCitation() == null)
    		this.setEventCitation(new ArrayList<EventCitation>());
    	this.getEventCitation().add(newEeventCitation);
    }

    @OneToMany
    private ArrayList<SourceCitationData> sourceCitationData = new ArrayList<SourceCitationData>();
    public ArrayList<SourceCitationData> getSourceCitationData() {return this.sourceCitationData;}
    public void setSourceCitationData(ArrayList<SourceCitationData> newSourceCitationData) {this.sourceCitationData = newSourceCitationData;}
    public void addSourceCitationData(SourceCitationData newSourceCitationData)
    {
    	if (this.getSourceCitationData() == null)
    		this.setSourceCitationData(new ArrayList<SourceCitationData>());
    	this.getSourceCitationData().add(newSourceCitationData);
    }
    
    @OneToMany
    private ArrayList<Quay> quay = new ArrayList<Quay>();
    public ArrayList<Quay> getQuay() {return this.quay;}
    public void setQuay(ArrayList<Quay> quay) {this.quay = quay;}
    public void addQuay(Quay newQuay)
    {
    	if (this.getQuay() == null)
    		this.setQuay(new ArrayList<Quay>());
    	this.getQuay().add(newQuay);
    }
    
    @OneToMany
    private ArrayList<MultimediaLink> multimediaLink = new ArrayList<MultimediaLink>();
    public ArrayList<MultimediaLink> getMultimediaLink() {return this.multimediaLink;}
    public void setMultimediaLink(ArrayList<MultimediaLink> multimediaLink) {this.multimediaLink = multimediaLink;}
    public void addMultimediaLink(MultimediaLink newMultimediaLink)
    {
    	if (this.getMultimediaLink() == null)
    		this.setMultimediaLink(new ArrayList<MultimediaLink>());
    	this.getMultimediaLink().add(newMultimediaLink);
    }

    @OneToMany
    private ArrayList<NoteStructure> noteStructure = new ArrayList<NoteStructure>();
    public ArrayList<NoteStructure> getNoteStructure() {return this.noteStructure;}
    public void setNoteStructure(ArrayList<NoteStructure> noteStructure) {this.noteStructure = noteStructure;}
    public void addNoteStructure(NoteStructure newNoteStructure)
    {
    	if (this.getNoteStructure() == null)
    		this.setNoteStructure(new ArrayList<NoteStructure>());
    	this.getNoteStructure().add(newNoteStructure);
    }
    
	@Transient
    private static Instruction[] instructions = new Instruction[] {
    	new Instruction("PAGE", "page"),
    	new Instruction("EVEN", "eventCitation"),
    	new Instruction("DATA", "sourceCitationData"),
    	new Instruction("QUAY", "quay"),
    	new Instruction("OBJE", "multimediaLink"),
    	new Instruction("NOTE", "noteStructure")
    };

	public SourceCitation() {super();}
    
    public SourceCitation(Node node)
    {
    	super(node);
		Instruction.buildChildren(this, instructions, this.nodeChildren);
    }
}
