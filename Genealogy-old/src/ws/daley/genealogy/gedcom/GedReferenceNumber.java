package ws.daley.genealogy.gedcom;

import org.apache.xerces.dom.NodeImpl;

public class GedReferenceNumber extends GedDocument
{
	private String gedReferenceNumber = null;
	
	public GedReferenceNumber(NodeImpl node)
    {
	    super(node);
    }
	
//	@Override
//    public void reorg()
//	{
//		if (this.reorganized)
//			return;
//		super.reorg();
//		this.gedReferenceNumber = getChildValue(this);
//	}
	
	public String getReferenceNumber() {return this.gedReferenceNumber;}
}
