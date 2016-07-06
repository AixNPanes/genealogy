package ws.daley.genealogy.db.record;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.Gedcom55;
import ws.daley.genealogy.db.structure.MultimediaLinkStructure;
import ws.daley.genealogy.db.structure.NoteStructure;
import ws.daley.genealogy.db.structure.UserReferenceNumberStructure;
import ws.daley.genealogy.db.structure.source.SourceDataStructure;
import ws.daley.genealogy.db.structure.source.SourceRepoCitationStructure;
import ws.daley.genealogy.util.Util;

/**
 * 
 * @author Tim.Daley
 *
 *				SOURCE_RECORD:=
				n @<XREF:SOUR>@ SOUR													{1:1} 
					+1 DATA																{0:1} 
						+2 EVEN <EVENTS_RECORDED>										{0:M} 
							+3 DATE <DATE_PERIOD>										{0:1} 
							+3 PLAC <SOURCE_JURISDICTION_PLACE>							{0:1} 
						+2 AGNC <RESPONSIBLE_AGENCY>									{0:1} 
						+2 <<NOTE_STRUCTURE>>											{0:M} 
					+1 AUTH <SOURCE_ORIGINATOR>											{0:1} 
						+2 [CONT|CONC] <SOURCE_ORIGINATOR>								{0:M} 
					+1 TITL <SOURCE_DESCRIPTIVE_TITLE>									{0:1} 
						+2 [CONT|CONC] <SOURCE_DESCRIPTIVE_TITLE>						{0:M} 
					+1 ABBR <SOURCE_FILED_BY_ENTRY>										{0:1} 
					+1 PUBL <SOURCE_PUBLICATION_FACTS>									{0:1} 
						+2 [CONT|CONC] <SOURCE_PUBLICATION_FACTS>						{0:M} 
					+1 TEXT <TEXT_FROM_SOURCE>											{0:1} 
						+2 [CONT|CONC] <TEXT_FROM_SOURCE>								{0:M} 
					+1 <<SOURCE_REPOSITORY_CITATION>>									{0:1} 
					+1 <<MULTIMEDIA_LINK>>												{0:M}
					+1 <<NOTE_STRUCTURE>>												{0:M} 
					+1 REFN <USER_REFERENCE_NUMBER>										{0:M}
						+2 TYPE <USER_REFERENCE_TYPE>									{0:1} 
					+1 RIN <AUTOMATED_RECORD_ID>										{0:1} 
					+1 <<CHANGE_DATE>>													{0:1}

 */

public class SourceRecord extends GedRecord
{
    private static final long serialVersionUID = 1L;
    
    private Gedcom55 gedcom55;
    public Gedcom55 getGedcom55() {return this.gedcom55;}
    public void setGedcom55(Gedcom55 gedcom55) {this.gedcom55 = gedcom55;}

    private String originator;
    public String getSourceOriginator() {return this.originator;}
    public void setSourceOriginator(String riginator) {this.originator = riginator;}

    private String title;
    public String getSourceTitle() {return this.title;}
    public void setSourceTitle(String title) {this.title = title;}

    private String filedByEntry;
    public String getFiledByEntry() {return this.filedByEntry;}
    public void setFiledByEntry(String filedByEntry) {this.filedByEntry = filedByEntry;}

    private String publicationFacts;
    public String getPublicationFacts() {return this.publicationFacts;}
    public void setPublicationFacts(String publicationFacts) {this.publicationFacts = publicationFacts;}

    private String textFromSource;
    public String getTextFromSource() {return this.textFromSource;}
    public void setTextFromSource(String textFromSource) {this.textFromSource = textFromSource;}

    private String automatedRecordId;
    public String getAutomatedRecordId() {return this.automatedRecordId;}
    public void setAutomatedRecordId(String automatedRecordIdStructures) {this.automatedRecordId = automatedRecordIdStructures;}

    private String changeDate;
    public String getChangeDate() {return this.changeDate;}
    public void setChangeDate(String changeDate) {this.changeDate = changeDate;}

    private SourceRepoCitationStructure sourceRepositoryCitationStructure;
    public SourceRepoCitationStructure getSourceRepositoryCitationStructure() {return this.sourceRepositoryCitationStructure;}
    public void setSourceRepositoryCitationStructure(SourceRepoCitationStructure sourceRepositoryCitationStructure) {this.sourceRepositoryCitationStructure = sourceRepositoryCitationStructure;}

	private SourceDataStructure sourceDataStructure;
    public SourceDataStructure getSourceDataStructure() {return this.sourceDataStructure;}
    public void setSourceDataStructure(SourceDataStructure sourceDataStructure) {this.sourceDataStructure = sourceDataStructure;}

    private List<MultimediaLinkStructure> multimediaLinkStructures = new ArrayList<MultimediaLinkStructure>();
    public List<MultimediaLinkStructure> getMultimediaLinkStructures() {return this.multimediaLinkStructures;}
    public void setMultimediaLinkStructures(List<MultimediaLinkStructure> multimediaLinkStructures) {this.multimediaLinkStructures = multimediaLinkStructures;}
    public void addMultimediaLinkStructure(MultimediaLinkStructure multimediaLinkStructure) {this.multimediaLinkStructures.add(multimediaLinkStructure);}

    private List<NoteStructure> noteStructures = new ArrayList<NoteStructure>();
    public List<NoteStructure> getNoteStructures() {return this.noteStructures;}
    public void setNoteStructures(List<NoteStructure> noteStructures) {this.noteStructures = noteStructures;}
    public void addNoteStructure(NoteStructure noteStructure) {this.noteStructures.add(noteStructure);}

    private List<UserReferenceNumberStructure> userReferenceNumberStructures = new ArrayList<UserReferenceNumberStructure>();
    public List<UserReferenceNumberStructure> getUserReferenceNumberStructures() {return this.userReferenceNumberStructures;}
    public void setUserReferenceNumberStructures(List<UserReferenceNumberStructure> userReferenceNumberStructures) {this.userReferenceNumberStructures = userReferenceNumberStructures;}
    public void addUserReferenceNumberStructure(UserReferenceNumberStructure userReferenceNumberStructure) {this.userReferenceNumberStructures.add(userReferenceNumberStructure);}

    public SourceRecord() {}
    
    public SourceRecord(Gedcom55 parent, Node node)
    {
    	super(node);
    	this.gedcom55 = parent;
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("DATA".equals(childNode.getNodeName()))
			{
				this.setSourceDataStructure(new SourceDataStructure(childNode));
			}
			else if ("AUTH".equals(childNode.getNodeName()))
			{
				this.setSourceOriginator(Util.getNodeValueWithChild(this.getClass(), childNode));
			}
			else if ("TITL".equals(childNode.getNodeName()))
			{
				this.setSourceTitle(Util.getNodeValueWithChild(this.getClass(), childNode));
			}
			else if ("ABBR".equals(childNode.getNodeName()))
			{
				this.setFiledByEntry(Util.getNodeValue(childNode));
			}
			else if ("PUBL".equals(childNode.getNodeName()))
			{
				this.setPublicationFacts(Util.getNodeValueWithChild(this.getClass(), childNode));
			}
			else if ("TEXT".equals(childNode.getNodeName()))
			{
				this.setTextFromSource(Util.getNodeValueWithChild(this.getClass(), childNode));
			}
			else if ("REPO".equals(childNode.getNodeName()))
			{
				this.setSourceRepositoryCitationStructure(new SourceRepoCitationStructure(childNode));
			}
			else if ("OBJE".equals(childNode.getNodeName()))
			{
				this.addMultimediaLinkStructure(new MultimediaLinkStructure(childNode));
			}
			else if ("NOTE".equals(childNode.getNodeName()))
			{
				this.addNoteStructure(new NoteStructure(childNode));
			}
			else if ("REFN".equals(childNode.getNodeName()))
			{
				this.addUserReferenceNumberStructure(new UserReferenceNumberStructure(childNode));
			}
			else if ("RIN".equals(childNode.getNodeName()))
			{
				this.setAutomatedRecordId(Util.getNodeValue(childNode));
			}
			else if ("CHAN".equals(childNode.getNodeName()))
			{
				this.setChangeDate(Util.getNodeValueWithChild(this.getClass(), childNode));
			}
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }
}
