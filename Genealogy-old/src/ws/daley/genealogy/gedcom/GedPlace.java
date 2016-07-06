package ws.daley.genealogy.gedcom;

import org.apache.xerces.dom.NodeImpl;

public class GedPlace extends GedID
{
	private String gedPlace = null;
	
	public GedPlace(NodeImpl node)
    {
	    super(node);
    }

//    @Override
//    public void reorg()
//    {
//		if (this.reorganized)
//			return;
//	    super.reorg();
//	    this.gedPlace = getReference(getGedcomHTML().getPlaceRecords());
//	}
    
    public String getPlace() {return this.gedPlace;}
}
