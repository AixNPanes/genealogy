package ws.daley.genealogy.db.people.structure;

import java.util.ArrayList;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.Instruction;

@Entity
public class SourceRepositoryCitation extends Text
{
	@Transient
    private static final long serialVersionUID = 1L;
    
	@OneToMany
    private ArrayList<SourceCallNumber> sourceCallNumber = new ArrayList<SourceCallNumber>();
    public ArrayList<SourceCallNumber> getSourceCallNumber() {return this.sourceCallNumber;}
    public void setSourceCallNumber(ArrayList<SourceCallNumber> sourceCallNumber) {this.sourceCallNumber = sourceCallNumber;}
    public void addSourceCallNumber(SourceCallNumber newSourceCallNumber)
    {
    	if (this.getSourceCallNumber() == null)
    		this.setSourceCallNumber(new ArrayList<SourceCallNumber>());
    	this.getSourceCallNumber().add(newSourceCallNumber);
    }

	@OneToMany
    private ArrayList<NoteStructure> noteStructure = new ArrayList<NoteStructure>();
    public ArrayList<NoteStructure> getNoteStructure() {return this.noteStructure;}
    public void setNoteStructure(ArrayList<NoteStructure> noteStructure) {this.noteStructure = noteStructure;}
    public void addNoteStructure(NoteStructure newNnoteStructure)
    {
    	if (this.getNoteStructure() == null)
    		this.setNoteStructure(new ArrayList<NoteStructure>());
    	this.getNoteStructure().add(newNnoteStructure);
    }
    
	@Basic
    private String citation;
    public String getCitation() {return this.citation;}
    public void setCitation(String citation) {this.citation = citation;}

    @Transient
    private static Instruction[] instructions = new Instruction[] {
    	new Instruction("CALN", "sourceCallNumber"),
    	new Instruction("NOTE", "noteStructure")
    };
    
	public SourceRepositoryCitation() {super();}

    public SourceRepositoryCitation(Node node)
    {
    	super(node);
		Instruction.buildChildren(this, instructions, this.nodeChildren);
		this.citation = this.getText();
    }
}
