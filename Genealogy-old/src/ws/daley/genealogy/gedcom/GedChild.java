package ws.daley.genealogy.gedcom;

import org.apache.xerces.dom.NodeImpl;

public class GedChild extends GedIndividualRecord
{
	public GedChild(NodeImpl node)
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
