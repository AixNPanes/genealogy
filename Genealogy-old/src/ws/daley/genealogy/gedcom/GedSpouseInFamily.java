package ws.daley.genealogy.gedcom;

import org.apache.xerces.dom.NodeImpl;

public class GedSpouseInFamily extends GedID
{
//	private ArrayList<GedDocument> gedFamilies = null;
	
	public GedSpouseInFamily(NodeImpl node)
	{
		super(node);
	}
	
//	@Override
//    public void reorg()
//	{
//		if (this.reorganized)
//			return;
//		super.reorg();
//		this.gedFamilies = getGedcomHTML().getFamilyRecords().get(this.getId());
//	}
	
//	public ArrayList<GedDocument> getFamilies()
//	{
//		if (this.gedFamilies == null)
//			this.gedFamilies = getGedcomHTML().getFamilyRecords().get(this.getId());
//		return this.gedFamilies;
//	}
}
