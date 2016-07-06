package ws.daley.genealogy.gedcom;

import org.apache.xerces.dom.NodeImpl;

public class GedPhone extends GedDocument
{
	private String gedPhone = null;
	
	public GedPhone(NodeImpl node)
    {
	    super(node);
    }

//    @Override
//    public void reorg()
//    {
//		if (this.reorganized)
//			return;
//	    super.reorg();
//	    this.gedPhone = getChildValue(this);
//    }
    
    public String getPhone() {return this.gedPhone;}
}
