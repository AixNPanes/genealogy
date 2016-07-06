package ws.daley.genealogy.db.structure;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.record.GedRecord;

/**
 * 
 * @author Tim.Daley
 *
 *				SPOUSE_TO_FAMILY_LINK:= 
				n FAMS @<XREF:FAM>@														{1:1} 
					+1 <<NOTE_STRUCTURE>>												{0:M}
 */

public class SpouseToFamilyLinkStructure extends GedRecord
{
    private static final long serialVersionUID = 1L;
    
    private List<NoteStructure> noteStructures = new ArrayList<NoteStructure>();
    public List<NoteStructure> getNoteStructures() {return this.noteStructures;}
    public void setNoteStructures(List<NoteStructure> noteStructures) {this.noteStructures = noteStructures;}
    public void addNoteStructure(NoteStructure noteStructure) {this.noteStructures.add(noteStructure);}

    public SpouseToFamilyLinkStructure() {}
    
    public SpouseToFamilyLinkStructure(Node node)
    {
    	super(node);
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("NOTE".equals(childNode.getNodeName()))
				this.addNoteStructure(new NoteStructure(childNode));
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }
}
