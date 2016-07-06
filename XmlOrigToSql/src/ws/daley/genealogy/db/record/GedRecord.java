package ws.daley.genealogy.db.record;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import ws.daley.genealogy.util.Util;

public class GedRecord extends SubRecord
{
    private static final long serialVersionUID = 1L;

	private String recordType = "empty";
    public String getRecordType() {return this.recordType;}
    public void setRecordType(String recordType) {this.recordType = recordType;}

	private Long recordLevel = -1l;
    public Long getRecordLevel() {return this.recordLevel;}
    public void setRecordLevel(Long recordLevel) {this.recordLevel = recordLevel;}
    
	private String recordId = "none";
    public String getRecordId() {return this.recordId;}
    public void setRecordId(String recordId) {this.recordId = recordId;}

	private String recordValue = "";
    public String getRecordValue() {return this.recordValue;}
    public void setRecordValue(String RecordValue) {this.recordValue = RecordValue;}
    
    public GedRecord() {}
    
    public GedRecord(Node node)
    {
    	super(node);
		if ("#text".equals(node.getNodeName()))
			return;
		this.setRecordType(node.getNodeName());
		NamedNodeMap attributes = node.getAttributes();
		if (attributes != null)
		{
			this.setRecordLevel(Util.getLongAttribute(attributes, "level"));
			this.setRecordId(Util.getStringAttribute(attributes, "id"));
			this.setRecordValue(Util.getStringAttribute(attributes, "value"));
		}
    }
}
