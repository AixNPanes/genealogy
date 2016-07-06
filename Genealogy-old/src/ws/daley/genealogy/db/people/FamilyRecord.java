package ws.daley.genealogy.db.people;

import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.apache.openjpa.persistence.OpenJPAEntityManager;
import org.w3c.dom.Node;

import ws.daley.genealogy.db.IdString;
import ws.daley.genealogy.db.Instruction;
import ws.daley.genealogy.db.people.structure.Marriage;
import ws.daley.genealogy.db.people.structure.NoteStructure;

@Entity
public class FamilyRecord extends IdString
{
	@Transient
    private static final long serialVersionUID = 1L;
	
	@Transient
	private static HashMap<String, FamilyRecord> map = null; 
    public static HashMap<String, FamilyRecord> getFamilyRecordMap() {return map;}
    public static void setFamilyRecordMap(HashMap<String, FamilyRecord> familyRecordMap) {FamilyRecord.map = familyRecordMap;}
	
    @OneToOne
	private IndividualRecord husband;
    public IndividualRecord getHusband() {return this.husband;}
    public void setHusband(IndividualRecord husband) {this.husband = husband;}
    
	@Transient
    private String husbandId;
    public String getHusbandId() {return this.husbandId;}
    public void setHusbandId(String newHusbandId) {this.husbandId = newHusbandId;}
	
    @OneToOne
	private IndividualRecord wife;
    public IndividualRecord getWife() {return this.wife;}
    public void setWife(IndividualRecord wife) {this.wife = wife;}
    
	@Transient
    private String wifeId;
    public String getWifeId() {return this.wifeId;}
    public void setWifeId(String newWifeId) {this.wifeId = newWifeId;}

    @OneToMany
    private ArrayList<IndividualRecord> children = null;
    public ArrayList<IndividualRecord> getChildren() {return this.children;}
    public void setChildren(ArrayList<IndividualRecord> children) {this.children = children;}
    public void addChildren(IndividualRecord child)
    {
    	if (this.getChildren() == null)
    		this.setChildren(new ArrayList<IndividualRecord>());
    	this.getChildren().add(child);
    }

	@Transient
    private ArrayList<String> childrenId = null;
    public ArrayList<String> getChildrenId() {return this.childrenId;}
    public void setChildrenId(ArrayList<String> newChildrenId) {this.childrenId = newChildrenId;}
    public void addChildrenId(String newChildId)
    {
    	if (this.getChildrenId() == null)
    		this.setChildrenId(new ArrayList<String>());
    	this.getChildrenId().add(newChildId);
    }
	
    @OneToMany
	private ArrayList<Marriage> marriage = null;
    public ArrayList<Marriage> getMarriage() {return this.marriage;}
    public void setMarriage(ArrayList<Marriage> marriage) {this.marriage = marriage;}
    public void addMarriage(Marriage marrage)
    {
    	if (this.getMarriage() == null)
    		this.setMarriage(new ArrayList<Marriage>());
    	this.getMarriage().add(marrage);
    }
	
    @OneToMany
	private ArrayList<NoteStructure> note = new ArrayList<NoteStructure>();
    public ArrayList<NoteStructure> getNote() {return this.note;}
    public void setNote(ArrayList<NoteStructure> note) {this.note = note;}
    public void addNote(NoteStructure not)
    {
    	if (this.getNote() == null)
    		this.setNote(new ArrayList<NoteStructure>());
    	this.getNote().add(not);
    }
    
	@Transient
    private static Instruction[] instructions = new Instruction[] {
    	new Instruction("HUSB", "husbandId"),
    	new Instruction("WIFE", "wifeId"),
    	new Instruction("CHIL", "childrenId"),
    	new Instruction("MARR", "marriage"),
    	new Instruction("NOTE", "note")
    };

	public FamilyRecord() {}
	
	public FamilyRecord(Node node) {super(node);}
	
	public void processInstructions()
	{
		Instruction.buildChildren(this, instructions, this.nodeChildren);
	}
	
	public static FamilyRecord getFamilyRecord(Node node)
	{
		if (map == null)
			map = new HashMap<String, FamilyRecord>();
		String nodeName = node.getAttributes().getNamedItem("id").getNodeValue();
		FamilyRecord record = map.get(nodeName);
		if (record != null)
			return record;
		record = new FamilyRecord(node);
		map.put(nodeName, record);
		return record;
	}
	
	public void resolveIds(OpenJPAEntityManager em)
	{
		em.getTransaction().commit();
		if (this.husbandId != null && this.husbandId.length() > 0)
		{
			em.getTransaction().begin();
			System.out.println("resolving husbandId");
			this.setHusband(IndividualRecord.getIndividualRecordMap().get(this.husbandId));
			if (this.husband != null)
				this.setHusbandId(null);
			em.getTransaction().commit();
		}
		if (this.wifeId != null && this.wifeId.length() > 0)
		{
			em.getTransaction().begin();
			System.out.println("resolving wifeId");
			this.setWife(IndividualRecord.getIndividualRecordMap().get(this.wifeId));
			if (this.wife != null)
				this.setWifeId(null);
			em.getTransaction().commit();
		}
		if (this.childrenId != null)
		{
			em.getTransaction().begin();
			System.out.println("resolving childrenId");
			for(String childId:this.childrenId)
			{
				if (childId != null && childId.length() > 0)
				{
					IndividualRecord child = IndividualRecord.getIndividualRecordMap().get(childId);
					if (child != null)
						this.addChildren(child);
				}
			}
			em.getTransaction().commit();
		}
		if (this.marriage != null)
		{
			em.getTransaction().begin();
			System.out.println("resolving marriage");
			for(Marriage marr:this.marriage)
				marr.processInstruction();
			em.getTransaction().commit();
		}
		if (this.note != null)
		{
			em.getTransaction().begin();
			System.out.println("resolving note");
			for(NoteStructure noteStructure:this.note)
				noteStructure.processInstruction();
			em.getTransaction().commit();
		}
		em.getTransaction().begin();
	}
}