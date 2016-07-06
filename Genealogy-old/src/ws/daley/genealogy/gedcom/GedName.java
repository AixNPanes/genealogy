package ws.daley.genealogy.gedcom;

import org.apache.xerces.dom.NodeImpl;

public class GedName extends GedDocument
{
	public String gedName = null;
	
	public GedName(NodeImpl node)
    {
	    super(node);
    }
	
//	@Override
//    public void reorg()
//	{
//		if (this.reorganized)
//			return;
//		super.reorg();
//		this.gedName = getChildValue(this);
//	}
	
	public String getName() {return this.gedName;}
}
