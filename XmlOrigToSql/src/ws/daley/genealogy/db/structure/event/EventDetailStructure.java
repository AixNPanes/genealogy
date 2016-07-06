package ws.daley.genealogy.db.structure.event;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.record.GedRecord;
import ws.daley.genealogy.db.structure.AddressStructure;
import ws.daley.genealogy.db.structure.MultimediaLinkStructure;
import ws.daley.genealogy.db.structure.NoteStructure;
import ws.daley.genealogy.db.structure.PlaceStructure;
import ws.daley.genealogy.db.structure.SourceCitationStructure;
import ws.daley.genealogy.util.Util;

/**
 * 
 * @author Tim.Daley
 *
 *				EVENT_DETAIL:= 
				n TYPE <EVENT_DESCRIPTOR>												{0:1} 
				n DATE <DATE_VALUE>														{0:1}
				n <<PLACE_STRUCTURE>>													{0:1} 
				n <<ADDRESS_STRUCTURE>>													{0:1} 
				n AGE <AGE_AT_EVENT>													0:1} 
				n AGNC <RESPONSIBLE_AGENCY>												{0:1} 
				n CAUS <CAUSE_OF_EVENT>													{0:1} 
				n <<SOURCE_CITATION>>													{0:M} 
				n <<MULTIMEDIA_LINK>>													{0:M}
				n <<NOTE_STRUCTURE>>													{0:M}
 */

/**
 * 
 * @author Tim.Daley
 *
 *				PLACE_STRUCTURE:= 
				n PLAC <PLACE_VALUE>													{1:1} 
					+1 FORM <PLACE_HIERARCHY>											{0:1} 
					+1 <<SOURCE_CITATION>>												{0:M} 
					+1 <<NOTE_STRUCTURE>>												{0:M}
 */

public class EventDetailStructure extends GedRecord
{
    private static final long serialVersionUID = 1L;
    
    private static long lev = 100l;
    
    private String eventDescriptor;
    public String getEventDescriptor() {return this.eventDescriptor;}
    public void setEventDescriptor(String type) {this.eventDescriptor = type;}

    private String date;
    public String getDate() {return this.date;}
    public void setDate(String date) {this.date = date;}
    
    private String age;
    public String getAge() {return this.age;}
    public void setAge(String age) {this.age = age;}
    
    private String responsibleAgency;
    public String getResponsibleAgency() {return this.responsibleAgency;}
    public void setResponsibleAgency(String responsibleAgency) {this.responsibleAgency = responsibleAgency;}
    
    private String causeOfEvent;
    public String getCauseOfEvent() {return this.causeOfEvent;}
    public void setCauseOfEvent(String causeOfEvent) {this.causeOfEvent = causeOfEvent;}
    
    private PlaceStructure placeStructure;
    public PlaceStructure getPlaceStructure() {return this.placeStructure;}
    public void setPlaceStructure(PlaceStructure placeStructure) {this.placeStructure = placeStructure;}
    
    private AddressStructure addressStructure;
    public AddressStructure getAddressStructure() {return this.addressStructure;}
    public void setAddressStructure(AddressStructure addressStructure) {this.addressStructure = addressStructure;}
    
	private List<SourceCitationStructure> sourceCitationStructures = new ArrayList<SourceCitationStructure>();
    public List<SourceCitationStructure> getSourceCitationStructures() {return this.sourceCitationStructures;}
    public void setSourceCitationStructures(List<SourceCitationStructure> sourceCitationStructures) {this.sourceCitationStructures = sourceCitationStructures;}
    public void addSourceCitationStructure(SourceCitationStructure sourceCitationStructure) {this.sourceCitationStructures.add(sourceCitationStructure);}
    
    private List<MultimediaLinkStructure> multimediaLinkStructures = new ArrayList<MultimediaLinkStructure>();
    public List<MultimediaLinkStructure> getMultimediaLinkStructures() {return this.multimediaLinkStructures;}
    public void setMultimediaLinkStructures(List<MultimediaLinkStructure> multimediaLinkStructures) {this.multimediaLinkStructures = multimediaLinkStructures;}
    public void addMultimediaLinkStructure(MultimediaLinkStructure multimediaLinkStructure) {this.multimediaLinkStructures.add(multimediaLinkStructure);}

    private List<NoteStructure> noteStructures = new ArrayList<NoteStructure>();
    public List<NoteStructure> getNoteStructures() {return this.noteStructures;}
    public void setNoteStructures(List<NoteStructure> noteStructures) {this.noteStructures = noteStructures;}
    public void addNoteStructure(NoteStructure noteStructure) {this.noteStructures.add(noteStructure);}
    
    public EventDetailStructure()
    {
    	this.setRecordLevel(++lev);
    }
    
    public EventDetailStructure(Class<?> clazz)
    {
    	this.setRecordKey(++key);
    	this.setRecordLevel(++lev);
    	this.setRecordValue("from "+clazz.getSimpleName());
    }
    
    public EventDetailStructure(Node node)
    {
    	super(node);
    	this.setRecordLevel(9990000 + (++lev));
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("TYPE".equals(childNode.getNodeName()))
				this.setEventDescriptor(Util.getNodeValue(childNode));
			else if ("DATE".equals(childNode.getNodeName()))
				this.setDate(Util.getNodeValue(childNode));
			else if ("PLACE".equals(childNode.getNodeName()))
				this.setPlaceStructure(new PlaceStructure(childNode));
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
			else if ("SOUR".equals(childNode.getNodeName()))
				this.addSourceCitationStructure(new SourceCitationStructure(childNode));
			else if ("OBJE".equals(childNode.getNodeName()))
				this.addMultimediaLinkStructure(new MultimediaLinkStructure(childNode));
			else if ("NOTE".equals(childNode.getNodeName()))
				this.addNoteStructure(new NoteStructure(childNode));
			else if ("PLAC".equals(childNode.getNodeName()))
				this.setPlaceStructure(new PlaceStructure(childNode));
			else if ("AGE".equals(childNode.getNodeName()))
				this.setAge(Util.getNodeValue(childNode));
			else if ("AGNC".equals(childNode.getNodeName()))
				this.setResponsibleAgency(Util.getNodeValue(childNode));
			else if ("CAUS".equals(childNode.getNodeName()))
				this.setCauseOfEvent(Util.getNodeValue(childNode));
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }
}
