package ws.daley.genealogy.db.people;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.IdString;
import ws.daley.genealogy.db.Instruction;
import ws.daley.genealogy.db.people.element.Address;
import ws.daley.genealogy.db.people.element.EMail;
import ws.daley.genealogy.db.people.element.Phone;
import ws.daley.genealogy.db.people.structure.Name;

@Entity
public class RepositoryRecord extends IdString implements Serializable
{
	@Transient
	private static final long serialVersionUID = 1L;
	
	@Transient
	private static HashMap<String, RepositoryRecord> map = null; 
    public static HashMap<String, RepositoryRecord> getRepositoryRecordMap() {return map;}
    public static void setRepositoryRecordMap(HashMap<String, RepositoryRecord> newRepositoryRecordMap) {RepositoryRecord.map = newRepositoryRecordMap;}
    
	@OneToMany
	private ArrayList<Name> name = null;
    public ArrayList<Name> getName() {return this.name;}
    public void setName(ArrayList<Name> newName) {this.name = newName;}
    public void addName(Name newName)
    {
    	if (this.getName() == null)
    		this.setName(new ArrayList<Name>());
    	this.getName().add(newName);
    }
    
	@OneToMany
	private ArrayList<Address> address = null;
    public ArrayList<Address> getAddress() {return this.address;}
    public void setAddress(ArrayList<Address> newAddress) {this.address = newAddress;}
    public void addAddress(Address newAddress)
    {
    	if (this.getAddress() == null)
    		this.setAddress(new ArrayList<Address>());
    	this.getAddress().add(newAddress);
    }
    
	@OneToMany
	private ArrayList<EMail> email = null;
    public ArrayList<EMail> getEmail() {return this.email;}
    public void setEmail(ArrayList<EMail> newEmail) {this.email = newEmail;}
    public void addEmail(EMail newEmail)
    {
    	if (this.getEmail() == null)
    		this.setEmail(new ArrayList<EMail>());
    	this.getEmail().add(newEmail);
    }
    
	@OneToMany
	private ArrayList<Phone> phone = null;
    public ArrayList<Phone> getPhone() {return this.phone;}
    public void setPhone(ArrayList<Phone> newPhone) {this.phone = newPhone;}
    public void addPhone(Phone newPhone)
    {
    	if (this.getPhone() == null)
    		this.setPhone(new ArrayList<Phone>());
    	this.getPhone().add(newPhone);
    }

    @Transient
    private static Instruction[] instructions = new Instruction[] {
    	new Instruction("NAME", "name"),
    	new Instruction("ADDR", "address"),
    	new Instruction("EMAIL", "email"),
    	new Instruction("PHON", "phone")
    };

    public RepositoryRecord() {super();}
	
    public RepositoryRecord(Node node) {super(node);}
	
	public void processInstructions()
	{
		Instruction.buildChildren(this, instructions, this.nodeChildren);
	}
	
	public static RepositoryRecord getRepositoryRecord(Node node)
	{
		if (map == null)
			map = new HashMap<String, RepositoryRecord>();
		String nodeName = node.getAttributes().getNamedItem("id").getNodeValue();
		RepositoryRecord record = map.get(nodeName);
		if (record != null)
			return record;
		record = new RepositoryRecord(node);
		map.put(nodeName, record);
		return record;
	}

	public void resolveIds() {}
}
