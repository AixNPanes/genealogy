package ws.daley.genealogy.gedcom;

import java.util.ArrayList;

import java.util.TreeMap;

import org.apache.xerces.dom.NodeImpl;

public class GedIndividualRecord extends GedID
{
	private ArrayList<GedDocument> spouseInFamily = null;
	private GedSex gedSex = null;
	private String gedName = "";
	private GedEvent gedBirth = null;
	private GedEvent gedDeath = null;
	private GedEvent gedBurial = null;
	private String gedRelationshipToFather = null;
	private String gedRelationshipToMother = null;
	private TreeMap<String, TreeMap<String, ArrayList<GedDocument>>> gedChildren = null;

	public GedIndividualRecord(NodeImpl node)
	{
		super(node);
	}
	
//	@Override
//    public void reorg()
//	{
//		if (this.reorganized)
//			return;
//		super.reorg();
//		this.spouseInFamily = this.getChildDocuments("SpouseInFamily");
////		if (this.spouseInFamily != null)
////			for(GedDocument doc:this.spouseInFamily)
////				doc.reorg();
//		this.gedName = this.getChildValue("Name");
//		this.gedBirth = (GedEvent)this.getChildDocument("Birth");
//		this.gedDeath = (GedEvent)this.getChildDocument("Death");
//		this.gedBurial = (GedEvent)this.getChildDocument("Burial");
//		GedDocument relationshipToFather = this.getChildDocument("RelationshipToFather");
//		if (relationshipToFather != null)
//			this.gedRelationshipToFather = getChildValue(relationshipToFather);
//		GedDocument relationshipToMother = this.getChildDocument("RelationshipToMother");
//		if (relationshipToMother != null)
//			this.gedRelationshipToMother = getChildValue(relationshipToMother);
//		this.gedSex = (GedSex)this.getChildDocument("Sex");
//		this.gedChildren = this.children;
//	}
	
	public String[] getNameParts()
	{
    	return getNameParts("/");
	}
	
	public String[] getNameParts(String sep)
	{
    	String[] nameParts = getName().split("[" + sep + "]");
    	for(int j = 0; j < nameParts.length; j++)
    		nameParts[j] = nameParts[j].trim();
    	return nameParts;
	}

    public ArrayList<GedDocument> getSpouseInFamily() {return this.spouseInFamily;}
    public GedSex getSex() {return this.gedSex;}
    public String getName() {return this.gedName;}
    public GedEvent getBirth() {return this.gedBirth;}
    public GedEvent getDeath() {return this.gedDeath;}
    public GedEvent getBurial() {return this.gedBurial;}
    public String getRelationshipToFather() {return this.gedRelationshipToFather;}
    public String getRelationshipToMother() {return this.gedRelationshipToMother;}
    public TreeMap<String, TreeMap<String, ArrayList<GedDocument>>> getGedChildren() {return this.gedChildren;}
}
