package ws.daley.genealogy.db.people.structure;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.Instruction;
import ws.daley.genealogy.db.people.element.ChangeDateTime;

@Entity
public class ChangeDateDate extends Text
{
	@Transient
    private static final long serialVersionUID = 1L;

	@OneToMany
    private ArrayList<ChangeDateTime> changeDateTime = new ArrayList<ChangeDateTime>();
    public ArrayList<ChangeDateTime> getChangeDateTime() {return this.changeDateTime;}
    public void setChangeDateTime(ArrayList<ChangeDateTime> changeDateTime) {this.changeDateTime = changeDateTime;}
    public void addChangeDateTime(ChangeDateTime newChangeDateTime)
    {
    	if (this.getChangeDateTime() == null)
    		this.setChangeDateTime(new ArrayList<ChangeDateTime>());
    	this.getChangeDateTime().add(newChangeDateTime);
    }
    
	@Transient
    private static Instruction[] instructions = new Instruction[] {
    	new Instruction("TIME", "changeDateTime")
    };

	public ChangeDateDate() {super();}
	
	public ChangeDateDate(Node node)
	{
		super(node);
		Instruction.buildChildren(this, instructions, this.nodeChildren);
	}
}
