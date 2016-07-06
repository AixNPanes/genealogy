package ws.daley.genealogy.gedcom;

import org.apache.xerces.dom.NodeImpl;

public class GedSex extends GedDocument
{
	private String gedSex = null;
	
	public GedSex(NodeImpl node)
    {
	    super(node);
    }
	
//	@Override
//    public void reorg()
//	{
//		if (this.reorganized)
//			return;
//		super.reorg();
//		this.gedSex = getChildValue(this);
//	}
	
	public String getSex() {return this.gedSex;}
}
