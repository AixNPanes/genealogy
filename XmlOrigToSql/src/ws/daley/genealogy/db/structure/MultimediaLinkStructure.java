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
 *				MULTIMEDIA_LINK:=
				[	embedded form 
				n OBJE @<XREF:OBJE>@													{1:1} 
				|	linked form 
				n OBJE 																	{1:1} 
					+1 FORM <MULTIMEDIA_FORMAT>											{1:1} 
					+1 TITL <DESCRIPTIVE_TITLE>											{0:1} 
					+1 FILE <MULTIMEDIA_FILE_REFERENCE>									{1:1} 
					+1 <<NOTE_STRUCTURE>>												{0:M} 
				]

 */

public class MultimediaLinkStructure extends GedRecord
{
    private static final long serialVersionUID = 1L;
    
    private String format;
    public String getFormat() {return this.format;}
    public void setFormat(String ormat) {this.format = ormat;}
    
    private String title;
    public String getTitle() {return this.title;}
    public void setTitle(String title) {this.title = title;}
    
    private String fileReference;
    public String getFileReference() {return this.fileReference;}
    public void setFileReference(String fileReference) {this.fileReference = fileReference;}
    
    private List<NoteStructure> noteStructures = new ArrayList<NoteStructure>();
    public List<NoteStructure> getNoteStructures() {return this.noteStructures;}
    public void setNoteStructures(List<NoteStructure> noteStructures) {this.noteStructures = noteStructures;}
    public void addNoteStructure(NoteStructure noteStructure) {this.noteStructures.add(noteStructure);}
    
    public MultimediaLinkStructure() {}
    
    public MultimediaLinkStructure(Node node)
    {
    	super(node);
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("FORM".equals(childNode.getNodeName()))
				this.setFormat(Util.getNodeValue(childNode));
			else if ("TITL".equals(childNode.getNodeName()))
				this.setTitle(Util.getNodeValue(childNode));
			else if ("FILE".equals(childNode.getNodeName()))
				this.setFileReference(Util.getNodeValue(childNode));
			else if ("NOTE".equals(childNode.getNodeName()))
				this.addNoteStructure(new NoteStructure(childNode));
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }
}
