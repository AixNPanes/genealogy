package ws.daley.genealogy.gedcom;

import org.apache.xerces.dom.NodeImpl;

public class GedSubmitterRecord extends GedDocument
{
	private String gedSubmitter = null;
	
	public GedSubmitterRecord(NodeImpl node)
    {
	    super(node);
    }
	
//	@Override
//    public void reorg()
//	{
//		if (this.reorganized)
//			return;
//		super.reorg();
//		this.gedSubmitter = getChildID(this);
//	}
	
	public String getSubmitter() {return this.gedSubmitter;}
}
