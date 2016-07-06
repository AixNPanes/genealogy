package ws.daley.genealogy.db.structure.source;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.record.GedRecord;
import ws.daley.genealogy.db.structure.NoteStructure;
import ws.daley.genealogy.db.structure.source.repo.SourceRepoCallNumberStructure;

/**
 * 
 * @author Tim.Daley
 *
 *	n REPO [ @XREF:REPO@ | <NULL>]			{1:1}
		+1 <<NOTE_STRUCTURE>>				{0:M}
		+1 CALN <SOURCE_CALL_NUMBER>		{0:M}
			+2 MEDI <SOURCE_MEDIA_TYPE>		{0:1}
 */

public class SourceRepoCitationStructure extends GedRecord
{
	private static final long serialVersionUID = 1L;

    private List<NoteStructure> noteStructures = new ArrayList<NoteStructure>();
    public List<NoteStructure> getNoteStructures() {return this.noteStructures;}
    public void setNoteStructures(List<NoteStructure> noteStructures) {this.noteStructures = noteStructures;}
    public void addNoteStructure(NoteStructure noteStructure) {this.noteStructures.add(noteStructure);}

    private List<SourceRepoCallNumberStructure> sourceRepositoryCallNumberStructures = new ArrayList<SourceRepoCallNumberStructure>();
    public List<SourceRepoCallNumberStructure> getSourceRepositoryCallNumberStructures() {return this.sourceRepositoryCallNumberStructures;}
    public void setSourceRepositoryCallNumberStructures(List<SourceRepoCallNumberStructure> sourceRepositoryCallNumberStructures) {this.sourceRepositoryCallNumberStructures = sourceRepositoryCallNumberStructures;}
    public void addSourceRepositoryCallNumberStructure(SourceRepoCallNumberStructure sourceRepositoryCallNumberStructure) {this.sourceRepositoryCallNumberStructures.add(sourceRepositoryCallNumberStructure);}
    
    public SourceRepoCitationStructure() {}
    
    public SourceRepoCitationStructure(Node node)
    {
    	super(node);
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("NOTE".equals(childNode.getNodeName()))
			{
//				this.addNoteStructure(new NoteStructure(childNode));
			}
			else if ("CALN".equals(childNode.getNodeName()))
			{
//				this.addSourceRepositoryCallNumberStructure(new SourceRepoCallNumberStructure(childNode));
			}
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }
}
