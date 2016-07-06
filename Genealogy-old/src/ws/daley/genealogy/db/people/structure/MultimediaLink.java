package ws.daley.genealogy.db.people.structure;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.Instruction;
import ws.daley.genealogy.db.people.element.MultimediaLinkFile;
import ws.daley.genealogy.db.people.element.MultimediaLinkFormat;
import ws.daley.genealogy.db.people.element.MultimediaLinkTitle;

@Entity
public class MultimediaLink extends Text
{
	@Transient
    private static final long serialVersionUID = 1L;
    
    @OneToMany
    private ArrayList<MultimediaLinkFormat> multimediaLinkFormat = new ArrayList<MultimediaLinkFormat>();
    public ArrayList<MultimediaLinkFormat> getMultimediaLinkFormat() {return this.multimediaLinkFormat;}
    public void setMultimediaLinkFormat(ArrayList<MultimediaLinkFormat> multimediaLinkFormat) {this.multimediaLinkFormat = multimediaLinkFormat;}
    public void addMultimediaLinkFormat(MultimediaLinkFormat newMultimediaLinkFormat)
    {
    	if (this.getMultimediaLinkFormat() == null)
    		this.setMultimediaLinkFormat(new ArrayList<MultimediaLinkFormat>());
    	this.getMultimediaLinkFormat().add(newMultimediaLinkFormat);
    }

    @OneToMany
    private ArrayList<MultimediaLinkTitle> multimediaLinkTitle = new ArrayList<MultimediaLinkTitle>();
    public ArrayList<MultimediaLinkTitle> getMultimediaLinkTitle() {return this.multimediaLinkTitle;}
    public void setMultimediaLinkTitle(ArrayList<MultimediaLinkTitle> multimediaLinkTitle) {this.multimediaLinkTitle = multimediaLinkTitle;}
    public void addMultimediaLinkTitle(MultimediaLinkTitle newMultimediaLinkTitle)
    {
    	if (this.getMultimediaLinkTitle() == null)
    		this.setMultimediaLinkTitle(new ArrayList<MultimediaLinkTitle>());
    	this.getMultimediaLinkTitle().add(newMultimediaLinkTitle);
    }

    @OneToMany
    private ArrayList<MultimediaLinkFile> multimediaLinkFile = new ArrayList<MultimediaLinkFile>();
    public ArrayList<MultimediaLinkFile> getMultimediaLinkFile() {return this.multimediaLinkFile;}
    public void setMultimediaLinkFile(ArrayList<MultimediaLinkFile> multimediaLinkFile) {this.multimediaLinkFile = multimediaLinkFile;}
    public void addMultimediaLinkFile(MultimediaLinkFile newMultimediaLinkFile)
    {
    	if (this.getMultimediaLinkFile() == null)
    		this.setMultimediaLinkFile(new ArrayList<MultimediaLinkFile>());
    	this.getMultimediaLinkFile().add(newMultimediaLinkFile);
    }

    @OneToMany
    private ArrayList<NoteStructure> noteStructure = new ArrayList<NoteStructure>();
    public ArrayList<NoteStructure> getNoteStructure() {return this.noteStructure;}
    public void setNoteStructure(ArrayList<NoteStructure> noteStructure) {this.noteStructure = noteStructure;}
    public void addNoteStructure(NoteStructure newNoteStructure)
    {
    	if (this.getNoteStructure() == null)
    		this.setNoteStructure(new ArrayList<NoteStructure>());
    	this.getNoteStructure().add(newNoteStructure);
    }
    
	@Transient
    private static Instruction[] instructions = new Instruction[] {
    	new Instruction("FORM", "multimediaLinkFormat"),
    	new Instruction("TITL", "multimediaLinkTitle"),
    	new Instruction("FILE", "multimediaLinkFile"),
    	new Instruction("NOTE", "noteStructure")
    };

	public MultimediaLink() {super();}

    public MultimediaLink(Node node)
    {
    	super(node);
		Instruction.buildChildren(this, instructions, this.nodeChildren);
    }
}
