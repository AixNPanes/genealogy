package ws.daley.genealogy.db.people.structure;

import java.util.ArrayList;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.Instruction;

@Entity
public class Event extends Text
{
	@Transient
    private static final long serialVersionUID = 1L;

	@Basic
	private String date;
    public String getDate() {return this.date;}
    public void setDate(String date) {this.date = date;}
	
	@Basic
	private String place;
    public String getPlace() {return this.place;}
    public void setPlace(String place) {this.place = place;}

    @OneToMany
	private ArrayList<Source> source;
    public ArrayList<Source> getSource() {return this.source;}
    public void setSource(ArrayList<Source> source) {this.source = source;}
    public void addSource(Source newSource)
    {
    	if (this.getSource() == null)
    		this.setSource(new ArrayList<Source>());
    	this.getSource().add(newSource);
    }
    
	@Transient
    private static Instruction[] instructions = new Instruction[] {
    	new Instruction("DATE", "date"),
    	new Instruction("PLAC", "place"),
    	new Instruction("SOUR", "source")
    };
	
	public Event() {}
	
	public Event(Node node)
	{
		super(node);
		Instruction.buildChildren(this, instructions, this.nodeChildren);
	}
	
	public void processInstruction()
	{
		throw new RuntimeException("");
	}
}
