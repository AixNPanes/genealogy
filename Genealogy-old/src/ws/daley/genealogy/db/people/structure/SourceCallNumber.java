package ws.daley.genealogy.db.people.structure;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.Instruction;
import ws.daley.genealogy.db.people.element.SourceMediaType;

@Entity
public class SourceCallNumber extends Text
{
	@Transient
    private static final long serialVersionUID = 1L;

	@OneToMany
    private ArrayList<SourceMediaType> sourceMediaType = new ArrayList<SourceMediaType>();
    public ArrayList<SourceMediaType> getSourceMediaType() {return this.sourceMediaType;}
    public void setSourceMediaType(ArrayList<SourceMediaType> sourceMediaType) {this.sourceMediaType = sourceMediaType;}
    public void addSourceMediaType(SourceMediaType newSourceMediaType)
    {
    	if (this.getSourceMediaType() == null)
    		this.setSourceMediaType(new ArrayList<SourceMediaType>());
    	this.getSourceMediaType().add(newSourceMediaType);
    }
    
	@Transient
    private static Instruction[] instructions = new Instruction[] {
    	new Instruction("MEDI", "sourceMediaType")
    };

	public SourceCallNumber() {super();}
    
    public SourceCallNumber(Node node)
    {
    	super(node);
		Instruction.buildChildren(this, instructions, this.nodeChildren);
    }
}
