 package ws.daley.genealogy.db.people.structure;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.IdNumber;
import ws.daley.genealogy.db.Instruction;
import ws.daley.genealogy.db.people.element.Repository;
import ws.daley.genealogy.db.people.element.Title;

@Entity
public class Source extends IdNumber
{
	@Transient
    private static final long serialVersionUID = 1L;

	@OneToOne
	private Title title;
    public Title getTitle() {return this.title;}
    public void setTitle(Title newTitle) {this.title = newTitle;}
	
	@OneToOne
	private Repository repository;
    public Repository getRepository() {return this.repository;}
    public void setRepository(Repository newRepository) {this.repository = newRepository;}

    @Basic
	private String note;
    public String getNote() {return this.note;}
    public void setNote(String newNote) {this.note = newNote;}
	
	@OneToOne
	private SourceCitationData data;
    public SourceCitationData getData() {return this.data;}
    public void setData(SourceCitationData newData) {this.data = newData;}
    
	@Transient
    private static Instruction[] instructions = new Instruction[] {
    	new Instruction("TITL", "title"),
    	new Instruction("REPO", "repository"),
    	new Instruction("NOTE", "note"),
    	new Instruction("DATA", "data")
    };
	
	public Source() {}
	
	public Source(Node node)
	{
		super(node);
		Instruction.buildChildren(this, instructions, this.nodeChildren);
	}
}