package ws.daley.genealogy.db.people;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.IdString;
import ws.daley.genealogy.db.Instruction;
import ws.daley.genealogy.db.people.element.SourceFiledBy;
import ws.daley.genealogy.db.people.element.SourceOriginator;
import ws.daley.genealogy.db.people.element.SourcePublication;
import ws.daley.genealogy.db.people.element.SourceText;
import ws.daley.genealogy.db.people.element.SourceTitle;
import ws.daley.genealogy.db.people.structure.ChangeDate;
import ws.daley.genealogy.db.people.structure.MultimediaLink;
import ws.daley.genealogy.db.people.structure.NoteStructure;
import ws.daley.genealogy.db.people.structure.Refn;
import ws.daley.genealogy.db.people.structure.SourceData;
import ws.daley.genealogy.db.people.structure.SourceRepositoryCitation;

@Entity
public class SourceRecord extends IdString implements Serializable
{
	@Transient
	private static final long serialVersionUID = 1L;
	
	@Transient
	private static HashMap<String, SourceRecord> map = null; 
    public static HashMap<String, SourceRecord> getSourceRecordMap() {return map;}
    public static void setSourceRecordMap(HashMap<String, SourceRecord> sourceRecordMap) {SourceRecord.map = sourceRecordMap;}
    
    @OneToMany
    private ArrayList<SourceData> sourceData = null;
    public ArrayList<SourceData> getSourceData() {return this.sourceData;}
    public void setSourceData(ArrayList<SourceData> newSourceData) {this.sourceData = newSourceData;}
    public void addSourceData(SourceData newSourceData)
    {
    	if (this.getSourceData() == null)
    		this.setSourceData(new ArrayList<SourceData>());
    	this.getSourceData().add(newSourceData);
    }

    @OneToMany
    private ArrayList<SourceOriginator> sourceOriginator = null;
    public ArrayList<SourceOriginator> getSourceOriginator() {return this.sourceOriginator;}
    public void setSourceOriginator(ArrayList<SourceOriginator> newSourceOriginator) {this.sourceOriginator = newSourceOriginator;}
    public void addSourceOriginator(SourceOriginator newSourceOriginator)
    {
    	if (this.getSourceOriginator() == null)
    		this.setSourceOriginator(new ArrayList<SourceOriginator>());
    	this.getSourceOriginator().add(newSourceOriginator);
    }

    @OneToMany
    private ArrayList<SourceTitle> sourceTitle = null;
    public ArrayList<SourceTitle> getSourceTitle() {return this.sourceTitle;}
    public void setSourceTitle(ArrayList<SourceTitle> newSourceTitle) {this.sourceTitle = newSourceTitle;}
    public void addSourceTitle(SourceTitle newSourceTitle)
    {
    	if (this.getSourceTitle() == null)
    		this.setSourceTitle(new ArrayList<SourceTitle>());
    	this.getSourceTitle().add(newSourceTitle);
    }

    @OneToMany
    private ArrayList<SourceFiledBy> sourceFiledBy = null;
    public ArrayList<SourceFiledBy> getSourceFiledBy() {return this.sourceFiledBy;}
    public void setSourceFiledBy(ArrayList<SourceFiledBy> newSourceFiledBy) {this.sourceFiledBy = newSourceFiledBy;}
    public void addSourceFiledBy(SourceFiledBy newSourceFiledBy)
    {
    	if (this.getSourceFiledBy() == null)
    		this.setSourceFiledBy(new ArrayList<SourceFiledBy>());
    	this.getSourceFiledBy().add(newSourceFiledBy);
    }

    @OneToMany
    private ArrayList<SourcePublication> sourcePublication = null;
    public ArrayList<SourcePublication> getSourcePublication() {return this.sourcePublication;}
    public void setSourcePublication(ArrayList<SourcePublication> newSourcePublication) {this.sourcePublication = newSourcePublication;}
    public void addSourcePublication(SourcePublication newSourcePublication)
    {
    	if (this.getSourcePublication() == null)
    		this.setSourcePublication(new ArrayList<SourcePublication>());
    	this.getSourcePublication().add(newSourcePublication);
    }

    @OneToMany
    private ArrayList<SourceText> sourceText = null;
    public ArrayList<SourceText> getSourceText() {return this.sourceText;}
    public void setSourceText(ArrayList<SourceText> newSourceText) {this.sourceText = newSourceText;}
    public void addSourceText(SourceText newSourceText)
    {
    	if (this.getSourceText() == null)
    		this.setSourceText(new ArrayList<SourceText>());
    	this.getSourceText().add(newSourceText);
    }

    @OneToMany
    private ArrayList<SourceRepositoryCitation> sourceRepositoryCitation = null;
    public ArrayList<SourceRepositoryCitation> getSourceRepositoryCitation() {return this.sourceRepositoryCitation;}
    public void setSourceRepositoryCitation(ArrayList<SourceRepositoryCitation> newSourceRepositoryCitation) {this.sourceRepositoryCitation = newSourceRepositoryCitation;}
    public void addSourceRepositoryCitation(SourceRepositoryCitation newSourceRepositoryCitation)
    {
    	if (this.getSourceRepositoryCitation() == null)
    		this.setSourceRepositoryCitation(new ArrayList<SourceRepositoryCitation>());
    	this.getSourceRepositoryCitation().add(newSourceRepositoryCitation);
    }

    @OneToMany
    private ArrayList<MultimediaLink> multimediaLink = null;
    public ArrayList<MultimediaLink> getMultimediaLink() {return this.multimediaLink;}
    public void setMultimediaLink(ArrayList<MultimediaLink> newMultimediaLink) {this.multimediaLink = newMultimediaLink;}
    public void addMultimediaLink(MultimediaLink newMultimediaLink)
    {
    	if (this.getMultimediaLink() == null)
    		this.setMultimediaLink(new ArrayList<MultimediaLink>());
    	this.getMultimediaLink().add(newMultimediaLink);
    }

    @OneToMany
    private ArrayList<NoteStructure> noteStructure = null;
    public ArrayList<NoteStructure> getNoteStructure() {return this.noteStructure;}
    public void setNoteStructure(ArrayList<NoteStructure> newNoteStructure) {this.noteStructure = newNoteStructure;}
    public void addNoteStructure(NoteStructure newNoteStructure)
    {
    	if (this.getNoteStructure() == null)
    		this.setNoteStructure(new ArrayList<NoteStructure>());
    	this.getNoteStructure().add(newNoteStructure);
    }

    @OneToMany
    private ArrayList<Refn> refn = null;
    public ArrayList<Refn> getRefn() {return this.refn;}
    public void setRefn(ArrayList<Refn> newRefn) {this.refn = newRefn;}
    public void addRefn(Refn newRefn)
    {
    	if (this.getRefn() == null)
    		this.setRefn(new ArrayList<Refn>());
    	this.getRefn().add(newRefn);
    }

    @Basic
    private String rin;
    public String getRin() {return this.rin;}
    public void setRin(String newRin) {this.rin = newRin;}

    @OneToMany
    private ArrayList<ChangeDate> changeDate = null;
    public ArrayList<ChangeDate> getChangeDate() {return this.changeDate;}
    public void setChangeDate(ArrayList<ChangeDate> newChangeDate) {this.changeDate = newChangeDate;}
    public void addChangeDate(ChangeDate newChangeDate)
    {
    	if (this.getChangeDate() == null)
    		this.setChangeDate(new ArrayList<ChangeDate>());
    	this.getChangeDate().add(newChangeDate);
    }
    
    @Transient
    private static Instruction[] instructions = new Instruction[] {
    	new Instruction("DATA", "sourceData"),
    	new Instruction("AUTH", "sourceOriginator"),
    	new Instruction("TITL", "sourceTitle"),
    	new Instruction("FILE", "sourceFiledBy"),
    	new Instruction("PUBL", "sourcePublication"),
    	new Instruction("TEXT", "sourceText"),
    	new Instruction("REPO", "sourceRepositoryCitation"),
    	new Instruction("OBJE", "multimediaLink"),
    	new Instruction("REFN", "refn"),
    	new Instruction("RIN", "rin"),
    	new Instruction("DATE", "changeDate"),
    	new Instruction("NOTE", "noteStructure")
    };

    public SourceRecord() {super();}
	
    public SourceRecord(Node node) {super(node);}
	
	public void processInstructions() {Instruction.buildChildren(this, instructions, this.nodeChildren);}
	
	public static SourceRecord getSourceRecord(Node node)
	{
		if (map == null)
			map = new HashMap<String, SourceRecord>();
		String nodeName = node.getAttributes().getNamedItem("id").getNodeValue();
		SourceRecord record = map.get(nodeName);
		if (record != null)
			return record;
		record = new SourceRecord(node);
		map.put(nodeName, record);
		return record;
	}

	public void resolveIds() {}
}
