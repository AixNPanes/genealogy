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
 *				LDS_SPOUSE_SEALING:= 
				n SLGS	{1:1} 
					+1 STAT <LDS_SPOUSE_SEALING_DATE_STATUS>							{0:1} 
					+1 DATE <DATE_LDS_ORD>												{0:1} 
					+1 TEMP <TEMPLE_CODE>												{0:1} 
					+1 PLAC <PLACE_LIVING_ORDINANCE>									{0:1} 
					+1 <<SOURCE_CITATION>>												{0:M} 
					+1 <<NOTE_STRUCTURE>>												{0:M}
 */
public class LDSSpouseSealingStructure extends GedRecord
{
    private static final long serialVersionUID = 1L;

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

    private List<SourceCitationStructure> sourceCitationStructures = new ArrayList<SourceCitationStructure>();
    public List<SourceCitationStructure> getSourceCitationStructures() {return this.sourceCitationStructures;}
    public void setSourceCitattionStructures(List<SourceCitationStructure> sourceCitationStructures) {this.sourceCitationStructures = sourceCitationStructures;}
    public void addSourceCitationStructure(SourceCitationStructure sourceCitationStructure) {this.sourceCitationStructures.add(sourceCitationStructure);}

    private List<NoteStructure> noteStructures = new ArrayList<NoteStructure>();
    public List<NoteStructure> getNoteStructures() {return this.noteStructures;}
    public void setNoteStructures(List<NoteStructure> noteStructures) {this.noteStructures = noteStructures;}
    public void addNoteStructure(NoteStructure noteStructure) {this.noteStructures.add(noteStructure);}
    
    public LDSSpouseSealingStructure() {}
    
    public LDSSpouseSealingStructure(Node node)
    {
    	super(node);
		NodeList nodeList = node.getChildNodes();
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
