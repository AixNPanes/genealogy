package ws.daley.genealogy.gedcom;

import org.apache.xerces.dom.NodeImpl;

public class GedEMail extends GedDocument
{
	private String gedEMail = null;
	public GedEMail(NodeImpl node)
    {
	    super(node);
    }
//
//    @Override
//    public void reorg()
//    {
//		if (this.reorganized)
//			return;
//	    super.reorg();
//	    this.gedEMail = getChildValue(this);
//    }
    
    public String getEMail() {return this.gedEMail;}
}
