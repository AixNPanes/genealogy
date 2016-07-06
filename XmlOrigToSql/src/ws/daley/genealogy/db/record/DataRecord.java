package ws.daley.genealogy.db.record;

import java.util.ArrayList;
import java.util.List;


public class DataRecord extends GedRecord
{
    private static final long serialVersionUID = 1L;
    
    private DataRecord parent;
    public DataRecord getParent() {return this.parent;}
    public void setParent(DataRecord parent) {this.parent = parent;}
    
    private List<DataRecord> contains = new ArrayList<DataRecord>();
    public List<DataRecord> getContains() {return this.contains;}
    public void setContains(List<DataRecord> contains) {this.contains = contains;}
    
    public DataRecord() {}
}
