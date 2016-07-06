package ws.daley.genealogy.gedcom;

import org.apache.xerces.dom.NodeImpl;

public class GedAddress extends GedDocument
{
	private String gedAddress = null;
	
	public GedAddress(NodeImpl node)
    {
	    super(node);
    }
//	
//	@Override
//    public void reorg()
//	{
//		if (this.reorganized)
//			return;
//		super.reorg();
//		this.gedAddress = getChildValue(this);
//	}
	
	public String getAddress() {return this.gedAddress;}
}
