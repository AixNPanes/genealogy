package ws.daley.genealogy.db.people;

import java.io.Serializable;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.IdNumber;

@Entity
public class TrailerRecord extends IdNumber implements Serializable
{
	@Transient
	private static final long serialVersionUID = 1L;
	
	@Transient
	private static HashMap<String, TrailerRecord> map = new HashMap<String, TrailerRecord>(); 
    public static HashMap<String, TrailerRecord> getTrailerRecordMap() {return map;}
    public static void setTrailerRecordMap(HashMap<String, TrailerRecord> headerRecordMap) {TrailerRecord.map = headerRecordMap;}
	
	public TrailerRecord() {super();}
	
	public TrailerRecord(@SuppressWarnings("unused") Node node) {this();}
	
	public static TrailerRecord getTrailerRecord(Node node)
	{
		if (map == null)
			map = new HashMap<String, TrailerRecord>();
		TrailerRecord record = map.get("");
		if (record != null)
			return record;
		record = new TrailerRecord(node);
		map.put("", record);
		return record;
	}
}
