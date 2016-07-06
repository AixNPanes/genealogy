package ws.daley.genealogy.db.structure;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.record.GedRecord;
import ws.daley.genealogy.db.structure.sourcecitation.SourceCitationDataStructure;
import ws.daley.genealogy.db.structure.sourcecitation.SourceCitationEventStructure;
import ws.daley.genealogy.db.structure.sourcecitation.SourceCitationTextStructure;
import ws.daley.genealogy.util.Util;

/**
 * 
 * @author Tim.Daley
 *
 *				SOURCE_CITATION:=
				[ 
				n SOUR @<XREF:SOUR>@     pointer to source record 						{1:1} 
					+1 PAGE <WHERE_WITHIN_SOURCE>										{0:1} 
					+1 EVEN <EVENT_TYPE_CITED_FROM>										{0:1} 
						+2 ROLE <ROLE_IN_EVENT>											{0:1} 
					+1 DATA																{0:1} 
						+2 DATE <ENTRY_RECORDING_DATE>									{0:1} 
						+2 TEXT <TEXT_FROM_SOURCE>										{0:M} 
							+3 [ CONC | CONT ] <TEXT_FROM_SOURCE>						{0:M} 
					+1 QUAY <CERTAINTY_ASSESSMENT>										{0:1} 
					+1 <<MULTIMEDIA_LINK>>												{0:M} 
					+1 <<NOTE_STRUCTURE>>												{0:M} 
				|	 Systems not using source records 
				n SOUR <SOURCE_DESCRIPTION>												{1:1} 
					+1 [ CONC | CONT ] <SOURCE_DESCRIPTION>								{0:M} 
					+1 TEXT <TEXT_FROM_SOURCE>											{0:M} 
						+2 [CONC | CONT ] <TEXT_FROM_SOURCE>							{0:M} 
					+1 <<NOTE_STRUCTURE>>												{0:M} 
				]

 */

public class SourceCitationStructure extends GedRecord
{
    private static final long serialVersionUID = 1L;
    
    private String page;
    public String getPage() {return this.page;}
    public void setPage(String page) {this.page = page;}
    
    private SourceCitationEventStructure sourceCitationEventStructure;
    public SourceCitationEventStructure getNoteSourceCitationEventStructure() {return this.sourceCitationEventStructure;}
    public void setNoteSourceCitationEventStructure(SourceCitationEventStructure sourceCitationEventStructure) {this.sourceCitationEventStructure = sourceCitationEventStructure;}
    
    private SourceCitationDataStructure sourceCitationDataStructure;
    public SourceCitationDataStructure getNoteSourceCitationDataStructure() {return this.sourceCitationDataStructure;}
    public void setNoteSourceCitationDataStructure(SourceCitationDataStructure sourceCitationEventDataStructure) {this.sourceCitationDataStructure = sourceCitationEventDataStructure;}
    
    private String quay;
    public String getQuay() {return this.quay;}
    public void setQuay(String quay) {this.quay = quay;}
    
    private String text;
    public String getText() {return this.text;}
    public void setText(String text) {this.text = text;}
    
    private String footer;
    public String getFooter() {return this.footer;}
    public void setFooter(String footer) {this.footer = footer;}
    
    private List<MultimediaLinkStructure> multimediaLinkStructures = new ArrayList<MultimediaLinkStructure>();
    public List<MultimediaLinkStructure> getMultimediaLinkStructures() {return this.multimediaLinkStructures;}
    public void setMultimediaLinkStructures(List<MultimediaLinkStructure> multimediaLinkStructures) {this.multimediaLinkStructures = multimediaLinkStructures;}
    public void addMultimediaLinkStructures(MultimediaLinkStructure multimediaLinkStructure) {this.multimediaLinkStructures.add(multimediaLinkStructure);}
    
    private List<NoteStructure> noteStructures = new ArrayList<NoteStructure>();
    public List<NoteStructure> getNoteStructures() {return this.noteStructures;}
    public void setNoteStructures(List<NoteStructure> noteStructures) {this.noteStructures = noteStructures;}
    public void addNoteStructures(NoteStructure noteStructure) {this.noteStructures.add(noteStructure);}
    
    public SourceCitationStructure() {}
    
    public SourceCitationStructure(Node node)
    {
    	super(node);
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("CONT".equals(childNode.getNodeName()))
			{
				this.setRecordValue(Util.addContToValue(this.getRecordValue(), childNode, true));
			}
			else if ("CONC".equals(childNode.getNodeName()))
			{
				this.setRecordValue(Util.addContToValue(this.getRecordValue(), childNode, false));
			}
			else if ("PAGE".equals(childNode.getNodeName()))
			{
				this.setPage(Util.getNodeValue(childNode));
			}
			else if ("EVEN".equals(childNode.getNodeName()))
			{
				this.setNoteSourceCitationEventStructure(new SourceCitationEventStructure(childNode));
			}
			else if ("DATA".equals(childNode.getNodeName()))
			{
				this.setNoteSourceCitationDataStructure(new SourceCitationDataStructure(childNode));
			}
			else if ("QUAY".equals(childNode.getNodeName()))
			{
				this.setQuay(Util.getNodeValue(childNode));
			}
			else if ("TEXT".equals(childNode.getNodeName()))
			{
				this.setText((new SourceCitationTextStructure(childNode)).getRecordValue());
			}
			else if ("_FOOT".equals(childNode.getNodeName()))
			{
				this.setFooter(Util.getNodeValueWithChild(this.getClass(), childNode));
			}
			else if ("NOTE".equals(childNode.getNodeName()))
			{
				this.addNoteStructures(new NoteStructure(childNode));
			}
			else if ("OBJE".equals(childNode.getNodeName()))
			{
				this.addMultimediaLinkStructures(new MultimediaLinkStructure(childNode));
			}
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }
}
