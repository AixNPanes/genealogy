package ws.daley.genealogy.db.structure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.XmlOrigToSQL;
import ws.daley.genealogy.db.places.Fixup;
import ws.daley.genealogy.db.places.Place;
import ws.daley.genealogy.db.record.GedRecord;
import ws.daley.genealogy.util.Util;

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

public class PlaceStructure extends GedRecord
{
    private static final long serialVersionUID = 1L;

    private String form;
    public String getForm() {return this.form;}
    public void setForm(String form) {this.form = form;}
    
    private List<SourceCitationStructure> sourceCitationStructures = new ArrayList<SourceCitationStructure>();
    public List<SourceCitationStructure> getSourceCitationStructures() {return this.sourceCitationStructures;}
    public void setSourceCitationStructures(List<SourceCitationStructure> sourceCitationStructures) {this.sourceCitationStructures = sourceCitationStructures;}
    public void addSourceCitationStructure(SourceCitationStructure sourceCitationStructure) {this.sourceCitationStructures.add(sourceCitationStructure);}

	private List<NoteStructure> noteStructures = new ArrayList<NoteStructure>();
    public List<NoteStructure> getNoteStructures() {return this.noteStructures;}
    public void setNoteStructures(List<NoteStructure> multimediaLinkStructures) {this.noteStructures = multimediaLinkStructures;}
    public void addNoteStructure(NoteStructure noteStructure) {this.noteStructures.add(noteStructure);}
    
    private Place place;
    public Place getPlace() {return this.place;}
    public void setPlace(Place place) {this.place = place;}

    public PlaceStructure() {}
    
    @SuppressWarnings("unchecked")
    public PlaceStructure(Node node)
    {
    	super(node);
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("FORM".equals(childNode.getNodeName()))
				this.setForm(Util.getNodeValue(childNode));
			else if ("SOUR".equals(childNode.getNodeName()))
				this.addSourceCitationStructure(new SourceCitationStructure(childNode));
			else if ("NOTE".equals(childNode.getNodeName()))
				this.addNoteStructure(new NoteStructure(childNode));
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
		String value = Util.getStringAttribute(node.getAttributes(), "value");
		if (value == null)
			throw new RuntimeException(node.getNodeName()+" has no value");
		String fixup = Fixup.getMapEntry(value);
		if (fixup != null)
			value = fixup;
//		List<Object> objects = XmlOrigToSQL.getEntityManager().createNamedQuery("findFixup").setParameter("name", value).getResultList();
//		if (objects != null && objects.size() == 1)
//		{
//			Fixup fixup = (Fixup)objects.get(0);
//			value = fixup.getFixup();
//		}
		List<Object> objects = XmlOrigToSQL.getEntityManager().createNamedQuery("findPlace").setParameter("name", value).getResultList();
		if (objects == null || objects.size() != 1)
		{
			objects = XmlOrigToSQL.getEntityManager().createNamedQuery("findPlace").setParameter("name", value+", USA").getResultList();
			if (objects == null || objects.size() != 1)
			{
				try{this.place = newPlace(value);}
				catch(IOException e){}
				System.out.println("place '"+value+"' not found");
				return;
			}
			else
			{
				int x = 0;
			}
		}
		this.place = (Place)objects.get(0);
//		if (this.place == null)
//			System.out.println("'"+value+"' not found.");
    }
    
    private Place newPlace(String name) throws IOException
    {
    	Place place = null;
    	String[] element = name.split(",");
    	for(int i = 0; i < element.length; i++)
    		element[i] = element[i].trim();
    	place = Place.getPlace(element);
    	return place;
    }
}
