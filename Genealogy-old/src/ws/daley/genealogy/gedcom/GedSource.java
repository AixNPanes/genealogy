package ws.daley.genealogy.gedcom;

import java.util.ArrayList;

import org.apache.xerces.dom.NodeImpl;

public class GedSource extends GedRef
{
	private GedSourceRecord sourceRecord = null;
	private String note = null;
	private String title = null;
	private String repository = null;
	private String sourceOriginator = null;
	private String publication = null;
	
	public GedSource(NodeImpl node)
    {
	    super(node);
    }

//    @Override
//    public void reorg()
//    {
//		if (this.reorganized)
//			return;
//	    super.reorg();
//	    ArrayList<GedDocument> list = getGedcomHTML().getSourceRecords().get(this.getId());
//	    if (list == null || list.size() == 0)
//	    	return;
//	    if (list.size() > 1)
//	    	for(GedDocument doc:list)
//	    		if (!getChildID(list.get(0)).equals(getChildID(doc)))
//	    			throw new RuntimeException();
//	    this.sourceRecord = (GedSourceRecord)list.get(0);
//    }
    
    public String getSource()
    {
    	return null;
    }

    public GedSourceRecord getSourceRecord() {return this.sourceRecord;}
    public String getNote() {return this.note;}
    public String getTitle() {return this.title;}
    public String getRepository() {return this.repository;}
    public String getSourceOriginator() {return this.sourceOriginator;}
    public String getPublication() {return this.publication;}
}
