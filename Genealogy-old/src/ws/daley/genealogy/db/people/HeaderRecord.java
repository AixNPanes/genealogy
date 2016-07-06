package ws.daley.genealogy.db.people;

import java.io.Serializable;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.IdNumber;
import ws.daley.genealogy.db.Instruction;

@Entity
public class HeaderRecord extends IdNumber implements Serializable
{
	@Transient
	private static final long serialVersionUID = 1L;

	@Transient
	private static HashMap<String, HeaderRecord> map = new HashMap<String, HeaderRecord>(); 
	public static HashMap<String, HeaderRecord> getHeaderRecordMap() {return map;}
    public static void setHeaderRecordMap(HashMap<String, HeaderRecord> headerRecordMap) {HeaderRecord.map = headerRecordMap;}
    
	@Transient
    private static Instruction[] instructions = new Instruction[] {
    };

	public HeaderRecord() {super();}
	
	public HeaderRecord(Node node) {super(node);}
	
	public void processInstructions()
	{
		Instruction.buildChildren(this, instructions, this.nodeChildren);
	}
	
	public static HeaderRecord getHeaderRecord(Node node)
	{
		if (map == null)
			map = new HashMap<String, HeaderRecord>();
		HeaderRecord record = map.get("");
		if (record != null)
			return record;
		record = new HeaderRecord(node);
		map.put("", record);
		return record;
	}
}
