package ws.daley.genealogy.db.structure.source;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.record.GedRecord;
import ws.daley.genealogy.db.structure.NoteStructure;
import ws.daley.genealogy.db.structure.source.data.SourceDataEventsRecordedStructure;
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

public class SourceDataStructure extends GedRecord
{
    private static final long serialVersionUID = 1L;

	private String responsibleAgency;
    public String getResponsibleAgency() {return this.responsibleAgency;}
    public void setResponsibleAgency(String responsibleAgency) {this.responsibleAgency = responsibleAgency;}

    private List<SourceDataEventsRecordedStructure> sourceDataEventsRecordedStructures = new ArrayList<SourceDataEventsRecordedStructure>();
    public List<SourceDataEventsRecordedStructure> getSourceDataEventRecordedStructures() {return this.sourceDataEventsRecordedStructures;}
    public void setSourceDataEventRecordedStructures(List<SourceDataEventsRecordedStructure> sourceDataEventRecordedStructures) {this.sourceDataEventsRecordedStructures = sourceDataEventRecordedStructures;}
    public void addSourceDataEventRecordedStructure(SourceDataEventsRecordedStructure sourceDataEventsRecordedStructure) {this.sourceDataEventsRecordedStructures.add(sourceDataEventsRecordedStructure);}

    private List<NoteStructure> noteStructures;
    public List<NoteStructure> getNoteStructures() {return this.noteStructures;}
    public void setNoteStructures(List<NoteStructure> noteStructures) {this.noteStructures = noteStructures;}
    public void addNoteStructure(NoteStructure noteStructure) {this.noteStructures.add(noteStructure);}
    
    public SourceDataStructure() {}
    
    public SourceDataStructure(Node node)
    {
    	super(node);
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("EVEN".equals(childNode.getNodeName()))
				this.addSourceDataEventRecordedStructure(new SourceDataEventsRecordedStructure(childNode));
			else if ("AGNC".equals(childNode.getNodeName()))
				this.setResponsibleAgency(Util.getNodeValue(childNode));
			else if ("NOTE".equals(childNode.getNodeName()))
				this.addNoteStructure(new NoteStructure(childNode));
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }
}
