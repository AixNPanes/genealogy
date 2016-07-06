package ws.daley.genealogy.db.people.structure;

import java.util.ArrayList;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.Instruction;
import ws.daley.genealogy.db.people.element.Place;

@Entity
public class Marriage extends Text
{
	@Transient
    private static final long serialVersionUID = 1L;
    
    @Basic
	private String date;
    public String getDate() {return this.date;}
    public void setDate(String date) {this.date = date;}
	
    @OneToOne
	private Place place;
    public Place getPlace() {return this.place;}
    public void setPlace(Place place) {this.place = place;}
	
    @Basic
	private String placeId;
    public String getPlaceId() {return this.placeId;}
    public void setPlaceId(String placeId) {this.placeId = placeId;}
	
    @OneToMany
	private ArrayList<Source> sources;
    public ArrayList<Source> getSources() {return this.sources;}
    public void setSources(ArrayList<Source> newSources) {this.sources = newSources;}
    public void addSources(Source newSource)
    {
    	if (this.getSources() == null)
    		this.setSources(new ArrayList<Source>());
    	this.getSources().add(newSource);
    }

    @Basic
	private String sourceId;
    public String getSourceId() {return this.sourceId;}
    public void setSourceId(String sourceId) {this.sourceId = sourceId;}
    
	@Transient
    private static Instruction[] instructions = new Instruction[] {
    	new Instruction("DATE", "date"),
    	new Instruction("PLAC", "place"),
    	new Instruction("SOUR", "sources")
    };
	
	public Marriage() {}
	
	public Marriage(Node node)
	{
		super(node);
		Instruction.buildChildren(this, instructions, this.nodeChildren);
	}
	
    public void processInstruction()
    {
    	throw new RuntimeException();
    }
}
