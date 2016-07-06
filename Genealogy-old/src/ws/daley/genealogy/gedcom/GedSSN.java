package ws.daley.genealogy.gedcom;

import org.apache.xerces.dom.NodeImpl;

public class GedSSN extends GedDocument
{
	private String gedSSN = null;
	
	public GedSSN(NodeImpl node)
    {
	    super(node);
    }
	
//	@Override
//    public void reorg()
//	{
//		if (this.reorganized)
//			return;
//		super.reorg();
//		this.gedSSN = getChildValue(this);
//	}
	
	public String getSSN() {return this.gedSSN;}
}
