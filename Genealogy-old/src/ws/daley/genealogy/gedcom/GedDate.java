package ws.daley.genealogy.gedcom;

import org.apache.xerces.dom.NodeImpl;

public class GedDate extends GedDocument
{
	private String date = null;

	public GedDate(NodeImpl node)
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
//		this.date = getChildValue(this);
//	}
	
    public String getDate() {return this.date;}
}
