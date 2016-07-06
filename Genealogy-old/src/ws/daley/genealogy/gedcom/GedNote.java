package ws.daley.genealogy.gedcom;

import org.apache.xerces.dom.NodeImpl;

public class GedNote extends GedID
{
	private String note = null;
	
	public GedNote(NodeImpl node)
    {
	    super(node);
    }
//
//    @Override
//    public void reorg()
//    {
//		if (this.reorganized)
//			return;
//	    super.reorg();
//	    this.note = getReference(getGedcomHTML().getNoteRecords());
//    }

    public String getNote() {return this.note;}
}
