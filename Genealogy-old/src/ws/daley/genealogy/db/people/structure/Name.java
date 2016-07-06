package ws.daley.genealogy.db.people.structure;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.Instruction;

@Entity
public class Name extends Text
{
	@Transient
    private static final long serialVersionUID = 1L;

	@OneToMany
    private ArrayList<SourceCitation> source;
    public ArrayList<SourceCitation> getSource() {return this.source;}
    public void setSource(ArrayList<SourceCitation> newSource) {this.source = newSource;}
    public void addSource(SourceCitation newSource)
    {
    	if (this.getSource() == null)
    		this. setSource(new ArrayList<SourceCitation>());
    	this.getSource().add(newSource);
    }
    
	@Transient
    private static Instruction[] instructions = new Instruction[] {
    	new Instruction("SOUR", "source")
    };

	public Name() {super();}
	
	public Name(Node node)
	{
		super(node);
		Instruction.buildChildren(this, instructions, this.nodeChildren);
	}
	
	public void processInstruction()
	{
		throw new RuntimeException("");
	}
}
