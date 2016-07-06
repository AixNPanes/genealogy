package ws.daley.genealogy.db.people;

import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.IdString;
import ws.daley.genealogy.db.Instruction;
import ws.daley.genealogy.db.people.element.IdElement;
import ws.daley.genealogy.db.people.element.Sex;
import ws.daley.genealogy.db.people.structure.Event;
import ws.daley.genealogy.db.people.structure.Name;

@Entity
public class IndividualRecord extends IdString
{
	@Transient
    private static final long serialVersionUID = 1L;
	
	@Transient
	private static HashMap<String, IndividualRecord> map = null; 
    public static HashMap<String, IndividualRecord> getIndividualRecordMap() {return map;}
    public static void setIndividualRecordMap(HashMap<String, IndividualRecord> individualRecordMap) {IndividualRecord.map = individualRecordMap;}
    
	@Basic
	private String relationshipToFather = null;
    public String getRelationshipToFather() {return this.relationshipToFather;} 
    public void setRelationshipToFather(String relationshipToFather) {this.relationshipToFather = relationshipToFather;}
	
	@Basic
	private String relationshipToMother = null;
    public String getRelationshipToMother() {return this.relationshipToMother;}
    public void setRelationshipToMother(String relationshipToMother) {this.relationshipToMother = relationshipToMother;}
    
	@OneToMany
    private ArrayList<Sex> sex = null;
    public ArrayList<Sex> getSex() {return this.sex;}
    public void setSex(ArrayList<Sex> sex) {this.sex = sex;}
    public void addSex(Sex newSex)
    {
    	if (this.getSex() == null)
    		this.setSex(new ArrayList<Sex>());
    	this.getSex().add(newSex);
    }
    
	@OneToMany
    private ArrayList<Name> name = null;
    public ArrayList<Name> getName() {return this.name;}
    public void setName(ArrayList<Name> name) {this.name = name;}
    public void addName(Name newName)
    {
    	if (this.getName() == null)
    		this.setName(new ArrayList<Name>());
    	this.getName().add(newName);
    }
    
	@OneToMany
    private ArrayList<Event> occupation = null;
    public ArrayList<Event> getOccupation() {return this.occupation;}
    public void setOccupation(ArrayList<Event> occupation) {this.occupation = occupation;}
    public void addOccupation(Event husband)
    {
    	if (this.getOccupation() == null)
    		this.setOccupation(new ArrayList<Event>());
    	this.getOccupation().add(husband);
    }
    
	@OneToMany
    private ArrayList<Event> militaryService = null;
    public ArrayList<Event> getMilitaryService() {return this.militaryService;}
    public void setMilitaryService(ArrayList<Event> militaryService) {this.militaryService = militaryService;}
    public void addMilitaryService(Event husband)
   {
    	if (this.getMilitaryService() == null)
    		this.setMilitaryService(new ArrayList<Event>());
    	this.getMilitaryService().add(husband);
    }
    
    private ArrayList<Event> ordinance = null;
    public ArrayList<Event> getOrdinance() {return this.ordinance;}
    public void setOrdinance(ArrayList<Event> ordinance) {this.ordinance = ordinance;}
    public void addOrdinance(Event event)
    {
    	if (this.getOrdinance() == null)
    		this.setOrdinance(new ArrayList<Event>());
    	this.getOrdinance().add(event);
    }
    
	@OneToMany
    private ArrayList<Event> ssn = null;
    public ArrayList<Event> getSsn() {return this.ssn;}
    public void setSsn(ArrayList<Event> ssn) {this.ssn = ssn;}
    public void addSsn(Event newSsn)
    {
    	if (this.getSsn() == null)
    		this.setSsn(new ArrayList<Event>());
    	this.getSsn().add(newSsn);
    }
    
	@OneToMany
    private ArrayList<Event> userReferenceNumber = null;
    public ArrayList<Event> getUserReferenceNumber() {return this.userReferenceNumber;}
    public void setUserReferenceNumber(ArrayList<Event> userReferenceNumber) {this.userReferenceNumber = userReferenceNumber;}
    public void addUserReferenceNumber(Event event)
    {
    	if (this.getUserReferenceNumber() == null)
    		this.setUserReferenceNumber(new ArrayList<Event>());
    	this.getUserReferenceNumber().add(event);
    }
    
	@OneToMany
    private ArrayList<Event> residence = null;
    public ArrayList<Event> getResidence() {return this.residence;}
    public void setResidence(ArrayList<Event> residence) {this.residence = residence;}
    public void addResidence(Event event)
    {
    	if (this.getResidence() == null)
    		this.setResidence(new ArrayList<Event>());
    	this.getResidence().add(event);
    }
    
	@OneToMany
    private ArrayList<Event> title = null;
    public ArrayList<Event> getTitle() {return this.title;}
    public void setTitle(ArrayList<Event> title) {this.title = title;}
    public void addTitle(Event event)
    {
    	if (this.getTitle() == null)
    		this.setTitle(new ArrayList<Event>());
    	this.getTitle().add(event);
    }
    
	@OneToMany
    private ArrayList<Event> note = null;
    public ArrayList<Event> getNote() {return this.note;}
    public void setNote(ArrayList<Event> note) {this.note = note;}
    public void addNote(Event event)
    {
    	if (this.getNote() == null)
    		this.setNote(new ArrayList<Event>());
    	this.getNote().add(event);
    }

	@OneToMany
    private ArrayList<Event> birth = null;
    public ArrayList<Event> getBirth() {return this.birth;}
    public void setBirth(ArrayList<Event> birth) {this.birth = birth;}
    public void addBirth(Event event)
    {
    	if (this.getBirth() == null)
    		this.setBirth(new ArrayList<Event>());
    	this.getBirth().add(event);
    }

	@OneToMany
    private ArrayList<Event> death = null;
    public ArrayList<Event> getDeath() {return this.death;}
    public void setDeath(ArrayList<Event> death) {this.death = death;}
    public void addDeath(Event event)
    {
    	if (this.getDeath() == null)
    		this.setDeath(new ArrayList<Event>());
    	this.getDeath().add(event);
    }

	@OneToMany
    private ArrayList<Event> even = null;
    public ArrayList<Event> getEven() {return this.even;}
    public void setEven(ArrayList<Event> even) {this.even = even;}
    public void addEven(Event event)
    {
    	if (this.getEven() == null)
    		this.setEven(new ArrayList<Event>());
    	this.getEven().add(event);
    }

	@OneToMany
    private ArrayList<Event> burial = null;
    public ArrayList<Event> getBurial() {return this.burial;}
    public void setBurial(ArrayList<Event> burial) {this.burial = burial;}
    public void addBurial(Event event)
    {
    	if (this.getBurial() == null)
    		this.setBurial(new ArrayList<Event>());
    	this.getBurial().add(event);
    }

	@OneToMany
    private ArrayList<Event> blessing = null;
    public ArrayList<Event> getBlessing() {return this.blessing;}
    public void setBlessing(ArrayList<Event> blessing) {this.blessing = blessing;}
    public void addBlessing(Event event)
    {
    	if (this.getBlessing() == null)
    		this.setBlessing(new ArrayList<Event>());
    	this.getBlessing().add(event);
    }

	@OneToMany
    private ArrayList<Event> adoption = null;
    public ArrayList<Event> getAdoption() {return this.adoption;}
    public void setAdoption(ArrayList<Event> adoption) {this.adoption = adoption;}
    public void addAdoption(Event event)
    {
    	if (this.getAdoption() == null)
    		this.setAdoption(new ArrayList<Event>());
    	this.getAdoption().add(event);
    }

	@OneToMany
    private ArrayList<Event> christening = null;
    public ArrayList<Event> getChristening() {return this.christening;}
    public void setChristening(ArrayList<Event> christening) {this.christening = christening;}
    public void addChristening(Event event)
    {
    	if (this.getChristening() == null)
    		this.setChristening(new ArrayList<Event>());
    	this.getChristening().add(event);
    }
    
	@OneToMany
    private ArrayList<FamilyRecord> husbandInFamily = null;
    public ArrayList<FamilyRecord> getHusbandInFamily() {return this.husbandInFamily;}
    public void setHusbandInFamily(ArrayList<FamilyRecord> husbandInFamily) {this.husbandInFamily = husbandInFamily;}
    public void addHusbandInFamily(FamilyRecord husband)
    {
    	if (this.getHusbandInFamily() == null)
    		this.setHusbandInFamily(new ArrayList<FamilyRecord>());
    	this.getHusbandInFamily().add(husband);
    }
    
	@OneToMany
    private ArrayList<FamilyRecord> wifeInFamily = null;
    public ArrayList<FamilyRecord> getWifeInFamily() {return this.wifeInFamily;}
    public void setWifeInFamily(ArrayList<FamilyRecord> wifeInFamily) {this.wifeInFamily = wifeInFamily;}
    public void addWifeInFamily(FamilyRecord wife)
    {
    	if (this.getWifeInFamily() == null)
    		this.setWifeInFamily(new ArrayList<FamilyRecord>());
    	this.getWifeInFamily().add(wife);
    }
    
	@OneToMany
    private ArrayList<IdElement> husbandIdInFamily = null;
    public ArrayList<IdElement> getHusbandIdInFamily() {return this.husbandIdInFamily;}
    public void setHusbandIdInFamily(ArrayList<IdElement> husbandIdInFamily) {this.husbandIdInFamily = husbandIdInFamily;}
    public void addHusbandIdInFamily(IdElement husbandId)
    {
    	if (this.getHusbandIdInFamily() == null)
    		this.setHusbandIdInFamily(new ArrayList<IdElement>());
    	this.getHusbandIdInFamily().add(husbandId);
    }
    
	@OneToMany
    private ArrayList<IdElement> wifeIdInFamily = null;
    public ArrayList<IdElement> getWifeIdInFamily() {return this.wifeIdInFamily;}
    public void setWifeIdInFamily(ArrayList<IdElement> wifeIdInFamily) {this.wifeIdInFamily = wifeIdInFamily;}
    public void addWifeIdInFamily(IdElement wifeId)
    {
    	if (this.getWifeIdInFamily() == null)
    		this.setWifeIdInFamily(new ArrayList<IdElement>());
    	this.getWifeIdInFamily().add(wifeId);
    }
    
    @Transient
    private static Instruction[] instructions = new Instruction[] {
    	new Instruction("_FREL", "relationshipToFather"),
    	new Instruction("_MREL", "relationshipToMother"),
    	new Instruction("SEX", "sex"),
    	new Instruction("NAME", "name"),
    	new Instruction("OCCU", "occupation"),
    	new Instruction("_MILT", "militaryService"),
    	new Instruction("ORDI", "ordinance"),
    	new Instruction("SSN", "ssn"),
    	new Instruction("REFN", "userReferenceNumber"),
    	new Instruction("RESN", "residence"),
    	new Instruction("TITL", "title"),
    	new Instruction("NOTE", "note"),
    	new Instruction("BIRT", "birth"),
    	new Instruction("DEAT", "death"),
    	new Instruction("EVEN", "even"),
    	new Instruction("BURI", "burial"),
    	new Instruction("BLES", "blessing"),
    	new Instruction("ADOP", "adoption"),
    	new Instruction("CHIR", "christening"),
    	new Instruction("HUSB", "husbandIdInFamily"),
    	new Instruction("WIFE", "wifeIdInFamily")
    };
    
	public IndividualRecord() {}
	
	public IndividualRecord(Node node) {super(node);}
	
	public void processInstructions()
	{
		Instruction.buildChildren(this, instructions, this.nodeChildren);
	}
	
	public static IndividualRecord getIndividualRecord(Node node)
	{
		if (map == null)
			map = new HashMap<String, IndividualRecord>();
		String nodeName = node.getAttributes().getNamedItem("id").getNodeValue();
		IndividualRecord record = map.get(nodeName);
		if (record != null)
			return record;
		record = new IndividualRecord(node);
		map.put(nodeName, record);
		return record;
	}
	
	public void resolveIds()
	{
		if (this.husbandIdInFamily != null)
			for(IdElement husbandId:this.getHusbandIdInFamily())
			{
				FamilyRecord husband = FamilyRecord.getFamilyRecordMap().get(husbandId.getId());
				if (husband == null)
					throw new RuntimeException("Husband " + husbandId + " not found.");
				this.addHusbandInFamily(husband);
			}
		this.setHusbandIdInFamily(null);
		if (this.wifeIdInFamily != null)
			for(IdElement wifeId:this.getWifeIdInFamily())
			{
				FamilyRecord wife = FamilyRecord.getFamilyRecordMap().get(wifeId.getId());
				if (wife == null)
					throw new RuntimeException("Husband " + wifeId + " not found.");
				this.addWifeInFamily(wife);
			}
		this.setWifeIdInFamily(null);
		if (this.sex != null)
			for(Sex sx:this.sex)
				sx.processInstruction();
		if (this.name != null)
			for(Name nm:this.name)
				nm.processInstruction();
		if (this.occupation != null)
			for(Event o:this.occupation)
				o.processInstruction();
		if (this.militaryService != null)
			for(Event e:this.militaryService)
				e.processInstruction();
		if (this.ordinance != null)
			for(Event e:this.ordinance)
				e.processInstruction();
		if (this.ssn != null)
			for(Event e:this.ssn)
				e.processInstruction();
		if (this.userReferenceNumber != null)
			for(Event e:this.userReferenceNumber)
				e.processInstruction();
		if (this.residence != null)
			for(Event e:this.residence)
				e.processInstruction();
		if (this.title != null)
			for(Event e:this.title)
				e.processInstruction();
		if (this.note != null)
			for(Event e:this.note)
				e.processInstruction();
		if (this.birth != null)
			for(Event e:this.birth)
				e.processInstruction();
		if (this.death != null)
			for(Event e:this.death)
				e.processInstruction();
		if (this.even != null)
			for(Event e:this.even)
				e.processInstruction();
		if (this.burial != null)
			for(Event e:this.burial)
				e.processInstruction();
		if (this.blessing != null)
			for(Event e:this.blessing)
				e.processInstruction();
		if (this.adoption != null)
			for(Event e:this.adoption)
				e.processInstruction();
		if (this.christening != null)
			for(Event e:this.christening)
				e.processInstruction();
	}
}
