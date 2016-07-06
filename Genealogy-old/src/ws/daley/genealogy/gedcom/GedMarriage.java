package ws.daley.genealogy.gedcom;

import org.apache.xerces.dom.NodeImpl;

public class GedMarriage extends GedEvent
{
	public GedMarriage(NodeImpl node)
	{
		super(node);
	}
	
//	@Override
//    public void reorg()
//	{
//		if (this.reorganized)
//			return;
//		super.reorg();
//	}
}
