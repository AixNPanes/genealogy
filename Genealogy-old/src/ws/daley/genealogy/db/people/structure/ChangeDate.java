package ws.daley.genealogy.db.people.structure;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.Instruction;

@Entity
public class ChangeDate extends Text
{
	@Transient
    private static final long serialVersionUID = 1L;
    
	@OneToMany
	private ArrayList<ChangeDateDate> changeDateDate = new ArrayList<ChangeDateDate>();
    public ArrayList<ChangeDateDate> getChangeDateDate() {return this.changeDateDate;}
    public void setChangeDateDate(ArrayList<ChangeDateDate> changeDateDate) {this.changeDateDate = changeDateDate;}
    public void addChangeDateDate(ChangeDateDate newChangeDateDate)
    {
    	if (this.getChangeDateDate() == null)
    		this.setChangeDateDate(new ArrayList<ChangeDateDate>());
    	this.getChangeDateDate().add(newChangeDateDate);
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
    	new Instruction("DATE", "changeDateDate"),
    	new Instruction("NOTE", "noteStructure")
    };

	public ChangeDate() {super();}
	
	public ChangeDate(Node node)
	{
		super(node);
		Instruction.buildChildren(this, instructions, this.nodeChildren);
	}
}
