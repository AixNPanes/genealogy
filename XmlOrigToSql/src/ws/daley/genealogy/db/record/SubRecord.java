package ws.daley.genealogy.db.record;

import org.w3c.dom.Node;

public class SubRecord
{
    private static final long serialVersionUID = 1L;
    
    public static long key = -1;

    private Long recordKey = -1l;
    public Long getRecordKey() {return this.recordKey;}
    public void setRecordKey(Long recordKey) {this.recordKey = recordKey;}
    
    public SubRecord() {}
    
    public SubRecord(Node node)
    {
		if ("#text".equals(node.getNodeName()))
			return;
		this.setRecordKey(++key);
    }
}
