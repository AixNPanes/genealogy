package ws.daley.genealogy.db.people.structure;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.Instruction;

@Entity
public class NoteStructure extends Text
{
	@Transient
    private static final long serialVersionUID = 1L;
    
	@OneToMany
	private ArrayList<SourceCitation> sourceCitation = new ArrayList<SourceCitation>();
    public ArrayList<SourceCitation> getSourceCitation() {return this.sourceCitation;}
    public void setSourceCitation(ArrayList<SourceCitation> sourceCitation) {this.sourceCitation = sourceCitation;}
    public void addSourceCitation(SourceCitation newSourceCitation)
    {
    	if (this.getSourceCitation() == null)
    		this.setSourceCitation(new ArrayList<SourceCitation>());
    	this.getSourceCitation().add(newSourceCitation);
    }
    
	@Transient
    private static Instruction[] instructions = new Instruction[] {
    	new Instruction("SOUR", "sourceCitation")
    };

	public NoteStructure() {super();}
    
    public NoteStructure(Node node)
    {
    	super(node);
		Instruction.buildChildren(this, instructions, this.nodeChildren);
    }
    
    public void processInstruction()
    {
    	throw new RuntimeException();
    }
}
