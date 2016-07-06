package ws.daley.genealogy.db.record;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.Gedcom55;
import ws.daley.genealogy.db.structure.AddressStructure;
import ws.daley.genealogy.db.structure.NoteStructure;
import ws.daley.genealogy.db.structure.UserReferenceNumberStructure;
import ws.daley.genealogy.util.Util;

/**
 * 
 * @author Tim.Daley
 *
 *				REPOSITORY_RECORD:=
				n @<XREF:REPO>@ REPO													{1:1} 
					+1 NAME <NAME_OF_REPOSITORY> 										{0:1} 
					+1 <<ADDRESS_STRUCTURE>>											{0:1} 
					+1 <<NOTE_STRUCTURE>>												{0:M} 
					+1 REFN <USER_REFERENCE_NUMBER>										{0:M}
						+2 TYPE <USER_REFERENCE_TYPE>									{0:1} 
					+1 RIN <AUTOMATED_RECORD_ID>										{0:1} 
					+1 <<CHANGE_DATE>>													{0:1}
 */

/**
 * 
 * @author Tim.Daley
 *
 *	ADDRESS_STRUCTURE:=
		n ADDR <ADDRESS_LINE> {1:1} p.41
			+1 CONT <ADDRESS_LINE> {0:3} p.41
			+1 ADR1 <ADDRESS_LINE1> {0:1} p.41
			+1 ADR2 <ADDRESS_LINE2> {0:1} p.41
			+1 ADR3 <ADDRESS_LINE3> {0:1} p.41
			+1 CITY <ADDRESS_CITY> {0:1} p.41
			+1 STAE <ADDRESS_STATE> {0:1} p.42
			+1 POST <ADDRESS_POSTAL_CODE> {0:1} p.41
			+1 CTRY <ADDRESS_COUNTRY> {0:1} p.41
		n PHON <PHONE_NUMBER> {0:3} p.57
		n EMAIL <ADDRESS_EMAIL> {0:3} p.41
		n FAX <ADDRESS_FAX> {0:3} p.41
		n WWW <ADDRESS_WEB_PAGE> {0:3} p.42
 */

public class RepositoryRecord extends GedRecord
{
    private static final long serialVersionUID = 1L;
    
    private Gedcom55 gedcom55;
    public Gedcom55 getGedcom55() {return this.gedcom55;}
    public void setGedcom55(Gedcom55 gedcom55) {this.gedcom55 = gedcom55;}
    
	private String nameOfRepository;
    public String getNameOfRepository() {return this.nameOfRepository;}
    public void setNameOfRepository(String nameOfRepository) {this.nameOfRepository = nameOfRepository;}

	private String automatedRecordId;
    public String getAutomatedRecordId() {return this.automatedRecordId;}
    public void setAutomatedRecordId(String automatedRecordIdStructure) {this.automatedRecordId = automatedRecordIdStructure;}

    private String changeDate;
    public String getChangeDate() {return this.changeDate;}
    public void setChangeDate(String changeDate) {this.changeDate = changeDate;}

    private AddressStructure addressStructure;
    public AddressStructure getAddressStructure() {return this.addressStructure;}
    public void setAddressStructure(AddressStructure addressStructure) {this.addressStructure = addressStructure;}

    private List<UserReferenceNumberStructure> userReferenceNumberStructures = new ArrayList<UserReferenceNumberStructure>();
    public List<UserReferenceNumberStructure> getUserReferenceNumberStructures() {return this.userReferenceNumberStructures;}
    public void setUserReferenceNumberStructures(List<UserReferenceNumberStructure> userReferenceNumberStructures) {this.userReferenceNumberStructures = userReferenceNumberStructures;}
    public void addUserReferenceNumberStructure(UserReferenceNumberStructure userReferenceNumberStructure) {this.userReferenceNumberStructures.add(userReferenceNumberStructure);}

    private List<NoteStructure> noteStructures = new ArrayList<NoteStructure>();
    public List<NoteStructure> getNoteStructures() {return this.noteStructures;}
    public void setNoteStructures(List<NoteStructure> noteStructures) {this.noteStructures = noteStructures;}
    public void addNoteStructure(NoteStructure noteStructure) {this.noteStructures.add(noteStructure);}
    
	public RepositoryRecord() {}
    
    public RepositoryRecord(Gedcom55 parent, Node node)
    {
    	super(node);
    	this.gedcom55 = parent;
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("NAME".equals(childNode.getNodeName()))
				this.setNameOfRepository(Util.getNodeValue(childNode));
			else if ("ADDR".equals(childNode.getNodeName()))
				this.setAddressStructure(new AddressStructure(childNode));
			else if ("PHON".equals(childNode.getNodeName()))
				this.getAddressStructure().addPhoneNumberStructure(Util.getNodeValue(childNode));
			else if ("EMAIL".equals(childNode.getNodeName()))
				this.getAddressStructure().addAddressEmailStructure(Util.getNodeValue(childNode));
			else if ("FAX".equals(childNode.getNodeName()))
				this.getAddressStructure().addAddressFaxStructure(Util.getNodeValue(childNode));
			else if ("WWW".equals(childNode.getNodeName()))
				this.getAddressStructure().addAddressWWWStructure(Util.getNodeValue(childNode));
			else if ("REFN".equals(childNode.getNodeName()))
				this.addUserReferenceNumberStructure(new UserReferenceNumberStructure(childNode));
			else if ("NOTE".equals(childNode.getNodeName()))
				this.addNoteStructure(new NoteStructure(childNode));
			else if ("RIN".equals(childNode.getNodeName()))
				this.setAutomatedRecordId(Util.getNodeValue(childNode));
			else if ("CHAN".equals(childNode.getNodeName()))
				this.setChangeDate(Util.getNodeValueWithChild(this.getClass(), childNode));
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }
}
