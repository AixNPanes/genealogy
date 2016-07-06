package ws.daley.genealogy.db.people.structure;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.Instruction;
import ws.daley.genealogy.db.people.element.RefnType;

@Entity
public class Refn extends Text
{
	@Transient
    private static final long serialVersionUID = 1L;

	@OneToMany
    private ArrayList<RefnType> refnType = new ArrayList<RefnType>();
    public ArrayList<RefnType> getRefnType() {return this.refnType;}
    public void setRefnType(ArrayList<RefnType> refnType) {this.refnType = refnType;}
    public void addRefnType(RefnType newRefnType)
    {
    	if (this.getRefnType() == null)
    		this.setRefnType(new ArrayList<RefnType>());
    	this.getRefnType().add(newRefnType);
    }
    
	@Transient
    private static Instruction[] instructions = new Instruction[] {
    	new Instruction("TYPE", "refnType")
    };

	public Refn() {super();}

    public Refn(Node node)
    {
    	super(node);
		Instruction.buildChildren(this, instructions, this.nodeChildren);
    }
}
