package ws.daley.genealogy.db.structure;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.record.GedRecord;
import ws.daley.genealogy.util.Util;

/**
 * 
 * @author Tim.Daley
 *
 *				LDS_INDIVIDUAL_ORDINANCE:= 
				[ 
				n [ BAPL | CONL ] 														{1:1} 
					+1 STAT <LDS_BAPTISM_DATE_STATUS>									{0:1} 
					+1 DATE <DATE_LDS_ORD>												{0:1} 
					+1 TEMP <TEMPLE_CODE>												{0:1} 
					+1 PLAC <PLACE_LIVING_ORDINANCE>									{0:1} 
					+1 <<SOURCE_CITATION>>												{0:M} 
					+1 <<NOTE_STRUCTURE>>												{0:M} 
				| 
				n ENDL	{1:1} 
					+1 STAT <LDS_ENDOWMENT_DATE_STATUS>									{0:1} 
					+1 DATE <DATE_LDS_ORD>												{0:1} 
					+1 TEMP <TEMPLE_CODE>												{0:1} 
					+1 PLAC <PLACE_LIVING_ORDINANCE>									{0:1} 
					+1 <<SOURCE_CITATION>>												{0:M} 
					+1 <<NOTE_STRUCTURE>>												{0:M} 
				| 
				n SLGC	{1:1} 
					+1 STAT <LDS_CHILD_SEALING_DATE_STATUS>								{0:1} 
					+1 DATE <DATE_LDS_ORD>												{0:1} 
					+1 TEMP <TEMPLE_CODE>												{0:1} 
					+1 PLAC <PLACE_LIVING_ORDINANCE>									{0:1} 
					+1 FAMC @<XREF:FAM>@												{1:1} 
					+1 <<SOURCE_CITATION>>												{0:M} 
					+1 <<NOTE_STRUCTURE>>												{0:M}
				]
 */
public class LDSIndividualOrdinanceStructure extends GedRecord
{
    private static final long serialVersionUID = 1L;

    private String ordinanceType;
    public String getOrdinanceType() {return this.ordinanceType;}
    public void setOrdinanceType(String ordinanceType) {this.ordinanceType = ordinanceType;}

    private String dateStatus;
    public String getDateStatus() {return this.dateStatus;}
    public void setDateStatus(String dateStatus) {this.dateStatus = dateStatus;}

    private String date;
    public String getDate() {return this.date;}
    public void setDate(String date) {this.date = date;}

    private String templeCode;
    public String getTempleCode() {return this.templeCode;}
    public void setTempleCode(String templeCode) {this.templeCode = templeCode;}
    
    private String place;
    public String getPlace() {return this.place;}
    public void setPlace(String place) {this.place = place;}

    private String famc;
    public String getFamc() {return this.famc;}
    public void setFamc(String famc) {this.famc = famc;}

    private List<SourceCitationStructure> sourceCitationStructures = new ArrayList<SourceCitationStructure>();
    public List<SourceCitationStructure> getSourceCitationStructures() {return this.sourceCitationStructures;}
    public void setSourceCitattionStructures(List<SourceCitationStructure> sourceCitationStructures) {this.sourceCitationStructures = sourceCitationStructures;}
    public void addSourceCitationStructure(SourceCitationStructure sourceCitationStructure) {this.sourceCitationStructures.add(sourceCitationStructure);}

    private List<NoteStructure> noteStructures = new ArrayList<NoteStructure>();
    public List<NoteStructure> getNoteStructures() {return this.noteStructures;}
    public void setNoteStructures(List<NoteStructure> noteStructures) {this.noteStructures = noteStructures;}
    public void addNoteStructure(NoteStructure noteStructure) {this.noteStructures.add(noteStructure);}
    
    public LDSIndividualOrdinanceStructure() {}
    
    public LDSIndividualOrdinanceStructure(Node node)
    {
    	super(node);
		NodeList nodeList = node.getChildNodes();
		this.setOrdinanceType(node.getNodeName());
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("STAT".equals(childNode.getNodeName()))
				this.setDateStatus(Util.getNodeValue(childNode));
			else if ("DATE".equals(childNode.getNodeName()))
				this.setDate(Util.getNodeValue(childNode));
			else if ("TEMP".equals(childNode.getNodeName()))
				this.setTempleCode(Util.getNodeValue(childNode));
			else if ("PLAC".equals(childNode.getNodeName()))
				this.setPlace(Util.getNodeValue(childNode));
			else if ("FAMC".equals(childNode.getNodeName()))
				this.setFamc(Util.getNodeValue(childNode));
			else if ("SOUR".equals(childNode.getNodeName()))
				this.addSourceCitationStructure(new SourceCitationStructure(childNode));
			else if ("NOTE".equals(childNode.getNodeName()))
				this.addNoteStructure(new NoteStructure(childNode));
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }
}
