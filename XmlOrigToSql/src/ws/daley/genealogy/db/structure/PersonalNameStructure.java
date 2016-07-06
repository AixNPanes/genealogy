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
 *			PERSONAL_NAME_STRUCTURE:= 
				n NAME <NAME_PERSONAL>													{1:1} 
					+1 NPFX <NAME_PIECE_PREFIX>											{0:1} 
					+1 GIVN <NAME_PIECE_GIVEN>											{0:1} 
					+1 NICK <NAME_PIECE_NICKNAME>										{0:1} 
					+1 SPFX <NAME_PIECE_SURNAME_PREFIX									{0:1} 
					+1 SURN <NAME_PIECE_SURNAME>										{0:1} 
					+1 NSFX <NAME_PIECE_SUFFIX>											{0:1} 
					+1 <<SOURCE_CITATION>>												{0:M} 
					+1 <<NOTE_STRUCTURE>>												{0:M}
 */
public class PersonalNameStructure extends GedRecord
{
    private static final long serialVersionUID = 1L;

    private String prefix;
    public String getPrefix() {return this.prefix;}
    public void setPrefix(String prefix) {this.prefix = prefix;}

    private String given;
    public String getGiven() {return this.given;}
    public void setGiven(String given) {this.given = given;}

    private String nickname;
    public String getNickname() {return this.nickname;}
    public void setNickname(String nickname) {this.nickname = nickname;}

    private String surnamePrefix;
    public String getSurnamePrefix() {return this.surnamePrefix;}
    public void setSurnamePrefix(String surnamePrefix) {this.surnamePrefix = surnamePrefix;}

    private String surname;
    public String getSuname() {return this.surname;}
    public void setSuname(String surname) {this.surname = surname;}

    private String suffix;
    public String getSuffix() {return this.suffix;}
    public void setSuffix(String suffix) {this.suffix = suffix;}

    private List<SourceCitationStructure> sourceCitationStructures = new ArrayList<SourceCitationStructure>();
    public List<SourceCitationStructure> getSourceCitationStructures() {return this.sourceCitationStructures;}
    public void setSourceCitationStructures(List<SourceCitationStructure> sourceCitationStructures) {this.sourceCitationStructures = sourceCitationStructures;}
    public void addSourceCitationStructure(SourceCitationStructure sourceCitationStructure) {this.sourceCitationStructures.add(sourceCitationStructure);}

    private List<NoteStructure> noteStructures = new ArrayList<NoteStructure>();
    public List<NoteStructure> getNoteStructures() {return this.noteStructures;}
    public void setNoteStructures(List<NoteStructure> noteStructures) {this.noteStructures = noteStructures;}
    public void addNoteStructure(NoteStructure noteStructure) {this.noteStructures.add(noteStructure);}
    
    public PersonalNameStructure() {}
    
    public PersonalNameStructure(Node node)
    {
    	super(node);
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("NPFX".equals(childNode.getNodeName()))
			{
				this.setPrefix(Util.getNodeValue(childNode));
			}
			else if ("GIVN".equals(childNode.getNodeName()))
			{
				this.setGiven(Util.getNodeValue(childNode));
			}
			else if ("NICK".equals(childNode.getNodeName()))
			{
				this.setNickname(Util.getNodeValue(childNode));
			}
			else if ("SPFX".equals(childNode.getNodeName()))
			{
				this.setSurnamePrefix(Util.getNodeValue(childNode));
			}
			else if ("SURN".equals(childNode.getNodeName()))
			{
				this.setSuname(Util.getNodeValue(childNode));
			}
			else if ("NSFX".equals(childNode.getNodeName()))
			{
				this.setSuffix(Util.getNodeValue(childNode));
			}
			else if ("SOUR".equals(childNode.getNodeName()))
			{
				this.addSourceCitationStructure(new SourceCitationStructure(childNode));
			}
			else if ("NOTE".equals(childNode.getNodeName()))
			{
				this.addNoteStructure(new NoteStructure(childNode));
			}
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }
}
