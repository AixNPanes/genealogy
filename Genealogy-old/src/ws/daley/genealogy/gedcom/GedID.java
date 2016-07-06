package ws.daley.genealogy.gedcom;

import java.util.ArrayList;
import java.util.TreeMap;

import org.apache.xerces.dom.NodeImpl;

public class GedID extends GedDocument
{
	private String id = null;

	public GedID(NodeImpl node)
    {
	    super(node);
		this.id = getChildID(this);
		if (this.id == null)
			this.id = getChildValue(this);
    }
//    @Override
//    public void reorg()
//    {
//		if (this.reorganized)
//			return;
//	    super.reorg();
//    }

    public String getId(){return this.id;}

    public void setId(String id){this.id = id;}
    
    public String getReference(TreeMap<String, ArrayList<GedDocument>> map)
    {
    	ArrayList<GedDocument> docList = map.get(this.id);
    	if (docList == null || docList.size() == 0)
    		return null;
    	return getChildValue(docList.get(0));
 //   	return docList.get(0).getNodeValue();
    }
}
