package ws.daley.genealogy.gedcom;

import org.apache.xerces.dom.NodeImpl;

public class GedNoteRecord extends GedID
{
	private String gedNote = null;
	
	public GedNoteRecord(NodeImpl node)
	{
		super(node);
	}
	
//	@Override
//    public void reorg()
//	{
//		if (this.reorganized)
//			return;
//		super.reorg();
//		this.gedNote = getChildValue(this);
//	}
	
	public String getNote() {return this.gedNote;}
}
