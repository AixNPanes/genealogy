package ws.daley.genealogy.db.people;

import java.io.Serializable;
import java.util.HashMap;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.Instruction;
import ws.daley.genealogy.db.people.structure.ChangeDate;
import ws.daley.genealogy.db.people.structure.Refn;
import ws.daley.genealogy.db.people.structure.SourceCitation;
import ws.daley.genealogy.db.people.structure.TextIdString;

@Entity
public class NoteRecord extends TextIdString implements Serializable
{
	@Transient
	private static final long serialVersionUID = 1L;
	
	@Transient
	private static HashMap<String, NoteRecord> map = null; 
    public static HashMap<String, NoteRecord> getNoteRecordMap() {return map;}
    public static void setNoteRecordMap(HashMap<String, NoteRecord> noteRecordMap) {NoteRecord.map = noteRecordMap;}
    
    @OneToOne
    private SourceCitation sourceCitation;
    public SourceCitation getSourceCitation() {return this.sourceCitation;}
    public void setSourceCitation(SourceCitation source) {this.sourceCitation = source;}
    
    @OneToOne
    private Refn refn;
    public Refn getRefn() {return this.refn;}
    public void setRefn(Refn refn) {this.refn = refn;}
    
    @OneToOne
    private ChangeDate changeDate;
    public ChangeDate getChangeDate() {return this.changeDate;}
    public void setChangeDate(ChangeDate changeDate) {this.changeDate = changeDate;}

    @Basic
    private String rin;
    public String getRin() {return this.rin;}
    public void setRin(String rin) {this.rin = rin;}
    
    @Transient
    private static Instruction[] instructions = new Instruction[] {
    	new Instruction("SOUR", "sourceCitation"),
    	new Instruction("REFN", "refn"),
    	new Instruction("DATE", "changeDate"),
    	new Instruction("RIN", "rin")
    };

    public NoteRecord() {super();}
	
    public NoteRecord(Node node) {super(node);}
    
    public void processInstructions()
    {
		Instruction.buildChildren(this, instructions, this.nodeChildren);
    }
	
	public static NoteRecord getNoteRecord(Node node)
	{
		if (map == null)
			map = new HashMap<String, NoteRecord>();
		String nodeName = node.getAttributes().getNamedItem("id").getNodeValue();
		NoteRecord record = map.get(nodeName);
		if (record != null)
			return record;
		record = new NoteRecord(node);
		map.put(nodeName, record);
		return record;
	}

	public void resolveIds() {}
}
