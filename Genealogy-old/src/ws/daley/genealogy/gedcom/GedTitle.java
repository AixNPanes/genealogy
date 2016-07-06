package ws.daley.genealogy.gedcom;

import org.apache.xerces.dom.NodeImpl;

public class GedTitle extends GedDocument
{
	public String gedTitle = null;
	
	public GedTitle(NodeImpl node)
    {
	    super(node);
    }
	
//	@Override
//    public void reorg()
//	{
//		if (this.reorganized)
//			return;
//		super.reorg();
//		this.gedTitle = getChildValue(this);
//	}
	
	public String getTitle() {return this.gedTitle;}
}
