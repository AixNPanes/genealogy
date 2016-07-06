package ws.daley.genealogy.db.record;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.Gedcom55;
import ws.daley.genealogy.db.structure.ChildLinkStructure;
import ws.daley.genealogy.db.structure.LDSSpouseSealingStructure;
import ws.daley.genealogy.db.structure.MultimediaLinkStructure;
import ws.daley.genealogy.db.structure.NoteStructure;
import ws.daley.genealogy.db.structure.SourceCitationStructure;
import ws.daley.genealogy.db.structure.SubmitterStructure;
import ws.daley.genealogy.db.structure.UserReferenceNumberStructure;
import ws.daley.genealogy.db.structure.event.family.FamilyEventStructure;
import ws.daley.genealogy.util.Util;

/**
 * 
 * @author Tim.Daley
 *
 *				FAM_RECORD:=

				n @<XREF:FAM>@ FAM														{1:1} 
					+1 <<FAMILY_EVENT_STRUCTURE>>										{0:M} 
						+2 HUSB															{0:1} 
							+3 AGE <AGE_AT_EVENT>										{1:1} 
						+2 WIFE															{0:1} 
							+3 AGE <AGE_AT_EVENT>										{1:1} 
					+1 HUSB @<XREF:INDI>@												{0:1} 
					+1 WIFE @<XREF:INDI>@												{0:1} 
					+1 CHIL @<XREF:INDI>@												{0:M} 
					+1 NCHI <COUNT_OF_CHILDREN>											{0:1} 
					+1 SUBM @<XREF:SUBM>@												{0:M} 
					+1 <<LDS_SPOUSE_SEALING>>											{0:M} 
					+1 <<SOURCE_CITATION>>												{0:M} 
					+1 <<MULTIMEDIA_LINK>>												{0:M}
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
 *				FAMILY_EVENT_STRUCTURE:= 
				[ 
				n ANUL [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n DIV [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n DIVF [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n ENGA [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n MARR [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n MARB [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n MARC [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n MARL [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n MARS [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n CENS [Y|<NULL>]														{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				| 
				n EVEN																	{1:1} 
					+1 <<EVENT_DETAIL>>													{0:1} 
				]
 */

public class FamilyRecord extends GedRecord
{
    private static final long serialVersionUID = 1L;
    
    private Gedcom55 gedcom55;
    public Gedcom55 getGedcom55() {return this.gedcom55;}
    public void setGedcom55(Gedcom55 gedcom55) {this.gedcom55 = gedcom55;}
    
    private String husband;
    public String getHusband() {return this.husband;}
    public void setHusband(String husband) {this.husband = husband;}
    
    private String wife;
    public String getWife() {return this.wife;}
    public void setWife(String wife) {this.wife = wife;}
    
    private Long numberOfChildren;
    public Long getNumberOfChildren() {return this.numberOfChildren;}
    public void setNumberOfChildren(Long numberOfChildren) {this.numberOfChildren = numberOfChildren;}

    private String automatedRecordId;
    public String getAutomatedRecordId() {return this.automatedRecordId;}
    public void setAutomatedRecordId(String automatedRecordId) {this.automatedRecordId = automatedRecordId;}

    private String changeDate;
    public String getChangeDate() {return this.changeDate;}
    public void setChangeDate(String changeDate) {this.changeDate = changeDate;}
    
    private List<SubmitterStructure> submitters = new ArrayList<SubmitterStructure>();
    public List<SubmitterStructure> getSubmitters() {return this.submitters;}
    public void setSubmitters(List<SubmitterStructure> submitters) {this.submitters = submitters;}
    public void addSubmitter(SubmitterStructure submitter) {this.submitters.add(submitter);}

    private List<SourceCitationStructure> sourceCitations = new ArrayList<SourceCitationStructure>();
    public List<SourceCitationStructure> getSourceCitations() {return this.sourceCitations;}
    public void setSourceCitations(List<SourceCitationStructure> sourceCitations) {this.sourceCitations = sourceCitations;}
    public void addSourceCitation(SourceCitationStructure sourceCitation) {this.sourceCitations.add(sourceCitation);}

    private List<MultimediaLinkStructure> multimediaLinks = new ArrayList<MultimediaLinkStructure>();
    public List<MultimediaLinkStructure> getMultimediaLinks() {return this.multimediaLinks;}
    public void setMultimediaLinks(List<MultimediaLinkStructure> multimediaLinks) {this.multimediaLinks = multimediaLinks;}
    public void addMultimediaLink(MultimediaLinkStructure multimediaLink) {this.multimediaLinks.add(multimediaLink);}

    private List<NoteStructure> noteStructures = new ArrayList<NoteStructure>();
    public List<NoteStructure> getNoteStructures() {return this.noteStructures;}
    public void setNoteStructures(List<NoteStructure> noteStructures) {this.noteStructures = noteStructures;}
    public void addNoteStructure(NoteStructure noteStructure) {this.noteStructures.add(noteStructure);}

    private List<FamilyEventStructure> events = new ArrayList<FamilyEventStructure>();
    public List<FamilyEventStructure> getEvents() {return this.events;}
    public void setEvents(List<FamilyEventStructure> events) {this.events = events;}
    public void addEvent(FamilyEventStructure event) {this.events.add(event);}

    private List<ChildLinkStructure> children = new ArrayList<ChildLinkStructure>();
    public List<ChildLinkStructure> getChildren() {return this.children;}
    public void setChildren(List<ChildLinkStructure> children) {this.children = children;}
    public void addChild(ChildLinkStructure child) {this.children.add(child);}

    private List<LDSSpouseSealingStructure> ldsSpouseSealings = new ArrayList<LDSSpouseSealingStructure>();
    public List<LDSSpouseSealingStructure> getLdsSpouseSealings() {return this.ldsSpouseSealings;}
    public void setLdsSpouseSealings(List<LDSSpouseSealingStructure> ldsSpouseSealings) {this.ldsSpouseSealings = ldsSpouseSealings;}
    public void addLdsSpouseSealing(LDSSpouseSealingStructure ldsSpouseSealing) {this.ldsSpouseSealings.add(ldsSpouseSealing);}

    private List<UserReferenceNumberStructure> userReferenceNumbers = new ArrayList<UserReferenceNumberStructure>();
    public List<UserReferenceNumberStructure> getUserReferenceNumbers() {return this.userReferenceNumbers;}
    public void setUserReferenceNumbers(List<UserReferenceNumberStructure> userReferenceNumbers) {this.userReferenceNumbers = userReferenceNumbers;}
    public void addUserReferenceNumber(UserReferenceNumberStructure userReferenceNumber) {this.userReferenceNumbers.add(userReferenceNumber);}
  
    public FamilyRecord() {}
    
    public FamilyRecord(Gedcom55 parent, Node node)
    {
    	super(node);
    	this.gedcom55 = parent;
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("REFN".equals(childNode.getNodeName()))
			{
				this.addUserReferenceNumber(new UserReferenceNumberStructure(childNode));
			}
			else if ("RIN".equals(childNode.getNodeName()))
			{
				this.setAutomatedRecordId(Util.getNodeValue(childNode));
			}
			else if ("HUSB".equals(childNode.getNodeName()))
			{
				this.setHusband(Util.getNodeValue(childNode));
			}
			else if ("WIFE".equals(childNode.getNodeName()))
			{
				this.setWife(Util.getNodeValue(childNode));
			}
			else if ("NCHI".equals(childNode.getNodeName()))
			{
				this.setNumberOfChildren(Long.parseLong(Util.getNodeValue(childNode)));
			}
			else if ("CHAN".equals(childNode.getNodeName()))
			{
				this.setChangeDate(Util.getNodeValueWithChild(this.getClass(), childNode));
			}
			else if ("SUBM".equals(childNode.getNodeName()))
			{
				this.addSubmitter(new SubmitterStructure(childNode));
			}
			else if ("ANUL".equals(childNode.getNodeName()))
			{
				this.addEvent(new FamilyEventStructure(childNode));
			}
			else if ("DIV".equals(childNode.getNodeName()))
			{
				this.addEvent(new FamilyEventStructure(childNode));
			}
			else if ("DIVF".equals(childNode.getNodeName()))
			{
				this.addEvent(new FamilyEventStructure(childNode));
			}
			else if ("ENGA".equals(childNode.getNodeName()))
			{
				this.addEvent(new FamilyEventStructure(childNode));
			}
			else if ("MARR".equals(childNode.getNodeName()))
			{
				this.addEvent(new FamilyEventStructure(childNode));
			}
			else if ("MARB".equals(childNode.getNodeName()))
			{
				this.addEvent(new FamilyEventStructure(childNode));
			}
			else if ("MARC".equals(childNode.getNodeName()))
			{
				this.addEvent(new FamilyEventStructure(childNode));
			}
			else if ("MARL".equals(childNode.getNodeName()))
			{
				this.addEvent(new FamilyEventStructure(childNode));
			}
			else if ("MARS".equals(childNode.getNodeName()))
			{
				this.addEvent(new FamilyEventStructure(childNode));
			}
			else if ("CENS".equals(childNode.getNodeName()))
			{
				this.addEvent(new FamilyEventStructure(childNode));
			}
			else if ("EVEN".equals(childNode.getNodeName()))
			{
				this.addEvent(new FamilyEventStructure(childNode));
			}
			else if ("SOUR".equals(childNode.getNodeName()))
			{
				this.addSourceCitation(new SourceCitationStructure(childNode));
			}
			else if ("NOTE".equals(childNode.getNodeName()))
			{
				this.addNoteStructure(new NoteStructure(childNode));
			}
			else if ("CHIL".equals(childNode.getNodeName()))
			{
				this.addChild(new ChildLinkStructure(childNode));
			}
			else if ("SLGS".equals(childNode.getNodeName()))
			{
				this.addLdsSpouseSealing(new LDSSpouseSealingStructure(childNode));
			}
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }
}
