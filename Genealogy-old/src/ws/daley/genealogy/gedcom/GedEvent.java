package ws.daley.genealogy.gedcom;

import java.util.ArrayList;

import org.apache.xerces.dom.NodeImpl;

public class GedEvent extends GedDocument
{
	private String gedDate = null;
	private String gedPlace = null;
	private ArrayList<GedSource> gedSource = null;
	
	public GedEvent(NodeImpl node)
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
//		GedDate date = (GedDate)this.getChildDocument("Date");
//		if (date != null)
//			this.gedDate = date.getDate();
//		GedPlace place = (GedPlace)this.getChildDocument("Place");
//		if (place != null)
//			this.gedPlace = place.getPlace();
//		GedSource sourceCitation = (GedSource)this.getChildDocument("Source");
//		if (sourceCitation != null)
//		{
//			if (gedSource == null)
//				gedSource = new ArrayList<GedSource>();
//			gedSource.add(sourceCitation);
//		}
//		GedReferenceNumber number = (GedReferenceNumber)this.getChildDocument("UserReferenceNumber");
//		if (number != null)
//		{
//			int i = 0;
//		}
//	}

    public String getGedDate() {return this.gedDate;}
    public String getGedPlace() {return this.gedPlace;}
    public ArrayList<GedSource> getGedSource() {return this.gedSource;}
}
