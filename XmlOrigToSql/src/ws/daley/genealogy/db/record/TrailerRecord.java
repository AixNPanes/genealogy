package ws.daley.genealogy.db.record;

import org.w3c.dom.Node;

import ws.daley.genealogy.db.Gedcom55;

public class TrailerRecord extends GedRecord
{
    private static final long serialVersionUID = 1L;
    
    private Gedcom55 gedcom55;
    public Gedcom55 getGedcom55() {return this.gedcom55;}
    public void setGedcom55(Gedcom55 gedcom55) {this.gedcom55 = gedcom55;}
    
    public TrailerRecord() {}
    
    public TrailerRecord(Gedcom55 parent, Node node)
    {
    	super(node);
    	this.gedcom55 = parent;
    }
}
