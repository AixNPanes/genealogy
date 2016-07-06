package ws.daley.genealogy.gedcom;

import java.util.ArrayList;

import org.apache.xerces.dom.NodeImpl;

public class GedFamilyRecord extends GedID
{
	private GedDocument gedHusband = null;
	private GedDocument gedWife = null;
	private ArrayList<GedDocument> gedChildren = new ArrayList<GedDocument>();

	public GedFamilyRecord(NodeImpl node)
	{
		super(node);
	}
	
//	@Override
//    public void reorg()
//	{
//		if (this.reorganized)
//			return;
//		super.reorg();
//		this.setId(this.getAttributes().get("id").get(0).getNodeValue());
//		this.gedHusband = this.getChildDocument("Husband");
//		this.gedWife = this.getChildDocument("Wife");
//		ArrayList<GedDocument> list = this.getChildDocuments("Child");
//		if (list != null)
//		{
//			for(GedDocument doc:list)
//				this.gedChildren.add(doc);
//		}
//	}
    public GedDocument getGedHusband() {return this.gedHusband;}
    public GedDocument getGedWife() {return this.gedWife;}
    public ArrayList<GedDocument> getGedChildren() {return this.gedChildren;}
}
