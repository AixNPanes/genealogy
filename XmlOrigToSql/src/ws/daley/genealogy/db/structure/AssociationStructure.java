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
 *				ASSOCIATION_STRUCTURE:= 
				n ASSO @<XREF:INDI>@													{0:M} 
					+1 TYPE <RECORD_TYPE>												{1:1} 
					+1 RELA <RELATION_IS_DESCRIPTOR>									{1:1} 
					+1 <<NOTE_STRUCTURE>>												{0:M} 
					+1 <<SOURCE_CITATION>>												{0:M}
 */

public class AssociationStructure extends GedRecord
{
    private static final long serialVersionUID = 1L;

    private String relation;
    public String getRelation() {return this.relation;}
    public void setRelation(String relation) {this.relation = relation;}

    private List<NoteStructure> noteStructures = new ArrayList<NoteStructure>();
    public List<NoteStructure> getNoteStructures() {return this.noteStructures;}
    public void setNoteStructures(List<NoteStructure> noteStructures) {this.noteStructures = noteStructures;}
    public void addNoteStructure(NoteStructure noteStructure) {this.noteStructures.add(noteStructure);}

    private List<SourceCitationStructure> sourceCitationStructures = new ArrayList<SourceCitationStructure>();
    public List<SourceCitationStructure> getSourceCitationStructures() {return this.sourceCitationStructures;}
    public void setSourceCitationStructures(List<SourceCitationStructure> sourceCitationStructures) {this.sourceCitationStructures = sourceCitationStructures;}
    public void addSourceCitationStructure(SourceCitationStructure sourceCitationStructure) {this.sourceCitationStructures.add(sourceCitationStructure);}
    
    public AssociationStructure() {}
    
    public AssociationStructure(Node node)
    {
    	super(node);
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("TYPE".equals(childNode.getNodeName()))
				this.setRecordType(Util.getNodeValue(childNode));
			else if ("RELA".equals(childNode.getNodeName()))
				this.setRelation(Util.getNodeValue(childNode));
			else if ("NOTE".equals(childNode.getNodeName()))
				this.addNoteStructure(new NoteStructure(childNode));
			else if ("SOUR".equals(childNode.getNodeName()))
				this.addSourceCitationStructure(new SourceCitationStructure(childNode));
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }
}
