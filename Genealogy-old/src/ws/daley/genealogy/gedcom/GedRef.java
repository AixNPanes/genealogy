package ws.daley.genealogy.gedcom;

import java.util.ArrayList;
import java.util.TreeMap;

import org.apache.xerces.dom.NodeImpl;

public class GedRef extends GedDocument
{
	private String ref = null;

	public GedRef(NodeImpl node)
    {
	    super(node);
    }
//    @Override
//    public void reorg()
//    {
//		if (this.reorganized)
//			return;
//	    super.reorg();
//		this.ref = getChildRef(this);
//		if (this.ref == null)
//			this.ref = getChildValue(this);
//    }

    public String getId() {return this.ref;}
    
    public String getReference(TreeMap<String, ArrayList<GedDocument>> map)
    {
    	ArrayList<GedDocument> docList = map.get(this.ref);
    	if (docList == null || docList.size() == 0)
    		return null;
    	return getChildValue(docList.get(0));
 //   	return docList.get(0).getNodeValue();
    }
}
