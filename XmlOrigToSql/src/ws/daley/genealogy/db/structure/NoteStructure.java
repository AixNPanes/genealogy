package ws.daley.genealogy.db.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.record.GedRecord;
import ws.daley.genealogy.util.Util;

/**
 * 
 * @author Tim.Daley
 *
 *				NOTE_STRUCTURE:= 
				[ 
				n NOTE @<XREF:NOTE>@													{1:1} 
					+1 <<SOURCE_CITATION>>												{0:M} 
				| 
				n NOTE [SUBMITTER_TEXT> | <NULL>]										{1:1} 
					+1 [ CONC | CONT ] <SUBMITTER_TEXT>									{0:M} 
					+1 <<SOURCE_CITATION>>												{0:M}
				]

 */

public class NoteStructure extends GedRecord
{
    private static final long serialVersionUID = 1L;

    private static TreeMap<Long, NoteStructure> noteStructures = new TreeMap<Long, NoteStructure>();
    public static TreeMap<Long, NoteStructure> getNoteStructures() {return noteStructures;}
    
    private List<SourceCitationStructure> sourceCitationStructures = new ArrayList<SourceCitationStructure>();
    public List<SourceCitationStructure> getSourceCitationStructures() {return this.sourceCitationStructures;}
    public void setSourceCitationStructures(List<SourceCitationStructure> sourceCitationStructures) {this.sourceCitationStructures = sourceCitationStructures;}
    public void addSourceCitationStructures(SourceCitationStructure sourceCitationStructure) {this.sourceCitationStructures.add(sourceCitationStructure);}
    
    public NoteStructure() {}
    
    public NoteStructure(Node node)
    {
    	super(node);
    	noteStructures.put(new Long(key), this);
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("SOUR".equals(childNode.getNodeName()))
				this.addSourceCitationStructures(new SourceCitationStructure(childNode));
			else if ("CONT".equals(childNode.getNodeName()))
				this.setRecordValue(Util.addContToValue(this.getRecordValue(), childNode, true));
			else if ("CONC".equals(childNode.getNodeName()))
				this.setRecordValue(Util.addContToValue(this.getRecordValue(), childNode, false));
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }
}
