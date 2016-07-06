package ws.daley.genealogy.db.people.structure;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.Instruction;
import ws.daley.genealogy.db.people.element.SourceCitationDate;
import ws.daley.genealogy.db.people.element.SourceCitationText;

@Entity
public class SourceCitationData extends Text
{
	@Transient
    private static final long serialVersionUID = 1L;
    
    @OneToMany
    private ArrayList<SourceCitationDate> sourceCitationDate = new ArrayList<SourceCitationDate>();
    public ArrayList<SourceCitationDate> getSourceCitationDate() {return this.sourceCitationDate;}
    public void setSourceCitationDate(ArrayList<SourceCitationDate> newSourceCitationDate) {this.sourceCitationDate = newSourceCitationDate;}
    public void addSourceCitationDate(SourceCitationDate newSourceCitationDate)
    {
    	if (this.getSourceCitationDate() == null)
    		this.setSourceCitationDate(new ArrayList<SourceCitationDate>());
    	this.getSourceCitationDate().add(newSourceCitationDate);
    }

    @OneToMany
    private ArrayList<SourceCitationText> sourceCitationText = new ArrayList<SourceCitationText>();
    public ArrayList<SourceCitationText> getSourceCitationText() {return this.sourceCitationText;}
    public void setSourceCitationText(ArrayList<SourceCitationText> newSourceCitationText) {this.sourceCitationText = newSourceCitationText;}
    public void addSourceCitationText(SourceCitationText newSourceCitationText)
    {
    	if (this.getSourceCitationText() == null)
    		this.setSourceCitationText(new ArrayList<SourceCitationText>());
    	this.getSourceCitationText().add(newSourceCitationText);
    }
    
	@Transient
    private static Instruction[] instructions = new Instruction[] {
    	new Instruction("DATE", "sourceCitationDate"),
    	new Instruction("TEXT", "sourceCitationText")
    };

	public SourceCitationData() {super();}
    
    public SourceCitationData(Node node)
    {
    	super(node);
		Instruction.buildChildren(this, instructions, this.nodeChildren);
    }
}
