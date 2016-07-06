package ws.daley.genealogy.gedcom;

import org.apache.xerces.dom.NodeImpl;

public class GedSourceRecord extends GedID
{
	private GedTitle gedTitle = null;
	private GedDocument gedRepository = null;
	private GedNote gedNote = null;
	
	public GedSourceRecord(NodeImpl node)
    {
	    super(node);
    }
	
//	@Override
//    public void reorg()
//	{
//		if (this.reorganized)
//			return;
//		super.reorg();
//		this.gedNote = (GedNote)getChildDocument("Note");
//		this.gedRepository = getChildDocument("Repository");
//		this.gedTitle = (GedTitle)getChildDocument("Title");
//	}

    public GedTitle getGedTitle() {return this.gedTitle;}
    public GedDocument getGedRepository() {return this.gedRepository;}
    public GedNote getGedNote() {return this.gedNote;}
}
