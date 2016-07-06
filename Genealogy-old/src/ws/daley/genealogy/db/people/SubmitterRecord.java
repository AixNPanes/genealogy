package ws.daley.genealogy.db.people;

import java.util.HashMap;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.Instruction;
import ws.daley.genealogy.db.people.structure.Text;

@Entity
public class SubmitterRecord extends Text
{
	@Transient
    private static final long serialVersionUID = 1L;

	@Transient
    private static HashMap<String, SubmitterRecord> map = null; 
	public static HashMap<String, SubmitterRecord> getSubmitterRecordMap() {return map;}
    public static void setSubmitterRecordMap(HashMap<String, SubmitterRecord> submitterRecordMap) {SubmitterRecord.map = submitterRecordMap;}
    
	@Basic
	private String submitter;
    public String getSubmitter() {return this.submitter;}
    public void setSubmitter(String submitter) {this.submitter = submitter;}
    
    private static Instruction[] instructions = new Instruction[] {
    };
	
	public SubmitterRecord() {}

	public SubmitterRecord(@SuppressWarnings("unused") Node node) {}
	
	public void processInstructions()
	{
		Instruction.buildChildren(this, instructions, this.nodeChildren);
	}
	
	public static SubmitterRecord getSubmitterRecord(Node node)
	{
		if (map == null)
			map = new HashMap<String, SubmitterRecord>();
		SubmitterRecord record = map.get("");
		if (record != null)
			return record;
		record = new SubmitterRecord(node);
		map.put("", record);
		return record;
	}

	public void resolveIds() {}
}
