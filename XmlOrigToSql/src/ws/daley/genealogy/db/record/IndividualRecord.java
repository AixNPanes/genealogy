package ws.daley.genealogy.db.record;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.Gedcom55;
import ws.daley.genealogy.db.structure.AliasToIndividualLinkStructure;
import ws.daley.genealogy.db.structure.AncestorToIndividualLinkStructure;
import ws.daley.genealogy.db.structure.AssociationStructure;
import ws.daley.genealogy.db.structure.ChildToFamilyLinkStructure;
import ws.daley.genealogy.db.structure.DescendentToIndividualLinkStructure;
import ws.daley.genealogy.db.structure.LDSIndividualOrdinanceStructure;
import ws.daley.genealogy.db.structure.MultimediaLinkStructure;
import ws.daley.genealogy.db.structure.NoteStructure;
import ws.daley.genealogy.db.structure.PersonalNameStructure;
import ws.daley.genealogy.db.structure.SourceCitationStructure;
import ws.daley.genealogy.db.structure.SpouseToFamilyLinkStructure;
import ws.daley.genealogy.db.structure.SubmitterStructure;
import ws.daley.genealogy.db.structure.UserReferenceNumberStructure;
import ws.daley.genealogy.db.structure.attribute.individual.IndividualAttributeStructure;
import ws.daley.genealogy.db.structure.event.individual.IndividualEventStructure;
import ws.daley.genealogy.util.Util;

/**
 * 
 * @author Tim.Daley
 *
 *				INDIVIDUAL_RECORD:=

				n @XREF:INDI@ INDI														{1:1} 
					+1 RESN <RESTRICTION_NOTICE>										{0:1} 
					+1 <<PERSONAL_NAME_STRUCTURE>>										{0:M} 
					+1 SEX <SEX_VALUE> 													{0:1} 
					+1 <<INDIVIDUAL_EVENT_STRUCTURE>>									{0:M} 
					+1 <<INDIVIDUAL_ATTRIBUTE_STRUCTURE>>								{0:M}
					+1 <<LDS_INDIVIDUAL_ORDINANCE>>										{0:M} 
					+1 <<CHILD_TO_FAMILY_LINK>>											{0:M} 
					+1 <<SPOUSE_TO_FAMILY_LINK>>										{0:M} 
					+1 SUBM @<XREF:SUBM>@												{0:M} 
					+1 <<ASSOCIATION_STRUCTURE>>										{0:M} 
					+1 ALIA @<XREF:INDI>@												{0:M} 
					+1 ANCI @<XREF:SUBM>@												{0:M} 
					+1 DESI @<XREF:SUBM>@												{0:M} 
					+1 <<SOURCE_CITATION>>												{0:M} 
					+1 <<MULTIMEDIA_LINK>>												{0:M} 
					+1 <<NOTE_STRUCTURE>>												{0:M} 
					+1 RFN <PERMANENT_RECORD_FILE_NUMBER>								{0:1} 
					+1 AFN <ANCESTRAL_FILE_NUMBER>										{0:1} 
					+1 REFN <USER_REFERENCE_NUMBER>										{0:M}
						+2 TYPE <USER_REFERENCE_TYPE>									{0:1} 
					+1 RIN <AUTOMATED_RECORD_ID>										{0:1} 
					+1 <<CHANGE_DATE>>													{0:1}

 */

public class IndividualRecord extends GedRecord
{
    private static final long serialVersionUID = 1L;
    
    private Gedcom55 gedcom55;
    public Gedcom55 getGedcom55() {return this.gedcom55;}
    public void setGedcom55(Gedcom55 gedcom55) {this.gedcom55 = gedcom55;}

    private String restrictionNotice;
    public String getRestrictionNotice() {return this.restrictionNotice;}
    public void setRestrictionNotice(String restrictionNotice) {this.restrictionNotice = restrictionNotice;}

    private String sex;
    public String getSex() {return this.sex;}
    public void setSex(String sex) {this.sex = sex;}

    private String recordFileNumber;
    public String getRecordFileNumber() {return this.recordFileNumber;}
    public void setRecordFileNumber(String recordFileNumber) {this.recordFileNumber = recordFileNumber;}

    private String ancestralFileNumber;
    public String getAncestralFileNumber() {return this.ancestralFileNumber;}
    public void setAncestralFileNumber(String ancestralFileNumber) {this.ancestralFileNumber = ancestralFileNumber;}
    
    private String alias;
    public String getAlias() {return this.alias;}
    public void setAlias(String alias) {this.alias = alias;}

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

    private List<UserReferenceNumberStructure> userReferenceNumbers = new ArrayList<UserReferenceNumberStructure>();
    public List<UserReferenceNumberStructure> getUserReferenceNumbers() {return this.userReferenceNumbers;}
    public void setUserReferenceNumbers(List<UserReferenceNumberStructure> userReferenceNumbers) {this.userReferenceNumbers = userReferenceNumbers;}
    public void addUserReferenceNumber(UserReferenceNumberStructure userReferenceNumber) {this.userReferenceNumbers.add(userReferenceNumber);}

    private List<IndividualEventStructure> events = new ArrayList<IndividualEventStructure>();
    public List<IndividualEventStructure> getEvents() {return this.events;}
    public void setEvents(List<IndividualEventStructure> events) {this.events = events;}
    public void addEvent(IndividualEventStructure event) {this.events.add(event);}

    private List<IndividualAttributeStructure> attributes = new ArrayList<IndividualAttributeStructure>();
    public List<IndividualAttributeStructure> getAttributes() {return this.attributes;}
    public void setAttributes(List<IndividualAttributeStructure> attributes) {this.attributes = attributes;}
    public void addAttribute(IndividualAttributeStructure attribute) {this.attributes.add(attribute);}

    private List<LDSIndividualOrdinanceStructure> ldsIndividualOrdinances = new ArrayList<LDSIndividualOrdinanceStructure>();
    public List<LDSIndividualOrdinanceStructure> getLDSIndividualOrdinances() {return this.ldsIndividualOrdinances;}
    public void setLDSIndividualOrdinances(List<LDSIndividualOrdinanceStructure> lDSIndividualOrdinances) {this.ldsIndividualOrdinances = lDSIndividualOrdinances;}
    public void addLDSIndividualOrdinance(LDSIndividualOrdinanceStructure lDSIndividualOrdinance) {this.ldsIndividualOrdinances.add(lDSIndividualOrdinance);}

    private List<ChildToFamilyLinkStructure> childToFamilyLinks = new ArrayList<ChildToFamilyLinkStructure>();
    public List<ChildToFamilyLinkStructure> getChildToFamilyLinks() {return this.childToFamilyLinks;}
    public void setChildToFamilyLinks(List<ChildToFamilyLinkStructure> childToFamilyLinks) {this.childToFamilyLinks = childToFamilyLinks;}
    public void addChildToFamilyLink(ChildToFamilyLinkStructure childToFamilyLink) {this.childToFamilyLinks.add(childToFamilyLink);}

    private List<SpouseToFamilyLinkStructure> spouseToFamilyLinks = new ArrayList<SpouseToFamilyLinkStructure>();
    public List<SpouseToFamilyLinkStructure> getSpouseToFamilyLinks() {return this.spouseToFamilyLinks;}
    public void setSpouseToFamilyLinks(List<SpouseToFamilyLinkStructure> spouseToFamilyLinks) {this.spouseToFamilyLinks = spouseToFamilyLinks;}
    public void addSpouseToFamilyLink(SpouseToFamilyLinkStructure spouseToFamilyLink) {this.spouseToFamilyLinks.add(spouseToFamilyLink);}

    private List<AliasToIndividualLinkStructure> aliasToIndividualLinks = new ArrayList<AliasToIndividualLinkStructure>();
    public List<AliasToIndividualLinkStructure> getAoiasToIndividualLinks() {return this.aliasToIndividualLinks;}
    public void setAliasToIndividualLinks(List<AliasToIndividualLinkStructure> aliasToIndividualLinks) {this.aliasToIndividualLinks = aliasToIndividualLinks;}
    public void addAliasToIndividualLink(AliasToIndividualLinkStructure aliasToIndividualLink) {this.aliasToIndividualLinks.add(aliasToIndividualLink);}

    private List<AncestorToIndividualLinkStructure> ancestorToIndividualLinks = new ArrayList<AncestorToIndividualLinkStructure>();
    public List<AncestorToIndividualLinkStructure> getAncestorToIndividualLinks() {return this.ancestorToIndividualLinks;}
    public void setAncestorToIndividualLinks(List<AncestorToIndividualLinkStructure> ancestorToIndividualLinks) {this.ancestorToIndividualLinks = ancestorToIndividualLinks;}
    public void addAncestorToIndividualLink(AncestorToIndividualLinkStructure ancestorToIndividualLink) {this.ancestorToIndividualLinks.add(ancestorToIndividualLink);}

    private List<DescendentToIndividualLinkStructure> descendentToIndividualLinks = new ArrayList<DescendentToIndividualLinkStructure>();
    public List<DescendentToIndividualLinkStructure> getDescendentToIndividualLinks() {return this.descendentToIndividualLinks;}
    public void setDescendentToIndividualLinks(List<DescendentToIndividualLinkStructure> descendentToIndividualLinks) {this.descendentToIndividualLinks = descendentToIndividualLinks;}
    public void addDescendentToIndividualLink(DescendentToIndividualLinkStructure descendentToIndividualLink) {this.descendentToIndividualLinks.add(descendentToIndividualLink);}

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

    private List<PersonalNameStructure> personalNameStructures = new ArrayList<PersonalNameStructure>();
    public List<PersonalNameStructure> getPersonalNameStructures() {return this.personalNameStructures;}
    public void setPersonalNameStructures(List<PersonalNameStructure> personalNameStructures) {this.personalNameStructures = personalNameStructures;}
    public void addPersonalNameStructure(PersonalNameStructure personalNameStructure) {this.personalNameStructures.add(personalNameStructure);}

    private List<AssociationStructure> associationStructures = new ArrayList<AssociationStructure>();
    public List<AssociationStructure> getAssociationStructures() {return this.associationStructures;}
    public void setAssociationStructures(List<AssociationStructure> associationStructures) {this.associationStructures = associationStructures;}
    public void addAssociationStructure(AssociationStructure associationStructure) {this.associationStructures.add(associationStructure);}

    public IndividualRecord() {}
    
    public IndividualRecord(Gedcom55 parent, Node node)
    {
    	super(node);
    	this.gedcom55 = parent;
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("RESN".equals(childNode.getNodeName()))
			{
				this.setRestrictionNotice(Util.getNodeValue(childNode));
			}
			else if ("SEX".equals(childNode.getNodeName()))
			{
				this.setSex(Util.getNodeValue(childNode));
			}
			else if ("RFN".equals(childNode.getNodeName()))
			{
				this.setRecordFileNumber(Util.getNodeValue(childNode));
			}
			else if ("AFN".equals(childNode.getNodeName()))
			{
				this.setAncestralFileNumber(Util.getNodeValue(childNode));
			}
			else if ("ALIA".equals(childNode.getNodeName()))
			{
				this.addAliasToIndividualLink(new AliasToIndividualLinkStructure(childNode));
			}
			else if ("CHAN".equals(childNode.getNodeName()))
			{
				this.setChangeDate(Util.getNodeValueWithChild(this.getClass(), childNode));
			}
			else if ("SUBM".equals(childNode.getNodeName()))
			{
				this.addSubmitter(new SubmitterStructure(childNode));
			}
			else if ("REFN".equals(childNode.getNodeName()))
			{
				this.addUserReferenceNumber(new UserReferenceNumberStructure(childNode));
			}
			else if ("FAMC".equals(childNode.getNodeName()))
			{
				this.addChildToFamilyLink(new ChildToFamilyLinkStructure(childNode));
			}
			else if ("FAMS".equals(childNode.getNodeName()))
			{
				this.addSpouseToFamilyLink(new SpouseToFamilyLinkStructure(childNode));
			}
			else if ("ANCI".equals(childNode.getNodeName()))
			{
				this.addAncestorToIndividualLink(new AncestorToIndividualLinkStructure(childNode));
			}
			else if ("DESI".equals(childNode.getNodeName()))
			{
				this.addDescendentToIndividualLink(new DescendentToIndividualLinkStructure(childNode));
			}
			else if ("RIN".equals(childNode.getNodeName()))
			{
				this.setAutomatedRecordId(Util.getNodeValue(childNode));
			}
			else if ("SOUR".equals(childNode.getNodeName()))
			{
				this.addSourceCitation(new SourceCitationStructure(childNode));
			}
			else if ("NOTE".equals(childNode.getNodeName()))
			{
				this.addNoteStructure(new NoteStructure(childNode));
			}
			else if ("ASSO".equals(childNode.getNodeName()))
			{
				this.addAssociationStructure(new AssociationStructure(childNode));
			}
			else if ("SLGC".equals(childNode.getNodeName()))
			{
				this.addLDSIndividualOrdinance(new LDSIndividualOrdinanceStructure(childNode));
			}
			else if ("BAPL".equals(childNode.getNodeName()))
			{
				this.addLDSIndividualOrdinance(new LDSIndividualOrdinanceStructure(childNode));
			}
			else if ("CONL".equals(childNode.getNodeName()))
			{
				this.addLDSIndividualOrdinance(new LDSIndividualOrdinanceStructure(childNode));
			}
			else if ("ENDL".equals(childNode.getNodeName()))
			{
				this.addLDSIndividualOrdinance(new LDSIndividualOrdinanceStructure(childNode));
			}
			else if ("BIRT".equals(childNode.getNodeName()))
			{
				this.addEvent(new IndividualEventStructure(childNode));
			}
			else if ("BIRT".equals(childNode.getNodeName()))
			{
				this.addEvent(new IndividualEventStructure(childNode));
			}
			else if ("CHR".equals(childNode.getNodeName()))
			{
				this.addEvent(new IndividualEventStructure(childNode));
			}
			else if ("ADOP".equals(childNode.getNodeName()))
			{
				this.addEvent(new IndividualEventStructure(childNode));
			}
			else if ("DEAT".equals(childNode.getNodeName()))
			{
				this.addEvent(new IndividualEventStructure(childNode));
			}
			else if ("BURI".equals(childNode.getNodeName()))
			{
				this.addEvent(new IndividualEventStructure(childNode));
			}
			else if ("CREM".equals(childNode.getNodeName()))
			{
				this.addEvent(new IndividualEventStructure(childNode));
			}
			else if ("BAPM".equals(childNode.getNodeName()))
			{
				this.addEvent(new IndividualEventStructure(childNode));
			}
			else if ("BARM".equals(childNode.getNodeName()))
			{
				this.addEvent(new IndividualEventStructure(childNode));
			}
			else if ("BASM".equals(childNode.getNodeName()))
			{
				this.addEvent(new IndividualEventStructure(childNode));
			}
			else if ("BLES".equals(childNode.getNodeName()))
			{
				this.addEvent(new IndividualEventStructure(childNode));
			}
			else if ("CHRA".equals(childNode.getNodeName()))
			{
				this.addEvent(new IndividualEventStructure(childNode));
			}
			else if ("CONF".equals(childNode.getNodeName()))
			{
				this.addEvent(new IndividualEventStructure(childNode));
			}
			else if ("FCOM".equals(childNode.getNodeName()))
			{
				this.addEvent(new IndividualEventStructure(childNode));
			}
			else if ("ORDN".equals(childNode.getNodeName()))
			{
				this.addEvent(new IndividualEventStructure(childNode));
			}
			else if ("NATU".equals(childNode.getNodeName()))
			{
				this.addEvent(new IndividualEventStructure(childNode));
			}
			else if ("EMIG".equals(childNode.getNodeName()))
			{
				this.addEvent(new IndividualEventStructure(childNode));
			}
			else if ("IMMI".equals(childNode.getNodeName()))
			{
				this.addEvent(new IndividualEventStructure(childNode));
			}
			else if ("PROB".equals(childNode.getNodeName()))
			{
				this.addEvent(new IndividualEventStructure(childNode));
			}
			else if ("WILL".equals(childNode.getNodeName()))
			{
				this.addEvent(new IndividualEventStructure(childNode));
			}
			else if ("GRAD".equals(childNode.getNodeName()))
			{
				this.addEvent(new IndividualEventStructure(childNode));
			}
			else if ("RETI".equals(childNode.getNodeName()))
			{
				this.addEvent(new IndividualEventStructure(childNode));
			}
			else if ("CESN".equals(childNode.getNodeName()))
			{
				this.addEvent(new IndividualEventStructure(childNode));
			}
			else if ("EVEN".equals(childNode.getNodeName()))
			{
				this.addEvent(new IndividualEventStructure(childNode));
			}
			else if ("BURI".equals(childNode.getNodeName()))
			{
				this.addEvent(new IndividualEventStructure(childNode));
			}
			else if ("CAST".equals(childNode.getNodeName()))
			{
				this.addEvent(new IndividualEventStructure(childNode));
			}
			else if ("_MILT".equals(childNode.getNodeName()))
			{
				this.addEvent(new IndividualEventStructure(childNode));
			}
			else if ("DSCR".equals(childNode.getNodeName()))
			{
				this.addAttribute(new IndividualAttributeStructure(childNode));
			}
			else if ("EDUC".equals(childNode.getNodeName()))
			{
				this.addAttribute(new IndividualAttributeStructure(childNode));
			}
			else if ("IDNO".equals(childNode.getNodeName()))
			{
				this.addAttribute(new IndividualAttributeStructure(childNode));
			}
			else if ("NATI".equals(childNode.getNodeName()))
			{
				this.addAttribute(new IndividualAttributeStructure(childNode));
			}
			else if ("NCHI".equals(childNode.getNodeName()))
			{
				this.addAttribute(new IndividualAttributeStructure(childNode));
			}
			else if ("NMR".equals(childNode.getNodeName()))
			{
				this.addAttribute(new IndividualAttributeStructure(childNode));
			}
			else if ("OCCU".equals(childNode.getNodeName()))
			{
				this.addAttribute(new IndividualAttributeStructure(childNode));
			}
			else if ("PROP".equals(childNode.getNodeName()))
			{
				this.addAttribute(new IndividualAttributeStructure(childNode));
			}
			else if ("RELI".equals(childNode.getNodeName()))
			{
				this.addAttribute(new IndividualAttributeStructure(childNode));
			}
			else if ("RESI".equals(childNode.getNodeName()))
			{
				this.addAttribute(new IndividualAttributeStructure(childNode));
			}
			else if ("SSN".equals(childNode.getNodeName()))
			{
				this.addAttribute(new IndividualAttributeStructure(childNode));
			}
			else if ("TITL".equals(childNode.getNodeName()))
			{
				this.addAttribute(new IndividualAttributeStructure(childNode));
			}
			else if ("NAME".equals(childNode.getNodeName()))
			{
				this.addPersonalNameStructure(new PersonalNameStructure(childNode));
			}
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }
}
